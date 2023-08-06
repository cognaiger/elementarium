package elementarium.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneUtil {

    private static SceneUtil instance;

    private Stage primaryStage;

    private SceneUtil() {

    }

    public static SceneUtil getInstance() {
        if (instance == null) {
            instance = new SceneUtil();
        }
        return instance;
    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showScene(Scene scene) {
        primaryStage.setScene(scene);
    }

    // return scene 900x600 from fxml file
    public Scene loadScene(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();


        return new Scene(root, 1300, 700);

    }
}
