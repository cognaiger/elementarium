package elementarium.models;

import elementarium.db_connect.MySQLConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Combination {
    private static MySQLConnection connection;

    static {
        try {
            connection = new MySQLConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Element inputElement1;
    private Element inputElement2;
    private Element outputElement;

    public Combination() throws SQLException, ClassNotFoundException {
    }

    public Element getInputElement1() {
        return inputElement1;
    }

    public void setInputElement1(Element inputElement1) {
        this.inputElement1 = inputElement1;
    }

    public Element getInputElement2() {
        return inputElement2;
    }

    public void setInputElement2(Element inputElement2) {
        this.inputElement2 = inputElement2;
    }

    public Element getOutputElement() {
        return outputElement;
    }

    public void setOutputElement(Element outputElement) {
        this.outputElement = outputElement;
    }

    public static boolean checkCombine(int e1_id, int e2_id) throws SQLException {
        String sql = "SELECT * FROM combination WHERE element1_id = "+e1_id+" AND element2_id = "+e2_id;
        // Execute the query and check if a row was returned
        ResultSet rs = connection.executeQuery(sql);
        if (rs.next()) {
            return true;
        }
        return false;
    }
}
