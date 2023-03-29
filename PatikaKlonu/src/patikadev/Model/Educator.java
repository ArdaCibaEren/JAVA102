package patikadev.Model;

import patikadev.Helper.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Educator {

    public static ArrayList<Course> getListByEducator(int user_id) {
        ArrayList<Course> courseListByEdu = new ArrayList<>();

        Course obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM course WHERE user_id = " + user_id);
            while (rs.next()) {
                int course_id = rs.getInt("id");
                int userID = rs.getInt("user_id");
                int patika_id = rs.getInt("patika_id");
                String course_name = rs.getString("name");
                String language = rs.getString("language");
                obj = new Course(course_id, userID, patika_id, course_name, language);
                courseListByEdu.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return courseListByEdu;
    }

    public static ArrayList<String> getNameByEducator(int user_id) {
        ArrayList<String> nameListByEdu = new ArrayList<>();

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT name FROM course WHERE user_id = " + user_id);
            while (rs.next()) {
                String course_name = rs.getString("name");
                nameListByEdu.add(course_name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nameListByEdu;
    }
}
