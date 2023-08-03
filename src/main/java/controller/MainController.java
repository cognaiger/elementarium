package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import play.CreativeGame;
import play.Game;

public class MainController {
    @FXML
    private Button creativeButton;

    @FXML
    private Button historyButton;

    @FXML
    private Button natureButton;

    @FXML
    private Button chemistryButton;

    @FXML
    public void creativeGame() {
        try {
            CreativeGame game = new CreativeGame();
            Stage currentStage = (Stage) creativeButton.getScene().getWindow();
            // Đặt lại Scene của Stage với giao diện của Game
            game.start(currentStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void historyGame() {
        try {
            Game game = new Game();
            Stage currentStage = (Stage) historyButton.getScene().getWindow();
            // Đặt lại Scene của Stage với giao diện của Game
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/SelectLevel.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void natureGame() {
        try {
            Game game = new Game();
            Stage currentStage = (Stage) natureButton.getScene().getWindow();
            // Đặt lại Scene của Stage với giao diện của Game
            game.start(currentStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void chemistryGame() {
        try {
            Game game = new Game();
            Stage currentStage = (Stage) chemistryButton.getScene().getWindow();
            // Đặt lại Scene của Stage với giao diện của Game
            game.start(currentStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
