package controller;

import elementarium.utils.SceneUtil;
import javafx.fxml.FXML;
import javafx.scene.Scene;

import java.io.IOException;

public class ChemistryChooseLevelController {
    SceneUtil sceneUtil = SceneUtil.getInstance();

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
            // controller la ChemLevelController
            ChemLevelController.level = 1;
            Scene level1 = sceneUtil.loadScene("/layout/ChemistryGame.fxml");

            sceneUtil.showScene(level1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void selectLevel2() {
        try {
            ChemLevelController.level = 2;
            Scene level2 = sceneUtil.loadScene("/layout/ChemistryGame.fxml");
            sceneUtil.showScene(level2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void selectLevel3() {
        try {
            ChemLevelController.level = 3;
            Scene level3 = sceneUtil.loadScene("/layout/ChemistryGame.fxml");
            sceneUtil.showScene(level3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void selectLevel4() {
        try {
            ChemLevelController.level = 4;
            Scene level2 = sceneUtil.loadScene("/layout/ChemistryGame.fxml");
            sceneUtil.showScene(level2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
