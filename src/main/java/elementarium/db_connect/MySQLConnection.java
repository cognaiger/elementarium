package elementarium.db_connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    private String url = "jdbc:mysql://ur2p7drgo7axeexj:M60WskmDU7tV9GTNeH9W@b1uzxerttnqdid0ldvcr-mysql.services.clever-cloud.com:3306/b1uzxerttnqdid0ldvcr";
    private String user = "ur2p7drgo7axeexj";
    private String password = "M60WskmDU7tV9GTNeH9W";


    public MySQLConnection() throws SQLException, ClassNotFoundException {
        // Load the MySQL Connector/J JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Connect to the MySQL database
        conn = DriverManager.getConnection(url, user, password);

        // Create a statement object
        stmt = conn.createStatement();
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        // Execute a SQL query
        rs = stmt.executeQuery(sql);
        return rs;
    }

    public void close() throws SQLException {
        // Close the result set, statement, and connection
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
    }
}