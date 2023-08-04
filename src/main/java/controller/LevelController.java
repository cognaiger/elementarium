package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import elementarium.utils.SceneUtil;

import java.io.IOException;

public class LevelController {

    SceneUtil sceneUtil = SceneUtil.getInstance();

    @FXML
    private void handleTurnBack() {
        try {
            Scene main = sceneUtil.loadScene("/layout/main.fxml");
            sceneUtil.showScene(main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
