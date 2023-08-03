package elementarium.models;

public class Water extends Element {
    public Water(Element e) {
        this.elementId = e.getElementId();
        this.name = e.getName();
        this.description = e.getDescription();
        this.imageLink = e.getImageLink();
        this.canCombine = e.isCanCombine();
    }
}
