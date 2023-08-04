package controller;
import java.sql.SQLException;

public class CreativeController extends DragAndDropWindow {

    public CreativeController() throws SQLException, ClassNotFoundException {
        comRes = data.getCombinations();
        elements = data.getAllElements();
    }
}
