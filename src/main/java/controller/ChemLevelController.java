package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class ChemLevelController extends Chemistry2Controller {
    public static int level;

    @FXML
    private ImageView resetBtn;

    public ChemLevelController() {
        super();
        System.out.println("ok");
        createLevel();

    }

    void createLevel() {
        System.out.println(level);
        switch (level) {
            case 1: {
                text = "Hãy tạo ra NaOH";
                resId = 61;
                bar.add(65);
                bar.add(58);
                bar.add(56);
                bar.add(60);
                bar.add(54);
                bar.add(56);
                bar.add(58);
                bar.add(60);
                bar.add(57);
                System.out.println("vcl");
                break;
            }
            case 2: {
                text = "Hãy tạo ra Fe3O4";
                resId = 64;
                bar.add(60);
                bar.add(58);
                bar.add(56);
                bar.add(60);
                bar.add(54);
                bar.add(56);
                bar.add(63);
                bar.add(61);
                bar.add(62);
                break;

            }
            case 3: {
                text = "Hãy tạo Na";
                resId = 58;
                bar.add(60);
                bar.add(59);
                bar.add(57);
                bar.add(60);
                bar.add(62);
                bar.add(56);
                bar.add(63);
                bar.add(61);
                bar.add(64);
                break;
            }
            case 4: {
                text = "Hãy tạo ra Hcl";
                resId = 66;
                bar.add(58);
                bar.add(59);
                bar.add(57);
                bar.add(60);
                bar.add(62);
                bar.add(63);
                bar.add(65);
                bar.add(61);
                bar.add(67);
                break;

            }
        }
    }


    public void resetGame() {
        try {

            Scene main = sceneUtil.loadScene("/layout/ChemistryGame.fxml");
            sceneUtil.showScene(main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backToMain() {
        try {
            Scene main = sceneUtil.loadScene("/layout/SelectedChemistry.fxml");
            sceneUtil.showScene(main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
