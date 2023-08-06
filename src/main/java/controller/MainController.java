package controller;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import play.CreativeGame;
import play.Game;
import elementarium.utils.SceneUtil;

public class MainController {




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
    public void creativeGame() {

        try {
            Scene creativeScene = sceneUtil.loadScene("/layout/creativeGame.fxml");
            sceneUtil.showScene(creativeScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void historyGame() {
        try {
            Scene historyScene = sceneUtil.loadScene("/layout/HistorySelectLevel.fxml");
            sceneUtil.showScene(historyScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void natureGame() {
        try {
            Scene natureScene = sceneUtil.loadScene("/layout/NatureSelectLevel.fxml");
            sceneUtil.showScene(natureScene);
            // Đặt lại Scene của Stage với giao diện của Game

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void chemistryGame() {
        try {
            Game game = new Game();
            Stage currentStage = sceneUtil.getPrimaryStage();
            game.start(currentStage);
            Scene chemistry = sceneUtil.loadScene("/layout/SelectedChemistry.fxml");
            sceneUtil.showScene(chemistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
