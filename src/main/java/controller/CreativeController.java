package controller;

import elementarium.models.Combination;
import elementarium.models.Element;
import elementarium.utils.automatic_load_data.AutomaticLoadData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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


public class CreativeController {

    @FXML
    private ListView<ImageView> listView;
    @FXML
    private Pane pane;

    private ImageView draggedImageView;
    private int width = 80;
    private int height = 80;

    DataFormat idDataFormat = new DataFormat("id");

    private List<Element> elements = new ArrayList<Element>();

    private boolean inBar[] = new boolean[1000];

    private AutomaticLoadData data = new AutomaticLoadData();

    private ObservableList<ImageView> imageList = FXCollections.observableArrayList();

    public CreativeController() throws SQLException, ClassNotFoundException {
    }

    public void setup() {

        imageList.clear();
        for (int i = 0; i < elements.size(); i++) {
            Element x = elements.get(i);
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

        elements.add(data.getElementById(1));
        elements.add(data.getElementById(2));
        elements.add(data.getElementById(3));
        elements.add(data.getElementById(4));
        elements.add(data.getElementById(5));

        for (int i = 1; i <= 5; i++) {
            inBar[i] = true;
        }

        setup();

    // Set the onDragDetected event for the ImageView items in the ListView
    listView.setOnDragDetected(
        event -> {
          if (listView.getSelectionModel().getSelectedItem() != null) {
            Dragboard dragboard = listView.startDragAndDrop(TransferMode.COPY);
            // Put the image on the dragboard
            ClipboardContent clipboardContent = new ClipboardContent();
            // System.out.println(listView.getSelectionModel().getSelectedItem().getImage().getWidth());
            clipboardContent.putImage(listView.getSelectionModel().getSelectedItem().getImage());
            int id = (int) listView.getSelectionModel().getSelectedItem().getUserData();
            clipboardContent.put(idDataFormat, id);
            dragboard.setContent(clipboardContent);
            int idValue = (int) dragboard.getContent(idDataFormat);
            System.out.println(idValue);

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
                if (id1 > id2) {
                  int tmp = id1;
                  id1 = id2;
                  id2 = tmp;
                }
                override = img;
                break;
              }
            }
            if (override != null) {
                Combination curCom = null;
                try {
                    curCom = data.getCombinationByE1E2(id1, id2);
                    if (curCom != null) {
                        Element resElement = data.getElementById(curCom.getOutputElement());
                        if (inBar[resElement.getElementId()]) { /// sản phẩm đã có từ trước
                            pane.getChildren().remove(override);
                            ImageView newImg = new ImageView(resElement.getImageLink());
                            newImg.setLayoutX(event.getX() - width / 2);
                            newImg.setLayoutY(event.getY() - height / 2);
                            newImg.setUserData(resElement.getElementId());
                            pane.getChildren().add(newImg);
                        } else {
                            inBar[resElement.getElementId()] = true;
                            elements.add(resElement);
                            setup();
                            pane.getChildren().remove(override);
                            ImageView newImg = new ImageView(resElement.getImageLink());
                            newImg.setLayoutX(event.getX() - width / 2);
                            newImg.setLayoutY(event.getY() - height / 2);
                            newImg.setUserData(resElement.getElementId());
                            pane.getChildren().add(newImg);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
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
        if (x.getLayoutX() <= y.getLayoutX() && y.getLayoutX() <= x.getLayoutX() + width
                && x.getLayoutY() <= y.getLayoutY() + width && y.getLayoutY() + width <= x.getLayoutY() + width)
            return true;
        return false;
    }





}
