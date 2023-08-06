package controller;

import elementarium.utils.SceneUtil;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStartController {

    SceneUtil sceneUtil = SceneUtil.getInstance();
    @FXML
    private Button startBtn;
    @FXML
    private Button quitBtn;

    @FXML
    public void startGame() {
        try {
            Scene main = sceneUtil.loadScene("/layout/Main.fxml");
            sceneUtil.showScene(main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exitGame() {
        Stage curStage = sceneUtil.getPrimaryStage();
        curStage.close();
    }

    @FXML
    private void onMouseHover1() {
        startBtn.setStyle("-fx-background-radius: 20; -fx-border-color: #005fb4; -fx-border-radius: 20; " +
                "-fx-padding: 10 20; -fx-background-color: F1C93B;");
    }

    @FXML
    private void onMouseExit1() {
        startBtn.setStyle("-fx-background-radius: 20; -fx-border-color: #005fb4; -fx-border-radius: 20; " +
                "-fx-padding: 10 20; -fx-background-color: F4D160;");
    }

    @FXML
    private void onMouseHover2() {
        quitBtn.setStyle("-fx-background-radius: 20; -fx-border-color: #005fb4; -fx-border-radius: 20; " +
                "-fx-padding: 10 20; -fx-background-color: F1C93B;");
    }

    @FXML
    private void onMouseExit2() {
        quitBtn.setStyle("-fx-background-radius: 20; -fx-border-color: #005fb4; -fx-border-radius: 20; " +
                "-fx-padding: 10 20; -fx-background-color: F4D160;");
    }
}
