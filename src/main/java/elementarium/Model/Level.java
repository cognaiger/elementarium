package elementarium.Model;

public class Level {
    private int id;
    private String name;
    private String description;
    private int required_scored;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRequired_scored() {
        return required_scored;
    }

    public void setRequired_scored(int required_scored) {
        this.required_scored = required_scored;
    }
}
