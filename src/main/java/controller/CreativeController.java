package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class CreativeController {
    private List<ImageView> sideBar = new ArrayList<>();

    /*private List<ImageView> inPane = new ArrayList<>();*/

    @FXML
    private ListView<ImageView> listOfElement;

    @FXML
    private Pane creativePane;

    public CreativeController() {
        Image originalImage1 = new Image("fire.png");
        Image originalImage2 = new Image("water.png");

        // Create the image views for the original images
        ImageView i1 = new ImageView(originalImage1);
        ImageView i2 = new ImageView(originalImage2);

        // Set the properties of the image views
        sideBar.add(i1);
        sideBar.add(i2);
    }

    @FXML
    public void initialize() {
        for (ImageView x: sideBar) {
            x.setFitWidth(50);
            x.setFitHeight(50);
//            x.setOnMousePressed(event -> onMousePressed(event, x));
//            x.setOnMouseDragged(event -> onMouseDragged(event, x));
//            x.setOnMouseReleased(event -> onMouseReleased(event, x));
            listOfElement.getItems().add(x);
        }
    }

}
