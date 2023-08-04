package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ChemistryController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private ImageView Temperature;

    @FXML
    private ImageView hydroImg;

    @FXML
    private ImageView natri;

    @FXML
    private ImageView oxy;

    @FXML
    private ImageView water;

    @FXML
    private AnchorPane anchorPane = new AnchorPane();

    private double dragStartX, dragStartY;
    private double initialTranslateX, initialTranslateY;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hydroImg.setOnMousePressed(this::onMousePressed);
        hydroImg.setOnMouseDragged(this::onMouseDragged);
        hydroImg.setOnMouseReleased(this::onMouseReleased);

        Temperature.setOnMousePressed(this::onMousePressed);
        Temperature.setOnMouseDragged(this::onMouseDragged);
        Temperature.setOnMouseReleased(this::onMouseReleased);

        natri.setOnMousePressed(this::onMousePressed);
        natri.setOnMouseDragged(this::onMouseDragged);
        natri.setOnMouseReleased(this::onMouseReleased);

        water.setOnMousePressed(this::onMousePressed);
        water.setOnMouseDragged(this::onMouseDragged);
        water.setOnMouseReleased(this::onMouseReleased);

        oxy.setOnMousePressed(this::onMousePressed);
        oxy.setOnMouseDragged(this::onMouseDragged);
        oxy.setOnMouseReleased(this::onMouseReleased);


    }
    private void onMousePressed(MouseEvent event) {
        // Lưu trữ vị trí khởi đầu của ImageView
        initialTranslateX = ((ImageView) event.getSource()).getTranslateX();
        initialTranslateY = ((ImageView) event.getSource()).getTranslateY();

        // Lưu trữ vị trí khởi đầu của chuột
        dragStartX = event.getSceneX();
        dragStartY = event.getSceneY();
    }

    private void onMouseDragged(MouseEvent event) {
        // Tính toán khoảng cách đã kéo
        double offsetX = event.getSceneX() - dragStartX;
        double offsetY = event.getSceneY() - dragStartY;

        // Cập nhật vị trí của ImageView
        ((ImageView) event.getSource()).setTranslateX(initialTranslateX + offsetX);
        ((ImageView) event.getSource()).setTranslateY(initialTranslateY + offsetY);
    }

    private void onMouseReleased(MouseEvent event) {
        ((ImageView) event.getSource()).setTranslateX(initialTranslateX);
        ((ImageView) event.getSource()).setTranslateY(initialTranslateY);
    }
}
