package elementarium.models;

public class Question {
    private String question;
    private String correctAns;
    private String wrongAns1;
    private String wrongAns2;
    private String wrongAns3;

    public Question(String question, String correctAns, String wrongAns1, String wrongAns2, String wrongAns3) {
        this.question = question;
        this.correctAns = correctAns;
        this.wrongAns1 = wrongAns1;
        this.wrongAns2 = wrongAns2;
        this.wrongAns3 = wrongAns3;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public String getWrongAns1() {
        return wrongAns1;
    }

    public String getWrongAns2() {
        return wrongAns2;
    }

    public String getWrongAns3() {
        return wrongAns3;
    }
}
