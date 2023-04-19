package turizmacentesistemi.View;

import turizmacentesistemi.Helper.Config;
import turizmacentesistemi.Helper.Helper;
import turizmacentesistemi.Model.Hotel;
import turizmacentesistemi.Model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRoomGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_bed_no;
    private JTextField fld_stock;
    private JButton btn_add;
    private JComboBox cmb_room_type;
    private JCheckBox chk_gameconsole;
    private JCheckBox chk_mini_bar;
    private JCheckBox chk_tv;
    private JLabel lbl_hotel_name;
    private Hotel hotel;
    private final String[] roomTypeList = {"Single", "Double", "Suit"};

    public AddRoomGUI(int hotelId) {
        this.hotel = Hotel.getFetch(hotelId);
        add(wrapper);
        setSize(800, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        loadRoomTypeCombo();
        lbl_hotel_name.setText(hotel.getName());

        btn_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_bed_no) || Helper.isFieldEmpty(fld_stock)) {
                Helper.showMessage("fill");
            } else {
                String room_type = cmb_room_type.getSelectedItem().toString();
                int hotel_id = hotel.getId();
                if (Room.isRoomTypeAdded(room_type, hotel_id) == 0) {
                    int stock = Integer.parseInt(fld_stock.getText());
                    int bed_num = Integer.parseInt(fld_bed_no.getText());
                    boolean tv = chk_tv.isSelected();
                    boolean game_console = chk_gameconsole.isSelected();
                    boolean mini_bar = chk_mini_bar.isSelected();
                    if (Room.add(room_type, bed_num, stock, tv, mini_bar, game_console, hotel_id)) {
                        Helper.showMessage("done");
                    } else {
                        Helper.showMessage("error");
                    }
                } else {
                    Helper.showMessage("Bu otele ait " + room_type + " tipinde oda daha önce eklenmiştir.");
                }

            }
        });
    }

    public void loadRoomTypeCombo() {
        cmb_room_type.removeAllItems();
        for (String str : roomTypeList) {
            cmb_room_type.addItem(str);
        }
    }
}