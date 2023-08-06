package elementarium.utils.automatic_load_data;

import elementarium.db_connect.MySQLConnection;
import elementarium.models.Element;
import elementarium.models.Question;
import elementarium.models.Result;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutomaticLoadData {
    public static MySQLConnection mySQLConnection;

    public AutomaticLoadData() throws SQLException, ClassNotFoundException {

    }

    public static List<Question> getQuestions() throws SQLException, ClassNotFoundException {
        mySQLConnection = new MySQLConnection();
        String sql = "SELECT * FROM appear_four_choices_quiz";
        ResultSet rs = mySQLConnection.executeQuery(sql);
        List<Question> res = new ArrayList<Question>();

        // Create an Element object from the row data
        while (rs.next()) {
            String question = rs.getString("question");
            String correctAns = rs.getString("correct_ans");
            String wans1 = rs.getString("wrong_ans1");
            String wans2 = rs.getString("wrong_ans2");
            String wans3 = rs.getString("wrong_ans3");
            Question tmp = new Question(question, correctAns, wans1, wans2, wans3);
            res.add(tmp);
        }

        // Close the database connection
        mySQLConnection.close();
        return res;
    }

    public static Result[][] getCombinations() throws SQLException, ClassNotFoundException {
        mySQLConnection = new MySQLConnection();
        Result[][] res = new Result[100][100];
        String sql = "SELECT * FROM combination";
        ResultSet rs = mySQLConnection.executeQuery(sql);

        // Create an Element object from the row data
        while (rs.next()) {
            int element1_id = Integer.parseInt(rs.getString("element1_id"));
            int element2_id = Integer.parseInt(rs.getString("element2_id"));
            int result_id = Integer.parseInt(rs.getString("result_element"));
            String description = rs.getString("description");

            Result tmp = new Result(result_id, description);
            res[element1_id][element2_id] = tmp;
            res[element2_id][element1_id] = tmp;
        }

        // Close the database connection
        mySQLConnection.close();
        return res;
    }


    public static List<Element> getAllElements() throws SQLException, ClassNotFoundException {
        mySQLConnection = new MySQLConnection();
        List<Element> elements = new ArrayList<Element>();

        String sql = "SELECT * FROM element;";
        ResultSet rs = mySQLConnection.executeQuery(sql);

        // Create an Element object from the row data
        while (rs.next()) {
            int id = Integer.parseInt(rs.getString("id"));
            String elementName = rs.getString("element_name");
            String elementDescription = rs.getString("element_description");
            String imageLink = rs.getString("image_link");
            Element element = new Element(id, elementName, elementDescription, imageLink);
            elements.add(element);
        }

        // Close the database connection
        mySQLConnection.close();
        return elements;
    }
}
