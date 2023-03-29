package patikadev.Model;

import patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Student {

    public static ArrayList<ContentCourse> getList(int id) {
        ArrayList<ContentCourse> contentList = new ArrayList<>();
        ContentCourse obj;
        try {
            String query = "SELECT co.id, co.title, co.info, co.link, co.course_name FROM `course` c LEFT JOIN student_course s ON c.id = s.course_id LEFT JOIN content co ON c.name=co.course_name WHERE s.student_id=" + id;
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int content_id = rs.getInt("id");
                String title = rs.getString("title");
                String info = rs.getString("info");
                String link = rs.getString("link");
                String course_name = rs.getString("course_name");
                obj = new ContentCourse(content_id, title, info, link, course_name);
                contentList.add(obj);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return contentList;
    }

    public static void saveStudentCourse(int student_id, int course_id) {
        String query = "INSERT INTO student_course (student_id, course_id) VALUES (?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, student_id);
            pr.setInt(2, course_id);
            pr.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}