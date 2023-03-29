package patikadev.Model;

import patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course {
    private int id, user_id, patika_id;
    private String name, language;

    private Patika patika;
    private User user;

    public Course(int id, int user_id, int patika_id, String name, String language) {
        this.id = id;
        this.user_id = user_id;
        this.patika_id = patika_id;
        this.name = name;
        this.language = language;
        this.patika = Patika.getFetch(patika_id);
        this.user = User.getFetch(user_id);
    }

    public Course() {
    }

    public static String searchQuery(String patika_adi) {
        String query = "SELECT * FROM patika WHERE name LIKE '%{{name}}%'";
        query = query.replace("{{name}}", patika_adi);
        return query;
    }

    public static ArrayList<Integer> searchPatikaList(String query) {
        Patika obj;
        ArrayList<Integer> id = new ArrayList<Integer>();
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Patika();
                obj.setId(rs.getInt("id"));
                id.add(obj.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public static ArrayList<Course> mainSearch(String queryCall) {
        ArrayList<Course> courseList = new ArrayList<>();
        Course obj;
        for (Integer id : searchPatikaList(queryCall)) {
            String query = "SELECT * FROM course WHERE patika_id = " + id;
            try {
                Statement st = DBConnector.getInstance().createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    obj = new Course();
                    obj.setId(rs.getInt("id"));
                    obj.setPatika_id(rs.getInt("patika_id"));
                    obj.setName(rs.getString("name"));
                    courseList.add(obj);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return courseList;
    }

    public static boolean addComment(String comment, int rate, int content_id) {
        String query = "INSERT INTO content_comments (comment, rate, content_id) VALUES (?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, comment);
            pr.setInt(2, rate);
            pr.setInt(3, content_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPatika_id() {
        return patika_id;
    }

    public void setPatika_id(int patika_id) {
        this.patika_id = patika_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Patika getPatika() {
        return patika;
    }

    public void setPatika(Patika patika) {
        this.patika = patika;
    }

    public User getEducator() {
        return user;
    }

    public void setEducator(User user) {
        this.user = user;
    }

    public static ArrayList<Course> getList() {
        ArrayList<Course> courseList = new ArrayList<>();
        Course obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM course");
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                int patika_id = rs.getInt("patika_id");
                String name = rs.getString("name");
                String language = rs.getString("language");
                obj = new Course(id, user_id, patika_id, name, language);
                courseList.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return courseList;
    }

    public static ArrayList<Course> getListByUser(int user_id) {
        ArrayList<Course> courseList = new ArrayList<>();

        Course obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM course WHERE user_id = " + user_id);
            while (rs.next()) {
                int id = rs.getInt("id");
                int userID = rs.getInt("user_id");
                int patika_id = rs.getInt("patika_id");
                String name = rs.getString("name");
                String language = rs.getString("language");
                obj = new Course(id, userID, patika_id, name, language);
                courseList.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return courseList;
    }

    public static boolean add(int user_id, int patika_id, String name, String language) {
        String query = "INSERT INTO course (user_id, patika_id, name, language) VALUES (?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, user_id);
            pr.setInt(2, patika_id);
            pr.setString(3, name);
            pr.setString(4, language);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean delete(int id) {
        String query = "DELETE FROM course WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static int getIDbyName(String course_name) {
        int id = 0;
        String query = "SELECT id FROM course WHERE name = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, course_name);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    public static String getNamebyID(int id){
        String name = "";
        String query = "SELECT name FROM course WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return name;
    }
}