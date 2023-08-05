package controller;
import elementarium.utils.InititialNumberElement;

import java.sql.SQLException;

public class CreativeController extends DragAndDropWindow {

    public CreativeController() throws SQLException, ClassNotFoundException {
        initialNumberElement = InititialNumberElement.CREATIVE_MODE;
        comRes = data.getCombinations();
        elements = data.getAllElements();
    }
}
