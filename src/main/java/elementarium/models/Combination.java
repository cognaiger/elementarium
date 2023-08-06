package elementarium.models;

public class Combination {

    private int id;
    private int inputElement1;
    private int inputElement2;
    private int outputElement;
    private String description;

    public Combination() {

    }

    public Combination(int id, int inputElement1, int inputElement2, int outputElement, String description) {
        this.id = id;
        this.inputElement1 = inputElement1;
        this.inputElement2 = inputElement2;
        this.outputElement = outputElement;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInputElement1() {
        return inputElement1;
    }

    public void setInputElement1(int inputElement1) {
        this.inputElement1 = inputElement1;
    }

    public int getInputElement2() {
        return inputElement2;
    }

    public void setInputElement2(int inputElement2) {
        this.inputElement2 = inputElement2;
    }

    public int getOutputElement() {
        return outputElement;
    }

    public void setOutputElement(int outputElement) {
        this.outputElement = outputElement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
