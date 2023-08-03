package elementarium.utils.automatic_load_data;

import elementarium.db_connect.MySQLConnection;
import elementarium.models.Element;


import java.sql.ResultSet;
import java.sql.SQLException;

public class AutomaticLoadData {
    public static MySQLConnection mySQLConnection;

    public AutomaticLoadData() throws SQLException, ClassNotFoundException {

    }

    public static Element getElementById(int id) throws SQLException, ClassNotFoundException {
        mySQLConnection = new MySQLConnection();
        Element element = new Element();
        String sql = "SELECT * FROM element WHERE id = " + id;
        ResultSet rs = mySQLConnection.executeQuery(sql);

        // Create an Element object from the row data
        if (rs.next()) {
            String elementName = rs.getString("element_name");
            String elementDescription = rs.getString("element_description");
            boolean canCombine = rs.getBoolean("can_combine");
            String imageLink = rs.getString("image_link");
            element = new Element(id, elementName, elementDescription, canCombine, imageLink);
        }

        // Close the database connection
        mySQLConnection.close();
        return element;
    }
}
