package controller;

import elementarium.models.Element;
import elementarium.utils.SceneUtil;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Arrays;

public class NatureLevelController extends DragAndDropWindow {


    public static int level = 1;

    public int resId;
    @FXML
    public Pane helpTab;

    SceneUtil sceneUtil = SceneUtil.getInstance();




    public NatureLevelController() {
        super();
        createLevel();

    }

    public void setInitialId() {
        for (int i : initialId) {
            inBar[i] = true;
            bar.add(i);
        }
    }

    void createLevel() {
        switch (level) {
            case 1: {
                resId = 23;
                initialId.addAll(Arrays.asList(17, 1, 2, 5, 13, 68, 70));
                setInitialId();
                break;
            }
            case 2: {
                resId = 79;
                initialId.addAll(Arrays.asList(17, 1, 2, 5, 13, 77, 78));
                setInitialId();
                break;
            }
            case 3: {
                resId = 75;
                initialId.addAll(Arrays.asList(17, 1, 71, 5, 74, 73, 78));
                setInitialId();
                break;
            }

        }
    }

    @FXML
    public void nextLevel() throws IOException {
        congraBox.setDisable(true);
        congraBox.setVisible(false);
        level++;
        switchLayout();
    }

    @Override
    public void backToMain() {
        try {
            Scene main = sceneUtil.loadScene("/layout/NatureSelectLevel.fxml");
            sceneUtil.showScene(main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void checkRes(Element resElement) {
        if (resElement.getElementId() == resId) {
            congraBox.setDisable(false);
            congraBox.setVisible(true);
        }
    }

    public void switchLayout() throws IOException {
        switch (level) {
            case 1: {
                createLevel();
                Scene level1 = sceneUtil.loadScene("/layout/NatureLevel1.fxml");
                sceneUtil.showScene(level1);
                break;
            }
            case 2: {
                createLevel();
                Scene level2 = sceneUtil.loadScene("/layout/NatureLevel2.fxml");
                sceneUtil.showScene(level2);
                break;
            }
            case 3: {
                createLevel();
                Scene level3 = sceneUtil.loadScene("/layout/NatureLevel3.fxml");
                sceneUtil.showScene(level3);
                break;
            }
            case 4: {
                createLevel();
                QuestionHistoryController.questionNum = 1;
                Scene q = sceneUtil.loadScene("/layout/QuestionNature.fxml");
                sceneUtil.showScene(q);
                break;
            }

        }
    }

    public void closeHelpTab() {
        helpTab.setDisable(true);
        helpTab.setVisible(false);
    }


    public void openHelpTab() {
        helpTab.setVisible(true);
        helpTab.setDisable(false);
    }
}
