package elementarium;

import elementarium.db_connect.MySQLConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDB {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MySQLConnection mySQLConnection = new MySQLConnection();
        String sql = "SELECT element_name FROM element";
        ResultSet rs = mySQLConnection.executeQuery(sql);
        while (rs.next()) {
            String elementName = rs.getString("element_name");
            System.out.println("Element Name: " + elementName);
        }
    }
}
