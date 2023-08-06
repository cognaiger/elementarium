package controller;

import elementarium.utils.SceneUtil;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;

public class HistoryLevelController {

    SceneUtil sceneUtil = SceneUtil.getInstance();

    @FXML
    ImageView myImageView1;
    @FXML
    ImageView myImageView2;
    @FXML
    ImageView myImageView3;
    @FXML
    ImageView myImageView4;

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
    void onMouseHover4() {
        // Add the hover effect here, for example, changing the opacity
        scaleBiggerImageView(myImageView4);
    }

    @FXML
    void onMouseExit4() {
        // Add the effect when the mouse exits the ImageView (optional)
        scaleNormalImageView(myImageView4);
    }


    @FXML
    private void handleTurnBack() {
        try {
            Scene main = sceneUtil.loadScene("/layout/Main.fxml");
            sceneUtil.showScene(main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void selectLevel1() {
        try {
            LevelController.level = 1;
            Scene level1 = sceneUtil.loadScene("/layout/Level1His.fxml");
            sceneUtil.showScene(level1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void selectLevel2() {
        try {
            LevelController.level = 2;
            Scene level2 = sceneUtil.loadScene("/layout/Level1His.fxml");
            sceneUtil.showScene(level2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void selectLevel3() {
        try {
            LevelController.level = 3;
            Scene level2 = sceneUtil.loadScene("/layout/Level1His.fxml");
            sceneUtil.showScene(level2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void selectLevel4() {
        try {
            LevelController.level = 4;
            Scene level2 = sceneUtil.loadScene("/layout/Level1His.fxml");
            sceneUtil.showScene(level2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
