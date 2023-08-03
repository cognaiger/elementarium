package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.SceneUtil;

import java.io.IOException;

public class LevelController {

    SceneUtil sceneUtil = SceneUtil.getInstance();

    @FXML
    private ImageView turnBack;

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
