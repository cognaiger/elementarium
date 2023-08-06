package controller;


import elementarium.models.Element;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.sql.SQLException;



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