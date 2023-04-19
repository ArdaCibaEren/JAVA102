package turizmacentesistemi.View;

import turizmacentesistemi.Helper.Config;
import turizmacentesistemi.Helper.Helper;
import turizmacentesistemi.Model.Hotel;
import turizmacentesistemi.Model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomPricingGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_ultra;
    private JTextField fld_all_inc;
    private JTextField fld_room_brkf;
    private JTextField fld_full_pension;
    private JTextField fld_half_pension;
    private JTextField fld_bed_only;
    private JTextField fld_full_cre;
    private JButton btn_save;
    private JLabel lbl_hotel_name;

    private Hotel hotel;

    public RoomPricingGUI(int hotelId) {
        this.hotel = Hotel.getFetch(hotelId);
        add(wrapper);
        setSize(800, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        lbl_hotel_name.setText(hotel.getName());

        btn_save.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_all_inc) && Helper.isFieldEmpty(fld_bed_only) && Helper.isFieldEmpty(fld_ultra) && Helper.isFieldEmpty(fld_room_brkf)
                    && Helper.isFieldEmpty(fld_full_pension) && Helper.isFieldEmpty(fld_half_pension) && Helper.isFieldEmpty(fld_full_cre)) {
                Helper.showMessage("Lütfen en az 1 fiyat giriniz.");
            } else {
                int i = 0;
                int hotel_id = hotel.getId();
                int lodging_price[] = new int[Config.LODGING_TYPES.length];
                lodging_price[i++] = fld_ultra.getText().isEmpty() ? 0 : Integer.parseInt(fld_ultra.getText());
                lodging_price[i++] = fld_all_inc.getText().isEmpty() ? 0 : Integer.parseInt(fld_all_inc.getText());
                lodging_price[i++] = fld_room_brkf.getText().isEmpty() ? 0 : Integer.parseInt(fld_room_brkf.getText());
                lodging_price[i++] = fld_full_pension.getText().isEmpty() ? 0 : Integer.parseInt(fld_full_pension.getText());
                lodging_price[i++] = fld_half_pension.getText().isEmpty() ? 0 : Integer.parseInt(fld_half_pension.getText());
                lodging_price[i++] = fld_bed_only.getText().isEmpty() ? 0 : Integer.parseInt(fld_bed_only.getText());
                lodging_price[i++] = fld_full_cre.getText().isEmpty() ? 0 : Integer.parseInt(fld_full_cre.getText());

                if (Room.setRoomPrice(hotel_id, lodging_price)) {
                    Helper.showMessage("done");
                    dispose();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }
}