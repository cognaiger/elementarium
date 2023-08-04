package elementarium.utils.automatic_load_data;

import elementarium.db_connect.MySQLConnection;
import elementarium.models.Combination;
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
            String imageLink = rs.getString("image_link");
            element = new Element(id, elementName, elementDescription, imageLink);
        }

        // Close the database connection
        mySQLConnection.close();
        return element;
    }

    public static Combination getCombinationByE1E2(int id1, int id2) throws SQLException, ClassNotFoundException {
        mySQLConnection = new MySQLConnection();
        Combination combination = new Combination();
        String sql = "SELECT * FROM combination WHERE element1_id = " + id1 + " and element2_id = " + id2;
        ResultSet rs = mySQLConnection.executeQuery(sql);

        // Create an Element object from the row data
        if (rs.next()) {
            int element1_id = Integer.parseInt(rs.getString("element1_id"));
            int element2_id = Integer.parseInt(rs.getString("element2_id"));
            int result_id = Integer.parseInt(rs.getString("result_element"));
            int combinationId = Integer.parseInt(rs.getString("combinationId"));
            String description = rs.getString("description");

            combination = new Combination(combinationId, element1_id, element2_id, result_id, description);
        }

        // Close the database connection
        mySQLConnection.close();
        return combination;
    }
}
