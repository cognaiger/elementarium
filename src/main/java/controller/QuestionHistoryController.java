package controller;

import elementarium.models.Question;
import elementarium.utils.SceneUtil;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import play.Main;

import java.io.IOException;
import java.util.List;

public class QuestionHistoryController {

    public static int questionNum = 1;
    @FXML
    public TextField ans1;
    @FXML
    public TextField ans3;
    @FXML
    public TextField ans2;
    @FXML
    public TextField ans4;
    @FXML
    public Button nextBtn;
    @FXML
    public Button againBtn;
    @FXML
    TextArea questionText;
    List<Question> questionList = Main.getQuestions();
    int correct = 0;
    SceneUtil sceneUtil = SceneUtil.getInstance();


    public QuestionHistoryController() {
        //addQuestion();
    }

    public void initialize() {
        addQuestion();
    }

    void addQuestion() {
        System.out.println(questionNum);
        switch (questionNum) {
            case 1: {
                Question tmp = questionList.get(7);
                System.out.println("vcl1" + tmp.getQuestion());
                correct = 1;
                questionText.setText(tmp.getQuestion());
                ans1.setText(tmp.getCorrectAns());
                ans2.setText(tmp.getWrongAns1());
                ans3.setText(tmp.getWrongAns2());
                ans4.setText(tmp.getWrongAns3());
                break;
            }
            case 2: {
                Question tmp = questionList.get(8);
                System.out.println("vcl2" + tmp.getQuestion());
                correct = 3;
                questionText.setText(tmp.getQuestion());
                ans3.setText(tmp.getCorrectAns());
                ans2.setText(tmp.getWrongAns1());
                ans1.setText(tmp.getWrongAns2());
                ans4.setText(tmp.getWrongAns3());
                break;
            }
            case 3: {
                Question tmp = questionList.get(10);
                System.out.println("vcl3" + tmp.getQuestion());
                correct = 4;
                questionText.setText(tmp.getQuestion());
                ans4.setText(tmp.getCorrectAns());
                ans2.setText(tmp.getWrongAns1());
                ans1.setText(tmp.getWrongAns2());
                ans3.setText(tmp.getWrongAns3());
                break;
            }
        }
    }


    public void choose1() {
        checkAns(1);
    }

    public void choose2() {
        checkAns(2);
    }

    public void choose3() {
        checkAns(3);
    }

    public void choose4() {
        checkAns(4);
    }


    public void nextQuestion() {
        questionNum++;
        try {
            switchTab();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chooseAgain() {
        try {
            switchTab();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void checkAns(int choose) {
        if (choose == correct) {
            nextBtn.setVisible(true);
            nextBtn.setDisable(false);
        } else {
            againBtn.setDisable(false);
            againBtn.setVisible(true);
        }
    }

    void switchTab() throws IOException {
        switch (questionNum) {
            case 1: {
                Scene q = sceneUtil.loadScene("/layout/QuestionHis.fxml");
                sceneUtil.showScene(q);
                break;
            }

            case 2: {
                Scene q = sceneUtil.loadScene("/layout/QuestionHis.fxml");
                sceneUtil.showScene(q);
                break;
            }

            case 3: {
                Scene q = sceneUtil.loadScene("/layout/QuestionHis.fxml");
                sceneUtil.showScene(q);
                break;
            }
            case 4: {
                Scene q = sceneUtil.loadScene("/layout/HistorySelectLevel.fxml");
                sceneUtil.showScene(q);
                break;
            }
        }
    }
}
