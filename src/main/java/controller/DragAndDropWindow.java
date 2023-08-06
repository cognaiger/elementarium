package controller;

import elementarium.models.Element;
import elementarium.models.Result;
import elementarium.utils.SceneUtil;
import elementarium.utils.animation.Animation;
import elementarium.utils.animation.HoverEffectUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import play.Main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public abstract class DragAndDropWindow {

    @FXML
    protected ListView<ImageView> listView;

    @FXML
    protected ListView<String> listViewText;

    @FXML
    protected Pane pane;
    @FXML
    protected Pane knowledgeBox;
    @FXML
    protected Button backBtn;
    @FXML
    protected TextArea knowledgeText;
    @FXML
    protected ImageView newElement;
    @FXML
    protected TextField elementName;


    protected List<Integer> initialId = new ArrayList<>();



    protected int resId;


    protected ImageView draggedImageView;
    public static final int ELEMENT_WIDTH = 80;
    public static final int ELEMENT_HEIGHT = 80;

    protected SceneUtil sceneUtil = SceneUtil.getInstance();

    protected DataFormat idDataFormat = IdData.getInstance().getDataFormat();

    protected List<Element> elements = Main.getElements();
    protected Result[][] comRes = Main.getComRes();

    protected List<Integer> bar = new ArrayList<Integer>();

    protected boolean inBar[] = new boolean[1000];

    protected ObservableList<ImageView> imageList = FXCollections.observableArrayList();

    public DragAndDropWindow() {

    }

    public void setup() {

        imageList.clear();
        for (int i : bar) {
            Element x = elements.get(i - 1);
            ImageView imageView = new ImageView(x.getImageLink());
            imageView.setUserData(x.getElementId());
            imageList.add(imageView);
        }

        // Đặt items cho ListView
        listView.setItems(imageList);

        // Đặt cell factory cho ListView
        listView.setCellFactory(
                new Callback<ListView<ImageView>, ListCell<ImageView>>() {
                    @Override
                    public ListCell<ImageView> call(ListView<ImageView> param) {
                        return new ListCell<ImageView>() {
                            @Override
                            protected void updateItem(ImageView item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty || item == null) {
                                    setText(null);
                                    setGraphic(null);
                                } else {
                                    setGraphic(item);
                                    setUserData(item.getUserData());
                                }
                            }
                        };
                    }
                });
    }

    public void initialize() throws SQLException, ClassNotFoundException {

        // Tạo danh sách các ImageView

        setup();

        for (int i : bar) {
            Element x = elements.get(i - 1);
            listViewText.getItems().add(x.getName());
        }
        listViewText.setCellFactory(param -> new CustomListCell(18));

        // Set the onDragDetected event for the ImageView items in the ListView
        listView.setOnDragDetected(
                event -> {
                    if (listView.getSelectionModel().getSelectedItem() != null) {
                        Dragboard dragboard = listView.startDragAndDrop(TransferMode.COPY);
                        // Put the image on the dragboard
                        ClipboardContent clipboardContent = new ClipboardContent();
                        // System.out.println(listView.getSelectionModel().getSelectedItem().getImage().getELEMENT_WIDTH());
                        clipboardContent.putImage(listView.getSelectionModel().getSelectedItem().getImage());
                        int id = (int) listView.getSelectionModel().getSelectedItem().getUserData();
                        clipboardContent.put(idDataFormat, id);
                        dragboard.setContent(clipboardContent);
                        int idValue = (int) dragboard.getContent(idDataFormat);
                    }
                    event.consume();
                });

        // Set the onDragDropped event for the pane to handle the drop
        pane.setOnDragDropped(
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

                        if (event.getGestureSource() instanceof ImageView) {
                            pane.getChildren().remove(draggedImageView);
                        }
                        int id1 = 0, id2 = 0;
                        ImageView override = null;
                        for (Node node : pane.getChildren()) {
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
                                if (inBar[resElement.getElementId()]) { // / sản phẩm đã có từ trước
                                    pane.getChildren().remove(override);
                                    ImageView newImg = new ImageView(resElement.getImageLink());
                                    newImg.setLayoutX(event.getX() - ELEMENT_WIDTH / 2);
                                    newImg.setLayoutY(event.getY() - ELEMENT_HEIGHT / 2);
                                    newImg.setUserData(resElement.getElementId());
                                    pane.getChildren().add(newImg);
                                } else {  /// sản phẩm chưa có, cần hiển thị bảng.

                                    knowledgeBox.setVisible(!knowledgeBox.isVisible());
                                    knowledgeBox.setDisable(false);
                                    knowledgeText.setText(curCom.getDes());
                                    newElement.setImage(new Image(resElement.getImageLink()));
                                    elementName.setText(resElement.getName());
                                    listViewText.getItems().add(resElement.getName());
                                    listViewText.setCellFactory(param -> new CustomListCell(16));
                                    inBar[resElement.getElementId()] = true;
                                    bar.add(resElement.getElementId());
                                    setup();
                                    pane.getChildren().remove(override);
                                    ImageView newImg = new ImageView(resElement.getImageLink());
                                    newImg.setLayoutX(event.getX() - ELEMENT_WIDTH / 2);
                                    newImg.setLayoutY(event.getY() - ELEMENT_HEIGHT / 2);
                                    newImg.setUserData(resElement.getElementId());
                                    pane.getChildren().add(newImg);
                                }
                            }
                        } else {
                            pane.getChildren().add(imageView);
                        }
                    }
                    event.setDropCompleted(true);
                    event.consume();
                });

        // Set the onDragDetected event for the ImageView items in the ListView



        listView.setOnDragDetected(
                event -> {

                    if (listView.getSelectionModel().getSelectedItem() != null) {
                        System.out.println("DRAG DETEECT");
                        Dragboard dragboard = listView.startDragAndDrop(TransferMode.COPY);
                        // Put the image on the dragboard
                        ClipboardContent clipboardContent = new ClipboardContent();
                        // System.out.println(listView.getSelectionModel().getSelectedItem().getImage().getELEMENT_WIDTH());
                        clipboardContent.putImage(listView.getSelectionModel().getSelectedItem().getImage());
                        int id = (int) listView.getSelectionModel().getSelectedItem().getUserData();
                        clipboardContent.put(idDataFormat, id);
                        dragboard.setContent(clipboardContent);
                        int idValue = (int) dragboard.getContent(idDataFormat);
                    }
                    event.consume();
                });

        // Set the onDragDropped event for the pane to handle the drop
        pane.setOnDragDropped(
                event -> {
                    System.out.println("DROP ON DRAGBOARD");
                    Dragboard dragboard = event.getDragboard();
                    if (dragboard.hasImage()) {
                        // DataFormat idDataFormatt = new DataFormat("Imgid");
                        Integer idValue =
                                (Integer) dragboard.getContent(idDataFormat); // Ép kiểu Object sang Integer
                        int id = idValue.intValue();
                        ImageView imageView = new ImageView(dragboard.getImage());
                        // HoverEffectUtil.addHoverEffect(imageView);

                        imageView.setLayoutX(event.getX() - ELEMENT_WIDTH / 2);
                        imageView.setLayoutY(event.getY() - ELEMENT_HEIGHT / 2);
                        imageView.setUserData(id);

                        if (event.getGestureSource() instanceof ImageView) {
                            pane.getChildren().remove(draggedImageView);
                        }
                        int id1 = 0, id2 = 0;
                        ImageView override = null;
                        for (Node node : pane.getChildren()) {
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
                                if (inBar[resElement.getElementId()]) { // / sản phẩm đã có từ trước

                                    pane.getChildren().remove(override);
                                    ImageView newImg = new ImageView(resElement.getImageLink());
                                    newImg.setLayoutX(event.getX() - ELEMENT_WIDTH / 2);
                                    newImg.setLayoutY(event.getY() - ELEMENT_HEIGHT / 2);
                                    newImg.setUserData(resElement.getElementId());
                                    pane.getChildren().add(newImg);
                                } else {  /// sản phẩm chưa có, cần hiển thị bảng.

                                    listViewText.getItems().add(resElement.getName());
                                    listViewText.setCellFactory(param -> new CustomListCell(20));
                                    knowledgeBox.setVisible(!knowledgeBox.isVisible());
                                    knowledgeBox.setDisable(false);
                                    knowledgeText.setText(curCom.getDes());
                                    newElement.setImage(new Image(resElement.getImageLink()));
                                    elementName.setText(resElement.getName());


                                    inBar[resElement.getElementId()] = true;
                                    bar.add(resElement.getElementId());

                                    setup();

                                    pane.getChildren().remove(override);

                                    ImageView newImg = new ImageView(resElement.getImageLink());
                                    newImg.setLayoutX(event.getX() - ELEMENT_WIDTH / 2);
                                    newImg.setLayoutY(event.getY() - ELEMENT_HEIGHT / 2);
                                    newImg.setUserData(resElement.getElementId());
                                    pane.getChildren().add(newImg);

                                    checkRes(resElement);
                                }
                            } else {
                                Animation.shakeImageView(imageView);
                                System.out.println("Cant combine");
                                pane.getChildren().add(imageView);


                            }
                        } else {
                            pane.getChildren().add(imageView);
                        }
                    }
                    event.setDropCompleted(true);
                    event.consume();
                });


        //// Sự kiện kéo từ Pane
        pane.setOnDragDetected(
                event -> {

                    draggedImageView = null;
                    for (Node imageView : pane.getChildren()) {
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
                        System.out.println("DRAG FROM PANE ");
                        Dragboard db = draggedImageView.startDragAndDrop(TransferMode.MOVE);
                        int id = (int) draggedImageView.getUserData();
                        ClipboardContent content = new ClipboardContent();
                        content.putImage(draggedImageView.getImage());
                        content.put(idDataFormat, id);
                        db.setContent(content);
                    }
                    event.consume();
                });

        // Xử lý sự kiện khi kéo qua Pane
        pane.setOnDragOver(
                event -> {
                    if (event.getDragboard().hasImage()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }
                    event.consume();
                });

        // Xử lý sự kiện khi kéo qua ListView
        listView.setOnDragOver(
                event -> {
                    if (event.getGestureSource() != listView && event.getDragboard().hasImage()) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    event.consume();
                });

        // Xử lý sự kiện khi thả ImageView vào ListView
        listView.setOnDragDropped(
                event -> {


                    Dragboard dragboard = event.getDragboard();
                    boolean success = false;
                    if (dragboard.hasImage() && draggedImageView != null) {
                        success = true;
                        pane.getChildren().remove(draggedImageView);
                    }
                    event.setDropCompleted(success);
                    event.consume();
                });

        for (ImageView imageView : listView.getItems()) {
            HoverEffectUtil.addHoverEffect(imageView);
        }

        // Apply hover effect to ImageView elements in the Pane
        for (Node imageView : pane.getChildren()) {
            if (imageView instanceof ImageView) {
                HoverEffectUtil.addHoverEffect((ImageView) imageView);
            }
        }
    }

    public boolean overlap(ImageView x, ImageView y) {
        if (x.getLayoutX() <= y.getLayoutX() && y.getLayoutX() <= x.getLayoutX() + ELEMENT_WIDTH
                && x.getLayoutY() <= y.getLayoutY() && y.getLayoutY() <= x.getLayoutY() + ELEMENT_WIDTH)
            return true;
        if (x.getLayoutX() <= y.getLayoutX() && y.getLayoutX() <= x.getLayoutX() + ELEMENT_WIDTH
                && x.getLayoutY() <= y.getLayoutY() + ELEMENT_WIDTH && y.getLayoutY() + ELEMENT_WIDTH <= x.getLayoutY() + ELEMENT_WIDTH)
            return true;
        return false;
    }

    public void onClose() {
        knowledgeBox.setVisible(!knowledgeBox.isVisible());
        knowledgeBox.setDisable(true);
    }


    public void backToMain() {
        try {
            Scene main = sceneUtil.loadScene("/layout/main.fxml");
            sceneUtil.showScene(main);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void backToMainNature() {
        try {
            Scene main = sceneUtil.loadScene("/layout/NatureSelectLevel.fxml");
            sceneUtil.showScene(main);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public abstract void checkRes(Element resElement);

    public void setInitialId() {
        for (int i : initialId) {
            inBar[i] = true;
            bar.add(i);
        }
    }
}
