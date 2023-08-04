package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import play.CreativeGame;
import play.Game;
import elementarium.utils.SceneUtil;

public class MainController {
    @FXML
    private Button creativeButton;

    @FXML
    private Button historyButton;

    @FXML
    private Button natureButton;

    @FXML
    private Button chemistryButton;

    CreativeGame game = new CreativeGame();

    SceneUtil sceneUtil = SceneUtil.getInstance();




    @FXML
    public void creativeGame() {
//        try {
//            Stage currentStage = (Stage) creativeButton.getScene().getWindow();
//            // Đặt lại Scene của Stage với giao diện của Game
//            game.start(currentStage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

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
            Scene historyScene = sceneUtil.loadScene("/layout/SelectLevel.fxml");
            sceneUtil.showScene(historyScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void natureGame() {
        try {
            Game game = new Game();
            Stage currentStage = (Stage) natureButton.getScene().getWindow();
            // Đặt lại Scene của Stage với giao diện của Game
            game.start(currentStage);
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
            Scene chemistry = sceneUtil.loadScene("/layout/Chemistry.fxml");
            sceneUtil.showScene(chemistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
