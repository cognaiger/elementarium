package elementarium.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Element {
    protected int elementId;
    protected String name;
    protected String description;
    protected String imageLink;

    public Element() {

    }

    public Element(int elementId, String name, String description, String imageLink) {
        this.elementId = elementId;
        this.name = name;
        this.description = description;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}