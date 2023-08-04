package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import elementarium.utils.SceneUtil;

import java.io.IOException;

public class HistoryLevelController {

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

    @FXML
    private void selectLevel1() {
        try {
            Scene level1 = sceneUtil.loadScene("/layout/Level1His.fxml");
            sceneUtil.showScene(level1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void selectLevel2() {
        try {
            Scene level2 = sceneUtil.loadScene("/layout/Level1His.fxml");
            sceneUtil.showScene(level2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void selectLevel3() {
        try {
            Scene level2 = sceneUtil.loadScene("/layout/Level1His.fxml");
            sceneUtil.showScene(level2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void selectLevel4() {
        try {
            Scene level2 = sceneUtil.loadScene("/layout/Level1His.fxml");
            sceneUtil.showScene(level2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}