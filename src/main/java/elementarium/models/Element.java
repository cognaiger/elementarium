package elementarium.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Element {
    protected int elementId;

    protected String name;
    protected String description;
    protected ImageView imageView;
    protected String imageLink;
    protected boolean canCombine;
    protected Era era;
    protected Level level;

    public Element() {
    }

    public Element(int elementId, String name, String description, String imageLink, boolean canCombine, Era era, Level level) {
        this.elementId = elementId;
        this.name = name;
        this.description = description;
        this.imageLink = imageLink;
        this.canCombine = canCombine;
        this.era = era;
        this.level = level;
        this.imageView = new ImageView(new Image(imageLink));
    }

    public Element(int id, String elementName, String elementDescription, boolean canCombine, String imageLink) {
        this.elementId = id;
        this.name = elementName;
        this.description = elementDescription;
        this.imageLink = imageLink;
        this.canCombine = canCombine;
        this.imageView = new ImageView(new Image(imageLink));

        // this.image = new Image(imageLink);
    }

    public Element(Element e) {
        this.elementId = e.getElementId();
        this.name = e.getName();
        this.description = e.getDescription();
        this.imageLink = e.getImageLink();
        this.canCombine = e.isCanCombine();
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
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

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public boolean isCanCombine() {
        return canCombine;
    }

    public void setCanCombine(boolean canCombine) {
        this.canCombine = canCombine;
    }

    public Era getEra() {
        return era;
    }

    public void setEra(Era era) {
        this.era = era;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Element{" +
                "elementId=" + elementId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageView=" + imageView +
                ", imageLink='" + imageLink + '\'' +
                ", canCombine=" + canCombine +
                ", era=" + era +
                ", level=" + level +
                '}';
    }
}