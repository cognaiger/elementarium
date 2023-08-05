package controller;

import java.sql.SQLException;


public class CreativeController extends DragAndDropWindow {
    public CreativeController() throws SQLException, ClassNotFoundException {
        super();
        for (int i = 1; i <= 5; i++) {
            inBar[i] = true;
            bar.add(i);
        }
    }
}
