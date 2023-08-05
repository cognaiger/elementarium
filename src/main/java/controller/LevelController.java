package controller;

import elementarium.models.Element;
import elementarium.utils.SceneUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;

import java.io.IOException;

public class LevelController extends DragAndDropWindow {


    public static int level = 1;

    public int resId;

    SceneUtil sceneUtil = SceneUtil.getInstance();

    @FXML
    Pane congraBox;


    public LevelController() {
        super();
        createLevel();

    }

    void createLevel() {
        switch (level){
            case 1:
            {
                inBar[1] = true;
                bar.add(1);
                inBar[15] = true;
                bar.add(15);
                resId = 14;
                break;
            }
            case 2:
            {
                inBar[1] = true;
                bar.add(1);
                inBar[2] = true;
                bar.add(2);
                inBar[3] = true;
                bar.add(3);
                inBar[4] = true;
                bar.add(4);
                inBar[5] = true;
                bar.add(5);
                resId = 19;
                break;
            }
            case 3:{
                inBar[1] = true;
                bar.add(1);
                inBar[17] = true;
                bar.add(17);
                resId = 23;
                break;
            }
            case 4: {
                inBar[1] = true;
                bar.add(1);
                inBar[9] = true;
                bar.add(9);
                resId = 26;
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
            Scene main = sceneUtil.loadScene("/layout/HistorySelectLevel.fxml");
            sceneUtil.showScene(main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkRes(Element resElement) {
        if (resElement.getElementId() == resId) {
            System.out.println("ok");
            congraBox.setDisable(false);
            congraBox.setVisible(true);
        }
    }

    public void switchLayout() throws IOException {
        switch (level){
            case 1:
            {
                Scene level1 = sceneUtil.loadScene("/layout/Level1His.fxml");
                sceneUtil.showScene(level1);
                break;
            }
            case 2: {
                Scene level2 = sceneUtil.loadScene("/layout/Level2His.fxml");
                sceneUtil.showScene(level2);
                break;
            }
            case 3: {
                Scene level3 = sceneUtil.loadScene("/layout/Level3His.fxml");
                sceneUtil.showScene(level3);
                break;
            }
            case 4: {
                Scene level4 = sceneUtil.loadScene("/layout/Level4His.fxml");
                sceneUtil.showScene(level4);
                break;
            }
        }
    }

}
