package play;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import elementarium.utils.SceneUtil;

public class Main extends Application {

    SceneUtil sceneUtil = SceneUtil.getInstance();

    @Override
    public void start(Stage stage) throws Exception {
        sceneUtil.setPrimaryStage(stage);
        Scene main = sceneUtil.loadScene("/layout/Main.fxml");

        stage.setTitle("ELEMENTARIUM");
        stage.setScene(main);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
