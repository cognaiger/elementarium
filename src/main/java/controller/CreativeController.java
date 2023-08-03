package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class CreativeController {

    /*private List<ImageView> inPane = new ArrayList<>();*/

    @FXML
    private ListView<ImageView> listView;


    @FXML
    private Pane pane;

    public void initialize() {
        // Tạo danh sách các ImageView
        ObservableList<ImageView> imageList = FXCollections.observableArrayList();

        imageList.add(new ImageView("fire.png"));
        imageList.add(new ImageView("water.png"));


        // ...

        // Đặt items cho ListView
        listView.setItems(imageList);

        // Đặt cell factory cho ListView
        listView.setCellFactory(new Callback<ListView<ImageView>, ListCell<ImageView>>() {
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
                        }
                    }
                };
            }
        });

        // Set the onDragDetected event for the ImageView items in the ListView
        listView.setOnDragDetected(
                event -> {
                    if (listView.getSelectionModel().getSelectedItem() != null) {
                        Dragboard dragboard = listView.startDragAndDrop(TransferMode.COPY);

                        // Put the image on the dragboard
                        ClipboardContent clipboardContent = new ClipboardContent();
                        //System.out.println(listView.getSelectionModel().getSelectedItem().getImage().getWidth());
                        clipboardContent.putImage(
                                listView.getSelectionModel().getSelectedItem().getImage());
                        dragboard.setContent(clipboardContent);
                    }
                    event.consume();
                });

        // Set the onDragOver event for the pane to accept the drag
        pane.setOnDragOver(event -> {
            if (event.getGestureSource() != pane && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        // Set the onDragDropped event for the pane to handle the drop
        pane.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            if (dragboard.hasImage()) {
                ImageView imageView = new ImageView(dragboard.getImage());
                imageView.setLayoutX(event.getX() - 25);
                imageView.setLayoutY(event.getY() -25);
                pane.getChildren().add(imageView);
            }
            event.setDropCompleted(true);
            event.consume();
        });
    }



}
