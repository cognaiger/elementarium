package controller;

import elementarium.utils.SceneUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModalDialogController {
    SceneUtil sceneUtil = SceneUtil.getInstance();
    Stage dialogStage = new Stage();

    @FXML
    private Label description;

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void showAndWait() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/ModalDialog.fxml"));
            Parent root = fxmlLoader.load();

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
    private void proceed() {
        System.out.println("close");
        dialogStage.close();
    }
}
