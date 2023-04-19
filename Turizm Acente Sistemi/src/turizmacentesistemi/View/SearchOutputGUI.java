package turizmacentesistemi.View;

import turizmacentesistemi.Helper.Config;
import turizmacentesistemi.Helper.DBConnector;
import turizmacentesistemi.Helper.Helper;
import turizmacentesistemi.Model.Hotel;
import turizmacentesistemi.Model.Operator;

import javax.swing.*;
import java.util.ArrayList;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SearchOutputGUI extends JFrame {
    private JPanel wrapper;
    private JLabel lbl_star;
    private JLabel lbl_feature;
    private JLabel lbl_check_in;
    private JLabel lbl_check_out;
    private JLabel lbl_adult_guest;
    private JLabel lbl_child_guest;
    private JLabel lbl_bed_no;
    private JLabel lbl_tv;
    private JLabel lbl_mini_bar;
    private JLabel lbl_game_console;
    private JComboBox cmb_lodging_type;
    private JLabel lbl_city;
    private JLabel lbl_address;
    private JLabel lbl_phone;
    private JLabel lbl_res_dates;
    private JButton btn_res;
    private JLabel lbl_ultra;
    private JLabel lbl_all_inc;
    private JLabel lbl_room_brkf;
    private JLabel lbl_full_pension;
    private JLabel lbl_half_pension;
    private JLabel lbl_bed_only;
    private JLabel lbl_full_cre;
    private JLabel lbl_hotel_name;
    private JLabel lbl_room_type;
    private JButton btn_back;
    private JLabel lbl_total_price;
    private String[] lodgingTypeList = new String[]{"Ultra Her Şey Dahil", "Her Şey Dahil", "Oda Kahvaltı", "Tam Pansiyon", "Yarım Pansiyon", "Sadece Yatak", "Alkol Hariç Full Credit"};
    private int room_id;
    private Date check_in, check_out;

    public SearchOutputGUI(Operator u, Hotel hotel, ArrayList<Integer> rooms, Date checkIn, Date checkOut, int numAdult, int numChild) {
        add(wrapper);
        setSize(1000, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        this.check_in = checkIn;
        this.check_out = checkOut;
        int hotel_id = hotel.getId();
        int res_Date = calculateResDate(checkIn, checkOut);
        String room_type = getRoomType(hotel.getId(), numAdult);

        lbl_hotel_name.setText(hotel.getName());
        lbl_city.setText(hotel.getCity());
        lbl_star.setText(hotel.getStar());
        lbl_phone.setText(hotel.getPhone());
        lbl_address.setText(hotel.getAddress());
        lbl_feature.setText(hotel.getFeature());
        lbl_adult_guest.setText("Yetişkin sayısı: " + numAdult);
        lbl_child_guest.setText("Çocuk sayısı: " + numChild);
        lbl_check_in.setText(String.valueOf(checkIn));
        lbl_check_out.setText(String.valueOf(checkOut));
        lbl_room_type.setText(room_type + " Oda");
        lbl_res_dates.setText(res_Date + " Gece");
        loadLodgingCombo();
        lbl_total_price.setText(String.valueOf(calculatePrice(hotel_id, cmb_lodging_type.getSelectedItem().toString(), numAdult, numChild, res_Date)));
        getRoomInfo(room_id);

        btn_back.addActionListener(e -> {
            OperatorGUI opGUI = new OperatorGUI(u);
            dispose();
        });

        Map<String, Integer> prices = loadPrices(hotel_id);

        lbl_ultra.setText(String.valueOf(prices.get("Ultra Her Şey Dahil")));
        lbl_all_inc.setText(String.valueOf(prices.get("Her Şey Dahil")));
        lbl_room_brkf.setText(String.valueOf(prices.get("Oda Kahvaltı")));
        lbl_full_pension.setText(String.valueOf(prices.get("Tam Pansiyon")));
        lbl_half_pension.setText(String.valueOf(prices.get("Yarım Pansiyon")));
        lbl_bed_only.setText(String.valueOf(prices.get("Sadece Yatak")));
        lbl_full_cre.setText(String.valueOf(prices.get("Alkol Hariç Full Credit")));

        btn_res.addActionListener(e -> {
            ResCompleteGUI resCompleteGUI = new ResCompleteGUI(u, hotel_id, room_id, room_type, numAdult, numChild, checkIn, checkOut);
        });

        cmb_lodging_type.addActionListener(e -> {
            String price = String.valueOf(calculatePrice(hotel_id, cmb_lodging_type.getSelectedItem().toString(), numAdult, numChild, res_Date));
            if (price.equals("0")) {
                lbl_total_price.setText("Uygun pansiyon tipi seçiniz.");
            } else {
                lbl_total_price.setText(price);
            }
        });
    }

    private void loadRoomInfo(int bed_no, boolean tv, boolean mini_bar, boolean game_console) {
        lbl_bed_no.setText("Yatak sayısı : " + bed_no + " adet");
        if (tv) {
            lbl_tv.setText("Televizyon : Var");
        } else {
            lbl_tv.setText("Televizyon : Yok");
        }
        if (mini_bar) {
            lbl_mini_bar.setText("Minibar : Var");
        } else {
            lbl_mini_bar.setText("Minibar : Yok");
        }
        if (game_console) {
            lbl_game_console.setText("Oyun konsolu : Var");
        } else {
            lbl_game_console.setText("Oyun konsolu : Yok");
        }
    }

    private void getRoomInfo(int room_id) {
        int bed_number = 0;
        boolean tv = true, mini_bar = true, game_console = true;
        String query = "SELECT bed_number,tv,mini_bar,game_console FROM room WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, room_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                bed_number = rs.getInt("bed_number");
                tv = rs.getBoolean("tv");
                mini_bar = rs.getBoolean("mini_bar");
                game_console = rs.getBoolean("game_console");
            }
            loadRoomInfo(bed_number, tv, mini_bar, game_console);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int calculateResDate(Date checkIn, Date checkOut) {
        String chekinDateStr = String.valueOf(checkIn);
        String chekouDateStr = String.valueOf(checkOut);
        String[] partsOfCheckin = chekinDateStr.split("-");
        String[] partsOfCheckOut = chekouDateStr.split("-");

        int yearToDay = (Integer.parseInt(partsOfCheckOut[0]) - Integer.parseInt(partsOfCheckin[0])) * 365;
        System.out.println(yearToDay);
        int monthToDay = (Integer.parseInt(partsOfCheckOut[1]) - Integer.parseInt(partsOfCheckin[1])) * 30;
        System.out.println(monthToDay);
        int day = (Integer.parseInt(partsOfCheckOut[2]) - Integer.parseInt(partsOfCheckin[2]));
        System.out.println(day);
        return yearToDay + monthToDay + day;
    }

    private String getRoomType(int hotel_id, int guestNum) {
        String query = "SELECT id, type, bed_number FROM room WHERE hotel_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                if (rs.getInt("bed_number") >= guestNum) {
                    room_id = rs.getInt("id");
                    return rs.getString("type");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Map<String, Integer> loadPrices(int hotel_id) {
        String query = "SELECT lodging_type, price FROM room_price WHERE hotel_id = ?";
        Map<String, Integer> result = new HashMap<>();
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                String lodging_type = rs.getString("lodging_type");
                int price = rs.getInt("price");
                result.put(lodging_type, price);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private void loadLodgingCombo() {
        cmb_lodging_type.removeAllItems();
        for (String lodgingType : lodgingTypeList) {
            cmb_lodging_type.addItem(lodgingType);
        }
    }

    private int calculatePrice(int hotel_id, String lodging_type, int num_adults, int num_children, int num_nights) {
        String query = "SELECT price FROM `room_price` WHERE hotel_id = " + hotel_id + " AND lodging_type LIKE '%" + lodging_type + "%'";
        int price = 0;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                price = rs.getInt("price");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int total_price = num_nights * price * num_adults;
        int chld_total_price = num_nights * price * num_children * 10 / 100;
        total_price = total_price + chld_total_price;
        if (check_in.getMonth() >= 6) {
            total_price = total_price * 2;
        }
        return total_price;
    }
}