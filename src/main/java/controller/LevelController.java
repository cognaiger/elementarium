package controller;

import elementarium.utils.SceneUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LevelController {

    private Stage primaryStage = SceneUtil.getInstance().getPrimaryStage();
    private Stage dialogStage = new Stage();

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

    @FXML
    private void startPlaying() {
        // Handle the "Start" button click to begin the gameplay.
    }
}
