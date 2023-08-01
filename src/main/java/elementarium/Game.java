package elementarium;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Game extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 800, 600);

        stage.setScene(scene);
        stage.setTitle("Elementarium");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}