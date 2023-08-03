package play;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import controller.MainController;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/layout/main.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            stage.setTitle("ELEMENTARIUM");
            stage.setScene(scene);
            stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
