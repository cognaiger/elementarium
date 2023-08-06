package controller;


import elementarium.utils.SceneUtil;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.IOException;

public class NatureSelectLevelController {
    SceneUtil sceneUtil = SceneUtil.getInstance();

    @FXML
    private ImageView backButton;

    @FXML
    private ImageView myImageView1;
    @FXML
    private ImageView myImageView2;

    @FXML
    private ImageView myImageView3;


    public void scaleBiggerImageView(ImageView imageView) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), imageView);
        scaleTransition.setToX(1.1); // Scale to 120% in the x-axis
        scaleTransition.setToY(1.1); // Scale to 120% in the y-axis
        scaleTransition.play();
    }

    public void scaleNormalImageView(ImageView imageView) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), imageView);
        scaleTransition.setToX(1.0); // Scale to 120% in the x-axis
        scaleTransition.setToY(1.0); // Scale to 120% in the y-axis
        scaleTransition.play();
    }

    @FXML
    public void natureGameLevel1() {
        try {
            Scene natureScene = sceneUtil.loadScene("/layout/NatureLevel1.fxml");
            sceneUtil.showScene(natureScene);
            // Đặt lại Scene của Stage với giao diện của Game
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void natureGameLevel2() {
        try {
            Scene natureScene = sceneUtil.loadScene("/layout/NatureLevel2.fxml");
            sceneUtil.showScene(natureScene);
            // Đặt lại Scene của Stage với giao diện của Game
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onMouseHover1() {
        // Add the hover effect here, for example, changing the opacity
        scaleBiggerImageView(myImageView1);
    }

    @FXML
    void onMouseExit1() {
        // Add the effect when the mouse exits the ImageView (optional)
        scaleNormalImageView(myImageView1);
    }

    @FXML
    void onMouseHover2() {
        // Add the hover effect here, for example, changing the opacity
        scaleBiggerImageView(myImageView2);
    }

    @FXML
    void onMouseExit2() {
        // Add the effect when the mouse exits the ImageView (optional)
        scaleNormalImageView(myImageView2);
    }

    @FXML
    void onMouseHover3() {
        // Add the hover effect here, for example, changing the opacity
        scaleBiggerImageView(myImageView3);
    }

    @FXML
    void onMouseExit3() {
        // Add the effect when the mouse exits the ImageView (optional)
        scaleNormalImageView(myImageView3);
    }

    @FXML
    private void handleTurnBack() {
        try {
            Scene main = sceneUtil.loadScene("/layout/main.fxml");
            sceneUtil.showScene(main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onMouseHoverButton() {
        scaleBiggerImageView(backButton);
    }

    @FXML
    void onMouseExitBackButton() {
        scaleNormalImageView(backButton);
    }

    public void natureGameLevel3(MouseEvent mouseEvent) {
        try {
            Scene natureScene = sceneUtil.loadScene("/layout/NatureLevel3.fxml");
            sceneUtil.showScene(natureScene);
            // Đặt lại Scene của Stage với giao diện của Game
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
