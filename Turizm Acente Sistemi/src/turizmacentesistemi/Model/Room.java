package turizmacentesistemi.Model;

import turizmacentesistemi.Helper.Config;
import turizmacentesistemi.Helper.DBConnector;
import turizmacentesistemi.View.SearchOutputGUI;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class Room {
    private int id, bed_number, stock, hotel_id;
    private String type;
    private boolean tv, mini_bar, game_console;


    private Hotel hotel;

    public Room(int id, String type, int bed_number, int stock, int hotel_id, boolean tv, boolean mini_bar, boolean game_console) {
        this.id = id;
        this.type = type;
        this.bed_number = bed_number;
        this.stock = stock;
        this.hotel_id = hotel_id;
        this.tv = tv;
        this.mini_bar = mini_bar;
        this.game_console = game_console;
    }

    public static boolean add(String type, int bed_number, int stock, boolean tv, boolean mini_bar, boolean game_console, int hotel_id) {
        String query = "INSERT INTO room (type, bed_number, stock, tv, mini_bar, game_console, hotel_id) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, type);
            pr.setInt(2, bed_number);
            pr.setInt(3, stock);
            pr.setBoolean(4, tv);
            pr.setBoolean(5, mini_bar);
            pr.setBoolean(6, game_console);
            pr.setInt(7, hotel_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int isRoomTypeAdded(String room_type, int hotel_id) {
        String query = "SELECT id FROM room WHERE type = ? AND hotel_id = ?";
        int id = 0;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, "type");
            pr.setString(2, "hotel_id");
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public static boolean setRoomPrice(int hotel_id, int[] lodging_price) {
        String query = "INSERT INTO room_price (lodging_type,hotel_id,price) VALUES (?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            for (int i = 0; i < lodging_price.length; i++) {
                pr.setString(1, Config.LODGING_TYPES[i]);
                pr.setInt(2, hotel_id);
                pr.setInt(3, lodging_price[i]);
                pr.addBatch();
            }
            pr.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<Integer> getAvailableRoomIds(int hotel_id, Date checkIn, Date checkOut) {
        ArrayList<Integer> roomIds = new ArrayList<Integer>();
        String query = "SELECT id FROM room WHERE hotel_id = ? AND stock > 0 AND id NOT IN " +
                "(SELECT room_id FROM res_list WHERE hotel_id = ? AND " +
                "((check_in <= ? AND check_out >= ?) OR (check_in >= ? AND check_in < ?)))";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            pr.setInt(2, hotel_id);
            pr.setDate(3, checkIn);
            pr.setDate(4, checkIn);
            pr.setDate(5, checkIn);
            pr.setDate(6, checkOut);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int roomId = rs.getInt("id");
                roomIds.add(roomId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBed_number() {
        return bed_number;
    }

    public void setBed_number(int bed_number) {
        this.bed_number = bed_number;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isMini_bar() {
        return mini_bar;
    }

    public void setMini_bar(boolean mini_bar) {
        this.mini_bar = mini_bar;
    }

    public boolean isGame_console() {
        return game_console;
    }

    public void setGame_console(boolean game_console) {
        this.game_console = game_console;
    }
}