package turizmacentesistemi.View;

import turizmacentesistemi.Helper.Config;
import turizmacentesistemi.Helper.Helper;
import turizmacentesistemi.Model.Hotel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateHotelGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_hotel_name;
    private JTextField fld_city;
    private JTextField fld_mail;
    private JTextField fld_phone;
    private JTextField fld_star;
    private JTextField fld_feature;
    private JTextField fld_lodging_type;
    private JTextField fld_address;
    private JButton btn_update;
    private int hotel_id;
    private Hotel hotel;

    public UpdateHotelGUI(int hotel_id) {
        this.hotel_id = hotel_id;
        add(wrapper);
        setSize(500, 700);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        hotel = Hotel.getFetch(hotel_id);
        fld_address.setText(hotel.getAddress());
        fld_city.setText(hotel.getCity());
        fld_feature.setText(hotel.getFeature());
        fld_hotel_name.setText(hotel.getName());
        fld_mail.setText(hotel.getMail());
        fld_lodging_type.setText(hotel.getLodging_type());
        fld_phone.setText(hotel.getPhone());
        fld_star.setText(hotel.getStar());

        btn_update.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_name) || Helper.isFieldEmpty(fld_city) || Helper.isFieldEmpty(fld_mail)
                    || Helper.isFieldEmpty(fld_phone) || Helper.isFieldEmpty(fld_star) || Helper.isFieldEmpty(fld_feature)
                    || Helper.isFieldEmpty(fld_lodging_type) || Helper.isFieldEmpty(fld_address)) {
                Helper.showMessage("fill");
            } else {
                if (Hotel.update(hotel_id, fld_hotel_name.getText(), fld_city.getText(), fld_mail.getText(), fld_phone.getText(),
                        fld_star.getText(), fld_feature.getText(), fld_lodging_type.getText(), fld_address.getText())) {
                    Helper.showMessage("done");
                }
                dispose();
            }
        });
    }
}