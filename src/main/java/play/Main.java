package play;
import elementarium.models.Element;
import elementarium.models.Question;
import elementarium.models.Result;
import elementarium.utils.automatic_load_data.AutomaticLoadData;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import elementarium.utils.SceneUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    SceneUtil sceneUtil = SceneUtil.getInstance();
    protected static List<Element> elements = new ArrayList<Element>();
    protected static Result[][] comRes = new Result[100][100];
    protected static List<Question> questions = new ArrayList<Question>();

    public static List<Element> getElements() {
        return elements;
    }

    public static Result[][] getComRes() {
        return comRes;
    }

    public static List<Question> getQuestions() {
        return questions;
    }

    @Override
    public void start(Stage stage) throws Exception {
        sceneUtil.setPrimaryStage(stage);
        Scene start = sceneUtil.loadScene("/layout/GameStart.fxml");
        getDatabase();
        stage.setTitle("ELEMENTARIUM");
        stage.setScene(start);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void getDatabase() throws SQLException, ClassNotFoundException {
        comRes = AutomaticLoadData.getCombinations(); /// các phản ứng
        elements = AutomaticLoadData.getAllElements(); /// tất cả các element
        questions = AutomaticLoadData.getQuestions();
    }
}
