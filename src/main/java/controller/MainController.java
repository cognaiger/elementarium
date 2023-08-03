package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import play.Game;

public class MainController {
    @FXML
    private Button startButton;
    @FXML
    protected void onButtonClick() {
        try {

            Game game = new Game();
            // Tạo FXMLLoader để tải file FXML của class Game
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("GameScene.fxml"));
            //Parent gameRoot = loader.load();

            // Lấy Stage hiện tại của Button
            Stage currentStage = (Stage) startButton.getScene().getWindow();
            // Đặt lại Scene của Stage với giao diện của Game
            game.start(currentStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
