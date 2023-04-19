package turizmacentesistemi.View;

import turizmacentesistemi.Helper.Config;
import turizmacentesistemi.Helper.DBConnector;
import turizmacentesistemi.Helper.Helper;
import turizmacentesistemi.Model.Operator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResCompleteGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_name1;
    private JTextField fld_name2;
    private JTextField fld_name3;
    private JTextField fld_name4;
    private JTextField fld_name5;
    private JTextField fld_name6;
    private JTextField fld_name7;
    private JTextField fld_name8;
    private JTextField fld_name9;
    private JTextField fld_id1;
    private JTextField fld_id2;
    private JTextField fld_id3;
    private JTextField fld_id4;
    private JTextField fld_id5;
    private JTextField fld_id6;
    private JTextField fld_id7;
    private JTextField fld_id8;
    private JTextField fld_id9;
    private JTextField fld_name;
    private JTextField fld_phone;
    private JTextField fld_mail;
    private JTextField fld_res_note;
    private JLabel lbl1;
    private JLabel lbl2;
    private JLabel lbl3;
    private JLabel lbl4;
    private JLabel lbl5;
    private JLabel lbl6;
    private JLabel lbl7;
    private JLabel lbl8;
    private JLabel lbl9;
    private JLabel lbl_name_lastname1;
    private JLabel lbl_name_lastname2;
    private JLabel lbl_name_lastname3;
    private JLabel lbl_name_lastname4;
    private JLabel lbl_name_lastname5;
    private JLabel lbl_name_lastname6;
    private JLabel lbl_name_lastname7;
    private JLabel lbl_name_lastname8;
    private JLabel lbl_name_lastname9;
    private JLabel lbl_id9;
    private JLabel lbl_id8;
    private JLabel lbl_id7;
    private JLabel lbl_id6;
    private JLabel lbl_id5;
    private JLabel lbl_id4;
    private JLabel lbl_id3;
    private JLabel lbl_id2;
    private JLabel lbl_id1;
    private JButton btn_save;
    private ArrayList<JLabel> guestNumLabelList = new ArrayList<>();
    private ArrayList<JLabel> idLabelList = new ArrayList<>();
    private ArrayList<JLabel> nameLabelList = new ArrayList<>();
    private ArrayList<JTextField> TCTextFieldList = new ArrayList<>();
    private ArrayList<JTextField> nameTexfieldList = new ArrayList<>();

    public ResCompleteGUI(Operator u, int hotel_id, int room_id, String room_type, int numAdult, int numChild, Date checkIn, Date checkOut) {
        int numGuest = numAdult + numChild;
        add(wrapper);
        setSize(1000, 800);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        nameTexfieldList.add(0, fld_name1);
        nameTexfieldList.add(1, fld_name2);
        nameTexfieldList.add(2, fld_name3);
        nameTexfieldList.add(3, fld_name4);
        nameTexfieldList.add(4, fld_name5);
        nameTexfieldList.add(5, fld_name6);
        nameTexfieldList.add(6, fld_name7);
        nameTexfieldList.add(7, fld_name8);
        nameTexfieldList.add(8, fld_name9);

        TCTextFieldList.add(0, fld_id1);
        TCTextFieldList.add(1, fld_id2);
        TCTextFieldList.add(2, fld_id3);
        TCTextFieldList.add(3, fld_id4);
        TCTextFieldList.add(4, fld_id5);
        TCTextFieldList.add(5, fld_id6);
        TCTextFieldList.add(6, fld_id7);
        TCTextFieldList.add(7, fld_id8);
        TCTextFieldList.add(8, fld_id9);

        guestNumLabelList.add(0, lbl1);
        guestNumLabelList.add(1, lbl2);
        guestNumLabelList.add(2, lbl3);
        guestNumLabelList.add(3, lbl4);
        guestNumLabelList.add(4, lbl5);
        guestNumLabelList.add(5, lbl6);
        guestNumLabelList.add(6, lbl7);
        guestNumLabelList.add(7, lbl8);
        guestNumLabelList.add(8, lbl9);

        nameLabelList.add(0, lbl_name_lastname1);
        nameLabelList.add(1, lbl_name_lastname2);
        nameLabelList.add(2, lbl_name_lastname3);
        nameLabelList.add(3, lbl_name_lastname4);
        nameLabelList.add(4, lbl_name_lastname5);
        nameLabelList.add(5, lbl_name_lastname6);
        nameLabelList.add(6, lbl_name_lastname7);
        nameLabelList.add(7, lbl_name_lastname8);
        nameLabelList.add(8, lbl_name_lastname9);

        idLabelList.add(0, lbl_id1);
        idLabelList.add(1, lbl_id2);
        idLabelList.add(2, lbl_id3);
        idLabelList.add(3, lbl_id4);
        idLabelList.add(4, lbl_id5);
        idLabelList.add(5, lbl_id6);
        idLabelList.add(6, lbl_id7);
        idLabelList.add(7, lbl_id8);
        idLabelList.add(8, lbl_id9);

        for (JTextField tfd : nameTexfieldList) {
            tfd.setVisible(false);
        }
        for (JTextField tcfd : TCTextFieldList) {
            tcfd.setVisible(false);
        }
        for (JLabel lblfd : guestNumLabelList) {
            lblfd.setVisible(false);
        }
        for (JLabel id : idLabelList) {
            id.setVisible(false);
        }
        for (JLabel name : nameLabelList) {
            name.setVisible(false);
        }

        fld_name1.setVisible(true);
        nameTexfieldList.get(0).setVisible(true);
        TCTextFieldList.get(0).setVisible(true);
        guestNumLabelList.get(0).setVisible(true);
        idLabelList.get(0).setVisible(true);
        nameLabelList.get(0).setVisible(true);

        for (int i = 0; i < numGuest; i++) {
            nameTexfieldList.get(i).setVisible(true);
            TCTextFieldList.get(i).setVisible(true);
            guestNumLabelList.get(i).setVisible(true);
            idLabelList.get(i).setVisible(true);
            nameLabelList.get(i).setVisible(true);
        }
        btn_save.addActionListener(e -> {
            Boolean status = false;
            String contactName = fld_name.getText();
            String contactResnote = fld_res_note.getText();
            String contactPhone = fld_phone.getText();
            String contactEmail = fld_mail.getText();
            String guestName = null, guestTc = null;
            for (int i = 0; i < numGuest; i++) {
                guestName = nameTexfieldList.get(i).getText();
                guestTc = TCTextFieldList.get(i).getText();
                if ((fld_id2.isVisible() == (true) && Helper.isFieldEmpty(fld_id2) && Helper.isFieldEmpty(fld_name2)) ||
                        (fld_id3.isVisible() == (true) && Helper.isFieldEmpty(fld_id3) && Helper.isFieldEmpty(fld_name3)) ||
                        (fld_id4.isVisible() == (true) && Helper.isFieldEmpty(fld_id4) && Helper.isFieldEmpty(fld_name4)) ||
                        (fld_id5.isVisible() == (true) && Helper.isFieldEmpty(fld_id5) && Helper.isFieldEmpty(fld_name5)) ||
                        (fld_id6.isVisible() == (true) && Helper.isFieldEmpty(fld_id6) && Helper.isFieldEmpty(fld_name6)) ||
                        (fld_id7.isVisible() == (true) && Helper.isFieldEmpty(fld_id7) && Helper.isFieldEmpty(fld_name7)) ||
                        (fld_id8.isVisible() == (true) && Helper.isFieldEmpty(fld_id8) && Helper.isFieldEmpty(fld_name8)) ||
                        (fld_id9.isVisible() == (true) && Helper.isFieldEmpty(fld_id9) && Helper.isFieldEmpty(fld_name9))) {
                    Helper.showMessage("Tüm alanları doldurun.");
                    i = 100;
                } else if ((Helper.isFieldEmpty(fld_name) || Helper.isFieldEmpty(fld_phone) || Helper.isFieldEmpty(fld_mail) || Helper.isFieldEmpty(fld_res_note) || Helper.isFieldEmpty(fld_name1) || Helper.isFieldEmpty(fld_id1))) {
                    Helper.showMessage("Tüm alanları doldurun.");
                    i = 1110;
                } else {
                    status = addRes(contactName, contactResnote, contactPhone, contactEmail, guestName, guestTc, hotel_id, room_id, checkIn, checkOut);
                }
            }
            if (Boolean.TRUE.equals(status)) {
                updateStock(room_id);
                Helper.showMessage("Rezervasyon Tamamlandı.");
                dispose();
            } else {
                Helper.showMessage("Bir hata oluştu.");
            }
        });
    }

    public boolean addRes(String contactName, String resNote, String contactPhone, String contactEmail, String guestName, String guestTc, int hotel_id, int room_id, Date checkIn, Date checkOut) {
        String query = "INSERT INTO res_list " + "(hotel_id, room_id, check_in, check_out, guest_name, guest_identity, res_note," +
                "contact_name,contact_phone,contact_mail" + ")" + "VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            pr.setInt(2, room_id);
            pr.setDate(3, checkIn);
            pr.setDate(4, checkOut);
            pr.setString(5, guestName);
            pr.setString(6, guestTc);
            pr.setString(7, resNote);
            pr.setString(8, contactName);
            pr.setString(9, contactPhone);
            pr.setString(10, contactEmail);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getStockInfo(int room_id) {
        int stock = -1;
        String query = "SELECT stock FROM room WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, room_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                stock = rs.getInt("stock");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stock;
    }

    public boolean updateStock(int room_id) {
        int stock = getStockInfo(room_id);
        if (stock == -1) {
            return false;
        }
        int newStock = stock - 1;
        String query = "UPDATE room SET stock = ? WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, newStock);
            pr.setInt(2, room_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}