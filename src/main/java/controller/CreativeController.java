package controller;

import elementarium.utils.InitialNumberElement;
import elementarium.utils.automatic_load_data.AutomaticLoadData;
import java.sql.SQLException;


public class CreativeController extends DragAndDropWindow {
    public CreativeController() throws SQLException, ClassNotFoundException {
        super();
        initialNumberElements = InitialNumberElement.CREATIVE_MODE;
        elements = AutomaticLoadData.getAllElements();
    }
}
