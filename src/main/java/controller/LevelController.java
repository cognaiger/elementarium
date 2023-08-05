package controller;

import elementarium.utils.SceneUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;

public class LevelController {

    @FXML
    private ImageView earth;
    @FXML
    private ImageView fire;
    @FXML
    private ListView<ImageView> listView;
    SceneUtil sceneUtil = SceneUtil.getInstance();

    @FXML
    private void initialize() {
            // Show the modal dialog when the application starts
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

            System.out.println("Selected level");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
