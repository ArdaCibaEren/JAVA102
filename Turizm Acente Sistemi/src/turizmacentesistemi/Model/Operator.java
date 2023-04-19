package turizmacentesistemi.Model;

import turizmacentesistemi.Helper.DBConnector;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Operator {
    private int id;
    private String name, email, password;

    public Operator() {
    }

    public Operator(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static Operator getFetch(String email, String password) {
        Operator obj = null;
        String query = "SELECT * FROM user WHERE email = ? AND password = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, email);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                obj = new Operator(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static Operator getFetch(String email) {
        Operator obj = null;
        String query = "SELECT * FROM user WHERE email = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, email);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                obj = obj = new Operator(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static ArrayList<Date> getCheckInOutDate(int hotel_id) {
        String query = "SELECT check_in, check_out FROM res_list WHERE hotel_id = ?";
        ArrayList<Date> dates = new ArrayList<>();
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                Date check_in = rs.getDate("check_in");
                Date check_out = rs.getDate("check_out");
                dates.add(check_in);
                dates.add(check_out);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}