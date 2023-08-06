package controller;

import elementarium.models.Element;
import elementarium.models.Result;
import elementarium.utils.SceneUtil;
import elementarium.utils.animation.Animation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import play.Main;

import java.util.ArrayList;
import java.util.List;

public class Chemistry2Controller {

    public static String text;

    public String helpSingleText;

    public static int resId;
    @FXML
    private Pane congraBox;

    @FXML
    private TextField goalText;

    static final int numColumns = 3;
    static final int numRows = 3;
    @FXML
    private GridPane gridPane;

    protected ImageView draggedImageView;

    protected DataFormat idDataFormat = IdData.getInstance().getDataFormat();
    protected DataFormat rowDataFormat = IdData.getInstance().getRowIndexFormat();

    protected DataFormat colDataFormat = IdData.getInstance().getColIndexFormat();

    @FXML
    private ListView<ImageView> listView;
    protected List<Element> elements = new ArrayList<Element>();

    protected List<Integer> bar = new ArrayList<Integer>();

    protected Result[][] comRes = Main.getComRes();
    protected ObservableList<ImageView> imageList = FXCollections.observableArrayList();

    public static final int ELEMENT_WIDTH = 80;
    public static final int ELEMENT_HEIGHT = 80;

    protected Pane knowledgeBox;
    @FXML
    protected TextArea knowledgeText;
    @FXML
    protected ImageView newElement;
    @FXML
    protected TextField elementName;
    protected SceneUtil sceneUtil = SceneUtil.getInstance();

    @FXML
    public Pane helpTab;
    @FXML
    private TextArea helpText;

    public Chemistry2Controller() {


    }

    public void setup() {
        elements = Main.getElements();
        goalText.setText(text);
        helpText.setText(helpSingleText);
        goalText.setStyle("-fx-font-weight: bold;-fx-font-size: 20px;");
//        bar.add(60);
//        bar.add(58);
//        bar.add(56);
//        bar.add(60);
//        bar.add(54);
//        bar.add(56);
//        bar.add(58);
//        bar.add(60);
//        bar.add(54);
        for (int i : bar) {
            Element element = elements.get(i - 1);
            ImageView imageView = new ImageView(element.getImageLink());
            imageView.setUserData(element.getElementId());
            imageList.add(imageView);

        }
        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                int index = i * numColumns + j;
                if (index < imageList.size()) {
                    ImageView imageView = imageList.get(index);

                    GridPane.setConstraints(imageView, j, i);
                    GridPane.setValignment(imageView, VPos.CENTER);
                    GridPane.setHalignment(imageView, HPos.CENTER);

                    gridPane.getChildren().add(imageView);
                }
            }
        }

    }

    public void initialize() {

        setup();

        gridPane.setOnDragDetected(
                event -> {

                    draggedImageView = null;
                    for (Node imageView : gridPane.getChildren()) {
                        ImageView tmp = (ImageView) imageView;
                        if (tmp.getLayoutX() <= event.getX()
                                && event.getX() <= tmp.getLayoutX() + ELEMENT_WIDTH
                                && tmp.getLayoutY() <= event.getY()
                                && event.getY() <= tmp.getLayoutY() + ELEMENT_HEIGHT) {
                            draggedImageView = tmp;
                            break;
                        }
                    }
                    if (draggedImageView != null) {
                        Dragboard db = draggedImageView.startDragAndDrop(TransferMode.MOVE);
                        int id = (int) draggedImageView.getUserData();
                        ClipboardContent content = new ClipboardContent();
                        content.putImage(draggedImageView.getImage());
                        content.put(colDataFormat, GridPane.getColumnIndex(draggedImageView));
                        content.put(rowDataFormat, GridPane.getRowIndex(draggedImageView));
                        content.put(idDataFormat, id);
                        db.setContent(content);
                    }
                    event.consume();
                });


        gridPane.setOnDragDropped(
                event -> {
                    Dragboard dragboard = event.getDragboard();
                    if (dragboard.hasImage()) {
                        // DataFormat idDataFormatt = new DataFormat("Imgid");
                        Integer idValue =
                                (Integer) dragboard.getContent(idDataFormat); // Ép kiểu Object sang Integer
                        int id = idValue.intValue();
                        ImageView imageView = new ImageView(dragboard.getImage());
                        imageView.setLayoutX(event.getX() - ELEMENT_WIDTH / 2);
                        imageView.setLayoutY(event.getY() - ELEMENT_HEIGHT / 2);
                        imageView.setUserData(id);
                        int row = ((Integer) dragboard.getContent(rowDataFormat)).intValue();
                        int col = ((Integer) dragboard.getContent(colDataFormat)).intValue();


                        if (event.getGestureSource() instanceof ImageView) {
                            gridPane.getChildren().remove(draggedImageView);
                        }
                        int id1 = 0, id2 = 0;
                        ImageView override = null;
                        for (Node node : gridPane.getChildren()) {
                            ImageView img = (ImageView) node;
                            if (overlap(img, imageView) || overlap(imageView, img)) {
                                id1 = (int) img.getUserData();
                                id2 = (int) imageView.getUserData();
                                override = img;
                                break;
                            }
                        }
                        if (override != null) {
                            Result curCom = null;
                            curCom = comRes[id1][id2];
                            if (curCom != null) {
                                Element resElement = elements.get(curCom.getId() - 1);
                                int rowIndex = GridPane.getRowIndex(override);
                                int colIndex = GridPane.getColumnIndex(override);
                                gridPane.getChildren().remove(override);
                                ImageView newImg = new ImageView(resElement.getImageLink());
                                newImg.setLayoutX(override.getLayoutX() - ELEMENT_WIDTH / 2);
                                newImg.setLayoutY(override.getLayoutY() - ELEMENT_HEIGHT / 2);
                                newImg.setUserData(resElement.getElementId());
                                GridPane.setConstraints(newImg, colIndex, rowIndex);
                                GridPane.setHalignment(newImg, HPos.CENTER);
                                GridPane.setValignment(newImg, VPos.CENTER);
                                gridPane.getChildren().add(newImg);
                                knowledgeBox.setVisible(!knowledgeBox.isVisible());
                                knowledgeBox.setDisable(false);
                                knowledgeText.setText(curCom.getDes());
                                newElement.setImage(new Image(resElement.getImageLink()));
                                elementName.setText(resElement.getName());
                                checkRes(resElement);

                            } else {
                                // todo
                                Animation.shakeImageView(imageView);
                                System.out.println("Cant combine");
                                GridPane.setConstraints(imageView, col, row);
                                GridPane.setHalignment(imageView, HPos.CENTER);
                                GridPane.setValignment(imageView, VPos.CENTER);
                                gridPane.getChildren().add(imageView);
                            }
                        } else {
                            System.out.println("khong co override");
                            GridPane.setConstraints(imageView, col, row);
                            GridPane.setHalignment(imageView, HPos.CENTER);
                            GridPane.setValignment(imageView, VPos.CENTER);
                            gridPane.getChildren().add(imageView);
                        }
                    }
                    event.setDropCompleted(true);
                    event.consume();
                });
        gridPane.setOnDragOver(
                event -> {
                    if (event.getDragboard().hasImage()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }
                    event.consume();
                });


    }


    public boolean overlap(ImageView x, ImageView y) {
        if (x.getLayoutX() <= y.getLayoutX() && y.getLayoutX() <= x.getLayoutX() + ELEMENT_WIDTH
                && x.getLayoutY() <= y.getLayoutY() && y.getLayoutY() <= x.getLayoutY() + ELEMENT_WIDTH)
            return true;
        return x.getLayoutX() <= y.getLayoutX() && y.getLayoutX() <= x.getLayoutX() + ELEMENT_WIDTH
                && x.getLayoutY() <= y.getLayoutY() + ELEMENT_WIDTH && y.getLayoutY() + ELEMENT_WIDTH <= x.getLayoutY() + ELEMENT_WIDTH;
    }

    public void closeHelpTab() {
        helpTab.setDisable(true);
        helpTab.setVisible(false);
    }

    public void checkRes(Element resElement) {
        if (resElement.getElementId() == resId) {
            System.out.println("Win game");

            congraBox.setDisable(false);
            congraBox.setVisible(true);
        }
    }


}
