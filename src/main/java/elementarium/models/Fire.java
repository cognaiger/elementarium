package elementarium.models;

public class Fire extends Element {
    public Fire(Element e) {
        this.elementId = e.getElementId();
        this.name = e.getName();
        this.description = e.getDescription();
        this.imageLink = e.getImageLink();
        this.canCombine = e.isCanCombine();
    }

}
