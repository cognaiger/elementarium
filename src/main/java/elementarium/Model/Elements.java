package elementarium.Model;

public abstract class Elements {
    protected int elementId;

    protected int name;
    protected String description;
    protected String image;
    protected boolean canCombine;
    protected int eraId;
    protected int levelId;

    public Elements() {
    }

    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isCanCombine() {
        return canCombine;
    }

    public void setCanCombine(boolean canCombine) {
        this.canCombine = canCombine;
    }

    public int getEraId() {
        return eraId;
    }

    public void setEraId(int eraId) {
        this.eraId = eraId;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    @Override
    public String toString() {
        return "Elements{" +
                "elementId=" + elementId +
                ", name=" + name +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", canCombine=" + canCombine +
                ", eraId=" + eraId +
                ", levelId=" + levelId +
                '}';
    }
}




