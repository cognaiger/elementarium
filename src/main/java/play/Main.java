package play;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.MainController;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/layout/main.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
    //System.out.println("ok");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
