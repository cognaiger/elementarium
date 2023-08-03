package play;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import controller.MainController;
import util.SceneUtil;

public class Main extends Application {

    SceneUtil sceneUtil = SceneUtil.getInstance();

    @Override
    public void start(Stage stage) throws Exception {
        sceneUtil.setPrimaryStage(stage);
        Scene main = sceneUtil.loadScene("/layout/main.fxml");

        stage.setTitle("ELEMENTARIUM");
        stage.setScene(main);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
