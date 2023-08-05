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
import javafx.stage.Modality;

import java.io.IOException;

public class LevelController extends DragAndDropWindow{

    @FXML
    private ImageView aimImg;

    @FXML
    private TextField aimText;

    public static int level = 1;

    public int resId;

    SceneUtil sceneUtil = SceneUtil.getInstance();

    public LevelController() {
        super();
        showInstructionsModal();

    }

    private void showInstructionsModal() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/ModalDialog.fxml"));
            Parent root = fxmlLoader.load();

            ModalDialogController modalDialogController = fxmlLoader.getController();
            modalDialogController.setDescription("This is description for level 1");

            // Show the modal dialog and wait for user interaction
            Dialog<String> dialog = new Dialog<>();
            dialog.getDialogPane().setContent(root);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.NEXT);
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setTitle("Instruction for level");

            dialog.showAndWait();
            createLevel();


        } catch (Exception e) {
            e.printStackTrace();
        }
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
                Element e = elements.get(resId - 1);
                aimText.setText(e.getName());
                break;
            }
        }
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
            System.out.println("thanh cong roi");
        }
    }


}
