package turizmacentesistemi.View;

import turizmacentesistemi.Helper.Config;
import turizmacentesistemi.Helper.DBConnector;
import turizmacentesistemi.Helper.Helper;
import turizmacentesistemi.Model.Hotel;
import turizmacentesistemi.Model.Operator;
import turizmacentesistemi.Model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.sql.Date;

public class OperatorGUI extends JFrame {
    private JPanel wrapper;
    private JButton btn_logout;
    private JPanel w_top;
    private JLabel lbl_welcome;
    private JComboBox cmb_area;
    private JSpinner spn_adult;
    private JSpinner spn_child;
    private JButton btn_sch;
    private JTabbedPane pane_op;
    private JTable tbl_res_list;
    private JTable tbl_hotel_list;
    private JScrollPane scrll_hotel_list;
    private JScrollPane scrll_res_list;
    private JTextField fld_hotel_name;
    private JTextField fld_city;
    private JTextField fld_mail;
    private JTextField fld_phone;
    private JRadioButton a1RadioButton;
    private JRadioButton a7RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a6RadioButton;
    private JRadioButton a3RadioButton;
    private JRadioButton a4RadioButton;
    private JRadioButton a5RadioButton;
    private JCheckBox chk_free_parking;
    private JCheckBox chk_free_wifi;
    private JCheckBox chk_pool;
    private JCheckBox chk_fitness;
    private JCheckBox chk_concierge;
    private JCheckBox chk_spa;
    private JCheckBox chk_room_service;
    private JCheckBox chk_ultra;
    private JCheckBox chk_all_inc;
    private JCheckBox chk_room_brkf;
    private JCheckBox chk_full_pension;
    private JCheckBox chk_half_pension;
    private JCheckBox chk_bed_only;
    private JCheckBox chk_full_cre;
    private JButton btn_save;
    private JTextField fld_address;
    private JTable tbl_hotels;
    private JScrollPane scrll_hotels;
    private JTextField fld_check_in;
    private JTextField fld_check_out;
    private DefaultTableModel mdl_res_list;
    private Object[] row_res_list;
    private DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;
    private JPopupMenu HotelMenu;
    private DefaultTableModel mdl_hotels;
    private Object[] row_hotels;
    private JPopupMenu SearchMenu;
    private final Operator u;
    private ArrayList<Integer> roomIdList;
    private String checkInText = "";
    private String checkOutText = "";
    private boolean statusIn = true;
    private boolean statusOut = true;

    public OperatorGUI(Operator u) {
        this.u = u;
        add(wrapper);
        setSize(1000, 700);
        int x = Helper.screenCenterPoint("x", getSize());
        int y = Helper.screenCenterPoint("y", getSize());
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        int min = 0;
        int max = 9;
        int step = 1;
        int initValue = 0;

        SpinnerModel spinnerModelAdult = new SpinnerNumberModel(1, min, 6, step);
        SpinnerModel spinnerModelChild = new SpinnerNumberModel(0, min, 3, step);

        spn_adult.setModel(spinnerModelAdult);
        spn_child.setModel(spinnerModelChild);

        lbl_welcome.setText("Hoşgeldiniz, " + u.getName());

        btn_logout.addActionListener(e -> {
            dispose();
            LogInGUI login = new LogInGUI();
        });

        mdl_res_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_res_list = {"Misafir-Ad Soyad", "Oda Numarası", "İletişim-Ad Soyad", "İletişim-Telefon Numarası", "Rezervasyon Notu"};
        mdl_res_list.setColumnIdentifiers(col_res_list);
        row_res_list = new Object[col_res_list.length];
        loadResModel();

        tbl_res_list.setModel(mdl_res_list);
        tbl_res_list.getTableHeader().setReorderingAllowed(false);

        btn_sch.addActionListener(e -> {
            String loc = cmb_area.getSelectedItem().toString();
            Date checkIn = Date.valueOf(fld_check_in.getText());
            Date checkOut = Date.valueOf(fld_check_out.getText());
            int numAdult = Integer.parseInt(spn_adult.getValue().toString());
            int numChild = Integer.parseInt(spn_child.getValue().toString());
            search(loc, checkIn, checkOut, numAdult, numChild);
        });

        HotelMenu = new JPopupMenu();
        JMenuItem updateHotelMenu = new JMenuItem("Güncelle");
        JMenuItem deleteHotelMenu = new JMenuItem("Sil");
        JMenuItem addRoom = new JMenuItem("Oda ekle");
        JMenuItem room_pricing = new JMenuItem("Oda fiyatlandır");
        HotelMenu.add(updateHotelMenu);
        HotelMenu.add(deleteHotelMenu);
        HotelMenu.add(addRoom);
        HotelMenu.add(room_pricing);

        updateHotelMenu.addActionListener(e -> {
            int select_id = Integer.parseInt(tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 0).toString());
            UpdateHotelGUI updateGUI = new UpdateHotelGUI(select_id);
            updateGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelModel();
                }
            });
        });

        deleteHotelMenu.addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int select_id = Integer.parseInt(tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 0).toString());
                if (Hotel.delete(select_id)) {
                    Helper.showMessage("done");
                    loadHotelModel();
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        addRoom.addActionListener(e -> {
                    int select_id = Integer.parseInt(tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 0).toString());
                    AddRoomGUI roomGUI = new AddRoomGUI(select_id);
                }
        );

        room_pricing.addActionListener(e -> {
            int select_id = Integer.parseInt(tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 0).toString());
            RoomPricingGUI pricingGUI = new RoomPricingGUI(select_id);
        });

        mdl_hotel_list = new DefaultTableModel();
        Object[] col_hotel_list = {"Sıra no", "Otel Adı", "Şehir", "Mail", "Tel no", "Yıldız", "Tesis Özellikleri", "Pansiyon Türleri", "Adres"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);
        row_hotel_list = new Object[col_hotel_list.length];
        loadHotelModel();

        tbl_hotel_list.setModel(mdl_hotel_list);
        tbl_hotel_list.setComponentPopupMenu(HotelMenu);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);
        tbl_hotel_list.getColumnModel().getColumn(0).setMaxWidth(75);

        tbl_hotel_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Point point = e.getPoint();
                int selected_row = tbl_hotel_list.rowAtPoint(point);
                tbl_hotel_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });

        SearchMenu = new JPopupMenu();
        JMenuItem res_hotel = new JMenuItem("Rezervasyon Yap");
        SearchMenu.add(res_hotel);
        res_hotel.addActionListener(e -> {
                    int select_id = Integer.parseInt(tbl_hotels.getValueAt(tbl_hotels.getSelectedRow(), 0).toString());
                    Date checkIn = Date.valueOf(fld_check_in.getText());
                    Date checkOut = Date.valueOf(fld_check_out.getText());
                    Hotel hotel = Hotel.getFetch(select_id);
                    ArrayList<Integer> roomIds = Room.getAvailableRoomIds(select_id, checkIn, checkOut);
                    SearchOutputGUI searchGUI = new SearchOutputGUI(u, hotel, roomIds, checkIn, checkOut, Integer.parseInt(spn_adult.getValue().toString()), Integer.parseInt(spn_child.getValue().toString()));
                }
        );

        mdl_hotels = new DefaultTableModel();
        Object[] col_hotels = {"Sıra no", "Otel Adı", "Yıldız"};
        mdl_hotels.setColumnIdentifiers(col_hotels);
        row_hotels = new Object[col_hotels.length];

        tbl_hotels.setModel(mdl_hotels);
        tbl_hotels.setComponentPopupMenu(SearchMenu);
        tbl_hotels.getTableHeader().setReorderingAllowed(false);
        tbl_hotels.getColumnModel().getColumn(0).setMaxWidth(75);

        ButtonGroup group = new ButtonGroup();
        group.add(a1RadioButton);
        group.add(a2RadioButton);
        group.add(a3RadioButton);
        group.add(a4RadioButton);
        group.add(a5RadioButton);
        group.add(a6RadioButton);
        group.add(a7RadioButton);

        btn_save.addActionListener(e -> {
            String name = fld_hotel_name.getText();
            String city = fld_city.getText();
            String mail = fld_mail.getText();
            String phone = fld_phone.getText();
            String star = getSelectedButtonText(group);
            String feature = "";
            String lodging_type = "";
            String address = fld_address.getText();

            if (Helper.isFieldEmpty(fld_hotel_name) || Helper.isFieldEmpty(fld_city) || Helper.isFieldEmpty(fld_mail)
                    || Helper.isFieldEmpty(fld_phone) || Helper.isFieldEmpty(fld_address)) {
                Helper.showMessage("fill");
            } else {
                if (chk_free_parking.isSelected()) {
                    if (feature.isEmpty()) {
                        feature += chk_free_parking.getText();
                    } else {
                        feature += ", " + chk_free_parking.getText();
                    }
                }
                if (chk_free_wifi.isSelected()) {
                    if (feature.isEmpty()) {
                        feature += chk_free_wifi.getText();
                    } else {
                        feature += ", " + chk_free_wifi.getText();
                    }
                }
                if (chk_pool.isSelected()) {
                    if (feature.isEmpty()) {
                        feature += chk_pool.getText();
                    } else {
                        feature += ", " + chk_pool.getText();
                    }
                }
                if (chk_fitness.isSelected()) {
                    if (feature.isEmpty()) {
                        feature += chk_fitness.getText();
                    } else {
                        feature += ", " + chk_fitness.getText();
                    }
                }
                if (chk_concierge.isSelected()) {
                    if (feature.isEmpty()) {
                        feature += chk_concierge.getText();
                    } else {
                        feature += ", " + chk_concierge.getText();
                    }
                }
                if (chk_spa.isSelected()) {
                    if (feature.isEmpty()) {
                        feature += chk_spa.getText();
                    } else {
                        feature += ", " + chk_spa.getText();
                    }
                }
                if (chk_room_service.isSelected()) {
                    if (feature.isEmpty()) {
                        feature += chk_room_service.getText();
                    } else {
                        feature += ", " + chk_room_service.getText();
                    }
                }
                if (chk_ultra.isSelected()) {
                    if (lodging_type.isEmpty()) {
                        lodging_type += chk_ultra.getText();
                    } else {
                        lodging_type += ", " + chk_ultra.getText();
                    }
                }
                if (chk_all_inc.isSelected()) {
                    if (lodging_type.isEmpty()) {
                        lodging_type += chk_all_inc.getText();
                    } else {
                        lodging_type += ", " + chk_all_inc.getText();
                    }
                }
                if (chk_room_brkf.isSelected()) {
                    if (lodging_type.isEmpty()) {
                        lodging_type += chk_room_brkf.getText();
                    } else {
                        lodging_type += ", " + chk_room_brkf.getText();
                    }
                }
                if (chk_full_pension.isSelected()) {
                    if (lodging_type.isEmpty()) {
                        lodging_type += chk_full_pension.getText();
                    } else {
                        lodging_type += ", " + chk_full_pension.getText();
                    }
                }
                if (chk_half_pension.isSelected()) {
                    if (lodging_type.isEmpty()) {
                        lodging_type += chk_half_pension.getText();
                    } else {
                        lodging_type += ", " + chk_half_pension.getText();
                    }
                }
                if (chk_bed_only.isSelected()) {
                    if (lodging_type.isEmpty()) {
                        lodging_type += chk_bed_only.getText();
                    } else {
                        lodging_type += ", " + chk_bed_only.getText();
                    }
                }
                if (chk_full_cre.isSelected()) {
                    if (lodging_type.isEmpty()) {
                        lodging_type += chk_full_cre.getText();
                    } else {
                        lodging_type += ", " + chk_full_cre.getText();
                    }
                }
                if (Hotel.add(name, city, mail, phone, star, feature, lodging_type, address)) {
                    Helper.showMessage("done");
                    loadHotelModel();
                    fld_hotel_name.setText(null);
                    fld_city.setText(null);
                    fld_mail.setText(null);
                    fld_phone.setText(null);
                    fld_address.setText(null);
                }
            }
        });
        tbl_hotels.addComponentListener(new ComponentAdapter() {
        });
        fld_check_in.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                checkInText = String.valueOf(checkInText);
                if (statusIn) {
                    try {
                        checkInText = checkInText.substring(0, checkInText.length() - 1);
                    } catch (StringIndexOutOfBoundsException exception) {
                        exception.getStackTrace();
                    }
                    statusIn = false;
                } else {
                    int length = fld_check_in.getText().length();
                    if (length == 7 || length == 4) {
                        checkInText = fld_check_in.getText();
                        checkInText += "-";
                        fld_check_in.setText(checkInText);
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    statusIn = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        fld_check_out.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (statusOut) {
                    try {
                        checkOutText = checkOutText.substring(0, checkOutText.length() - 1);
                    } catch (StringIndexOutOfBoundsException exception) {
                        exception.getStackTrace();
                    }
                    statusOut = false;
                } else {
                    int length = fld_check_out.getText().length();
                    if (length == 7 || length == 4) {
                        checkOutText = fld_check_out.getText();
                        checkOutText += "-";
                        fld_check_out.setText(checkOutText);
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    statusOut = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    private void search(String loc, Date checkIn, Date checkOut, int numAdult, int numChild) {
        ArrayList<Integer> hotels = searchHotel(Hotel.searchQuery(loc));
        ArrayList<Hotel> hotel_search = new ArrayList<>();
        for (int hotel_id : hotels) {
            ArrayList<Integer> roomIds = Room.getAvailableRoomIds(hotel_id, checkIn, checkOut);
            if (!roomIds.isEmpty()) {
                Hotel hotel = Hotel.getFetch(hotel_id);
                hotel_search.add(hotel);
            }
            if (hotel_search.isEmpty()) {
                Helper.showMessage("Uygun oda bulunamadı!");
            }
        }
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotels.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Hotel obj : hotel_search) {
            i = 0;
            row_hotels[i++] = obj.getId();
            row_hotels[i++] = obj.getName();
            row_hotels[i++] = obj.getStar();
            mdl_hotels.addRow(row_hotels);
        }
    }

    private ArrayList<Integer> getRoomIdList() {
        return roomIdList;
    }

    private ArrayList<Integer> searchHotel(String query) {
        ArrayList<Integer> searchHotelList = new ArrayList<>();
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                searchHotelList.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return searchHotelList;
    }

    private void loadResModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_res_list.getModel();
        clearModel.setRowCount(0);
        String query = "SELECT * FROM res_list";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int i = 0;
                row_res_list[i++] = rs.getString("guest_name");
                row_res_list[i++] = rs.getInt("room_id");
                row_res_list[i++] = rs.getString("contact_name");
                row_res_list[i++] = rs.getString("contact_phone");
                row_res_list[i++] = rs.getString("res_note");
                mdl_res_list.addRow(row_res_list);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadHotelModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Hotel obj : Hotel.getList()) {
            i = 0;
            row_hotel_list[i++] = obj.getId();
            row_hotel_list[i++] = obj.getName();
            row_hotel_list[i++] = obj.getCity();
            row_hotel_list[i++] = obj.getMail();
            row_hotel_list[i++] = obj.getPhone();
            row_hotel_list[i++] = obj.getStar();
            row_hotel_list[i++] = obj.getFeature();
            row_hotel_list[i++] = obj.getLodging_type();
            row_hotel_list[i++] = obj.getAddress();
            mdl_hotel_list.addRow(row_hotel_list);
        }
    }
}