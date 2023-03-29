package patikadev.Model;

import patikadev.Helper.DBConnector;
import patikadev.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ContentCourse {
    private int id;
    private String title, info, link, course_name;

    public ContentCourse(int id, String title, String info, String link, String course_name) {
        this.id = id;
        this.title = title;
        this.info = info;
        this.link = link;
        this.course_name = course_name;
    }

    public ContentCourse(){
    }

    public ContentCourse(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public static ContentCourse getFetch(int id) {
        ContentCourse obj = null;
        String query = "SELECT * FROM content WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new ContentCourse();
                obj.setId(rs.getInt("id"));
                obj.setTitle(rs.getString("title"));
                obj.setInfo(rs.getString("info"));
                obj.setLink(rs.getString("link"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return obj;
    }

    public static ContentCourse getFetchbyTitle(String title) {
        ContentCourse obj = null;
        String query = "SELECT * FROM content WHERE title = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, title);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new ContentCourse(rs.getInt("id"), rs.getString("title"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return obj;
    }

    public static boolean delete(int id) {
        String query = "DELETE FROM content WHERE id = ?";
        ArrayList<ContentCourse> contentList = ContentCourse.getList(id);
        for (ContentCourse obj : contentList) {
            if (obj.getId() == id) {
                ContentCourse.delete(obj.getId());
            }
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean update(int id, String title) {
        String query = "UPDATE content SET title = ? WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, title);
            pr.setInt(2, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean update(int id, String title, String info, String link) {
        String query = "UPDATE content SET title = ? , info = ? , link = ? WHERE id = " + id;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, title);
            pr.setString(2,info);
            pr.setString(3,link);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean add(String title, String info, String link, String course_name) {
        String query = "INSERT INTO content (title,info,link,course_name) VALUES (?,?,?,?)";
        ContentCourse findContentTitle = ContentCourse.getFetchbyTitle(title);
        if (findContentTitle != null) {
            Helper.showMessage("Bu kurs adı zaten mevcut, farklı bir kurs adı seçiniz.");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, title);
            pr.setString(2, info);
            pr.setString(3, link);
            pr.setString(4, course_name);
            int response = pr.executeUpdate();
            if (response == -1) {
                Helper.showMessage("error");
            }
            return response != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static boolean addQuiz(int content_id, String quiz_questions) {
        String query = "INSERT INTO quiz (content_id, quiz_questions) VALUES (?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, content_id);
            pr.setString(2, quiz_questions);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public static ArrayList<ContentCourse> getList(int id) {
        ArrayList<String> nameList = Educator.getNameByEducator(id);
        ArrayList<ContentCourse> contentList = new ArrayList<>();
        ContentCourse obj;

        for (String name : nameList) {
            try {
                String query = "SELECT * FROM content WHERE course_name = \"" + name + "\"";
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
        }
        return contentList;
    }

    public static ArrayList<ContentCourse> getList() {
        ArrayList<ContentCourse> contentList = new ArrayList<>();
        ContentCourse obj;
            try {
                String query = "SELECT * FROM content";
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

    public static String getContentNamebyID(int id) {
        String name = "";
        String query = "SELECT title FROM content WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                name = rs.getString("title");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return name;
    }

    public static int getIDbyName(String content_name) {
        int id = 0;
        String query = "SELECT id FROM content WHERE title = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, content_name);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    public static ArrayList<ContentCourse> getListbyCourse(String course) {
        ArrayList<ContentCourse> contentList = new ArrayList<>();
        ContentCourse obj;

            try {
                String query = "SELECT * FROM content WHERE course_name = \"" + course + "\"";
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

    public static ContentCourse getContentbyTitle(String title1) {
        ContentCourse obj;
        obj = new ContentCourse();
        try {
            String query = "SELECT * FROM content WHERE title = \"" + title1 + "\"";
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int content_id = rs.getInt("id");
                String title = rs.getString("title");
                String info = rs.getString("info");
                String link = rs.getString("link");
                String course_name = rs.getString("course_name");
                obj = new ContentCourse(content_id, title, info, link, course_name);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
}
