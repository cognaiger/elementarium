package controller;

import elementarium.models.Element;
import elementarium.models.Result;
import elementarium.utils.animation.Animation;
import elementarium.utils.automatic_load_data.AutomaticLoadData;
import elementarium.utils.sound.SoundUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DragAndDropWindow {
    protected final SoundUtil soundUtil = new SoundUtil();
    @FXML
    protected ListView<ImageView> listView;
    @FXML
    protected Pane pane;
    @FXML
    protected Pane knowledgeBox;
    @FXML
    protected Button closeBox;
    @FXML
    protected TextField knowledgeText;
    @FXML
    protected ImageView newElement;
    protected ImageView draggedImageView;
    protected int width = 80;
    protected int height = 80;

    protected DataFormat idDataFormat = new DataFormat("id");

    protected List<Element> elements = new ArrayList<Element>();
    protected Result[][] comRes = new Result[100][100];

    protected List<Integer> bar = new ArrayList<Integer>();

    protected boolean[] inBar = new boolean[1000];

    protected AutomaticLoadData data = new AutomaticLoadData();

    protected ObservableList<ImageView> imageList = FXCollections.observableArrayList();

    public DragAndDropWindow() throws SQLException, ClassNotFoundException {

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

        for (int i = 1; i <= 5; i++) {
            inBar[i] = true;
            bar.add(i);
        }

        setup();

        // Set the onDragDetected event for the ImageView items in the ListView
        listView.setOnDragDetected(
                event -> {
                    if (listView.getSelectionModel().getSelectedItem() != null) {
                        soundUtil.playSelectSoundEffect();
                        Dragboard dragboard = listView.startDragAndDrop(TransferMode.COPY);
                        // Put the image on the dragboard
                        ClipboardContent clipboardContent = new ClipboardContent();
                        // System.out.println(listView.getSelectionModel().getSelectedItem().getImage().getWidth());
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
                    soundUtil.playDropSoundEffect();
                    Dragboard dragboard = event.getDragboard();
                    if (dragboard.hasImage()) {
                        // DataFormat idDataFormatt = new DataFormat("Imgid");
                        Integer idValue =
                                (Integer) dragboard.getContent(idDataFormat); // Ép kiểu Object sang Integer
                        int id = idValue.intValue();
                        ImageView imageView = new ImageView(dragboard.getImage());
                        imageView.setLayoutX(event.getX() - width / 2);
                        imageView.setLayoutY(event.getY() - height / 2);
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

                                    soundUtil.playCanCombineSoundEffect();

                                    pane.getChildren().remove(override);
                                    ImageView newImg = new ImageView(resElement.getImageLink());
                                    newImg.setLayoutX(event.getX() - width / 2);
                                    newImg.setLayoutY(event.getY() - height / 2);
                                    newImg.setUserData(resElement.getElementId());
                                    pane.getChildren().add(newImg);
                                } else {  /// sản phẩm chưa có, cần hiển thị bảng.

                                    soundUtil.playNewElementSoundEffect();

                                    knowledgeBox.setVisible(!knowledgeBox.isVisible());
                                    knowledgeBox.setDisable(false);

                                    knowledgeText.setText(curCom.getDes());
                                    newElement.setImage(new Image(resElement.getImageLink()));

                                    inBar[resElement.getElementId()] = true;
                                    bar.add(resElement.getElementId());

                                    setup();

                                    pane.getChildren().remove(override);

                                    ImageView newImg = new ImageView(resElement.getImageLink());
                                    newImg.setLayoutX(event.getX() - width / 2);
                                    newImg.setLayoutY(event.getY() - height / 2);
                                    newImg.setUserData(resElement.getElementId());
                                    pane.getChildren().add(newImg);
                                }
                            } else {
                                Animation.shakeImageView(imageView);
                                System.out.println("Cant combine");
                                pane.getChildren().add(imageView);
                                soundUtil.playCanNotCombineSoundEffect();
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
                    soundUtil.playSelectSoundEffect();
                    draggedImageView = null;
                    for (Node imageView : pane.getChildren()) {
                        ImageView tmp = (ImageView) imageView;
                        if (tmp.getLayoutX() <= event.getX()
                                && event.getX() <= tmp.getLayoutX() + width
                                && tmp.getLayoutY() <= event.getY()
                                && event.getY() <= tmp.getLayoutY() + height) {
                            draggedImageView = tmp;
                            break;
                        }
                    }
                    if (draggedImageView != null) {
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
                    soundUtil.playBackToListViewSoundEffect();
                    Dragboard dragboard = event.getDragboard();
                    boolean success = false;
                    if (dragboard.hasImage() && draggedImageView != null) {
                        success = true;
                        pane.getChildren().remove(draggedImageView);
                    }
                    event.setDropCompleted(success);
                    event.consume();
                });
    }

    public boolean overlap(ImageView x, ImageView y) {
        if (x.getLayoutX() <= y.getLayoutX() && y.getLayoutX() <= x.getLayoutX() + width
                && x.getLayoutY() <= y.getLayoutY() && y.getLayoutY() <= x.getLayoutY() + width)
            return true;
        return x.getLayoutX() <= y.getLayoutX() && y.getLayoutX() <= x.getLayoutX() + width
                && x.getLayoutY() <= y.getLayoutY() + width && y.getLayoutY() + width <= x.getLayoutY() + width;
    }


    public void onClose() {
        knowledgeBox.setVisible(!knowledgeBox.isVisible());
        knowledgeBox.setDisable(true);
    }
}
