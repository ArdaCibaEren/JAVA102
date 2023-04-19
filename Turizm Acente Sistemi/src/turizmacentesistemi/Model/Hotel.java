package turizmacentesistemi.Model;

import turizmacentesistemi.Helper.DBConnector;

import java.sql.*;
import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name, address, city, mail, star, feature, lodging_type, phone;

    public Hotel() {
    }

    public Hotel(int id, String name, String city, String mail, String phone, String star, String feature, String lodging_type, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.mail = mail;
        this.star = star;
        this.feature = feature;
        this.phone = phone;
        this.lodging_type = lodging_type;
    }

    public static Hotel getFetch(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM hotels WHERE id = " + id;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                obj = new Hotel(rs.getInt("id"), rs.getString("name"), rs.getString("city"),
                        rs.getString("mail"), rs.getString("phone"), rs.getString("star"),
                        rs.getString("feature"), rs.getString("lodging_types"), rs.getString("address"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static ArrayList<Hotel> getList() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        Hotel obj;
        String sql = "SELECT * FROM hotels";
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                obj = new Hotel(rs.getInt("id"), rs.getString("name"), rs.getString("city"), rs.getString("mail"),
                        rs.getString("phone"), rs.getString("star"),
                        rs.getString("feature"), rs.getString("lodging_types"), rs.getString("address"));
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }

    public static Hotel getHotelByName(String hotelName) {
        Hotel obj = null;
        String query = "SELECT * FROM hotels WHERE name = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, hotelName);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Hotel(rs.getInt("id"), rs.getString("name"), rs.getString("city"), rs.getString("mail"),
                        rs.getString("phone"), rs.getString("star"),
                        rs.getString("feature"), rs.getString("lodging_types"), rs.getString("address"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static boolean add(String name, String city, String mail, String phone, String star, String feature, String lodging_type, String address) {
        String query = "INSERT INTO hotels (name, city, mail, phone, star, feature , lodging_types, address) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, city);
            pr.setString(3, mail);
            pr.setString(4, phone);
            pr.setString(5, star);
            pr.setString(6, feature);
            pr.setString(7, lodging_type);
            pr.setString(8, address);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean delete(int id) {
        String query = "DELETE FROM hotels WHERE id = ?";
        ArrayList<Hotel> courseList = Hotel.getList();
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean update(int id, String name, String city, String mail, String phone, String star, String feature, String lodging_type, String address) {
        String query = "UPDATE hotels SET name = ? , city = ? , mail = ? , phone = ? , star = ? , feature = ? , lodging_types = ? , address = ? WHERE id = " + id;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, city);
            pr.setString(3, mail);
            pr.setString(4, phone);
            pr.setString(5, star);
            pr.setString(6, feature);
            pr.setString(7, lodging_type);
            pr.setString(8, address);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static String searchQuery(String loc) {
        String query = "SELECT id,name FROM hotels WHERE city LIKE '%" + loc + "%'";
        return query;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getLodging_type() {
        return lodging_type;
    }

    public void setLodging_type(String lodging_type) {
        this.lodging_type = lodging_type;
    }
}