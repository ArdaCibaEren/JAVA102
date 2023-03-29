package patikadev.Model;

import patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Quiz {
    private int id, content_id;
    private String quiz_questions;

    public Quiz(int id, int content_id, String quiz_questions) {
        this.id = id;
        this.content_id = content_id;
        this.quiz_questions = quiz_questions;
    }
    public Quiz(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContent_id() {
        return content_id;
    }

    public void setContent_id(int content_id) {
        this.content_id = content_id;
    }

    public String getQuiz_questions() {
        return quiz_questions;
    }

    public void setQuiz_questions(String quiz_questions) {
        this.quiz_questions = quiz_questions;
    }

    public static ArrayList<Quiz> getQuizList(int id) {
        ArrayList<ContentCourse> contentList = ContentCourse.getList(id);
        ArrayList<Quiz> quizList = new ArrayList<>();
        Quiz obj;
        for (ContentCourse content : contentList) {
            try {
                String query = "SELECT * FROM quiz WHERE content_id = \"" + content.getId() + "\"";
                Statement st = DBConnector.getInstance().createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    int quiz_id = rs.getInt("id");
                    int content_id = rs.getInt("content_id");
                    String quiz_questions = rs.getString("quiz_questions");
                    obj = new Quiz(quiz_id, content_id, quiz_questions);
                    quizList.add(obj);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return quizList;
    }

    public static int getIDbyTitle(String title) {
        int id = 0;
        String query = "SELECT id FROM content WHERE title = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, title);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    public static String getTitlebyID(int id) {
        String title = "";
        String query = "SELECT title FROM content WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                title = rs.getString("title");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return title;
    }

    public static ArrayList<Quiz> getList(int id) {
        ArrayList<Quiz> quizList = new ArrayList<>();
        Quiz obj;
        try {
            String query = "SELECT q.content_id, q.quiz_questions FROM `course` c LEFT JOIN student_course s ON c.id = s.course_id LEFT JOIN content co ON c.name=co.course_name LEFT JOIN quiz q ON q.content_id=co.id WHERE s.student_id=" + id;
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int content_id = rs.getInt("content_id");
                String quiz_questions = rs.getString("quiz_questions");
                obj = new Quiz(0, content_id, quiz_questions);
                quizList.add(obj);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return quizList;
    }

    public static Quiz getQuizbyID(int id) {
        Quiz obj = new Quiz();
        try {
            String query = "SELECT * FROM quiz WHERE id = " + id;
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int content_id = rs.getInt("content_id");
                String quiz_questions = rs.getString("quiz_questions");
                obj = new Quiz(0, content_id, quiz_questions);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static boolean update(int id, String quiz_questions) {
        String query = "UPDATE quiz SET quiz_questions = ? WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, quiz_questions);
            pr.setInt(2, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean delete(int id) {
        String query = "DELETE FROM quiz WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static ArrayList<Quiz> getAllQuiz() {
        ArrayList<Quiz> quizList = new ArrayList<>();
        Quiz obj;
            try {
                String query = "SELECT * FROM quiz";
                Statement st = DBConnector.getInstance().createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    int quiz_id = rs.getInt("id");
                    int content_id = rs.getInt("content_id");
                    String quiz_questions = rs.getString("quiz_questions");
                    obj = new Quiz(quiz_id, content_id, quiz_questions);
                    quizList.add(obj);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        return quizList;
    }
}
