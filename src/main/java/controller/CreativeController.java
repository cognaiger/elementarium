package controller;


import elementarium.models.Element;


public class CreativeController extends DragAndDropWindow {


    public CreativeController() {
        super();
        for (int i = 1; i <= 5; i++) {
            inBar[i] = true;
            bar.add(i);
        }
    }

    @Override
    public void checkRes(Element resElement) {

    }
}