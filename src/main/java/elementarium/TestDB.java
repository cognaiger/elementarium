package elementarium;

import elementarium.DatabaseConnect.MySQLConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDB {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MySQLConnection mySQLConnection = new MySQLConnection();
        String sql = "SELECT COUNT(*) FROM element";
        ResultSet rs = mySQLConnection.executeQuery(sql);
        rs.next();
        int count = rs.getInt(1);
        System.out.println(count);
    }
}
