package patikadev.View;

import patikadev.Helper.*;
import patikadev.Model.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static patikadev.Model.User.getList;

public class OperatorGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane pnl_patika_list;
    private JPanel pnl_user_list;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JScrollPane scrll_user_list;
    private JTable tbl_user_list;
    private JPanel pnl_user_form;
    private JTextField fld_name;
    private JTextField fld_uname;
    private JTextField fld_password;
    private JComboBox cmb_type;
    private JButton btn_add_user;
    private JTextField fld_user_id;
    private JButton btn_user_delete;
    private JTextField fld_sh_user_name;
    private JTextField fld_sh_user_uname;
    private JComboBox cmb_sh_user_type;
    private JButton btn_user_sh;
    private JScrollPane scrll_patika_list;
    private JTable tbl_patika_list;
    private JPanel pnl_patika_add;
    private JTextField fld_patika_name;
    private JButton btn_patika_Add;
    private JPanel pnl_course_form;
    private JScrollPane sccrll_course_list;
    private JTable tbl_course_list;
    private JPanel pnl_course_add;
    private JTextField fld_course_name;
    private JTextField fld_course_lang;
    private JComboBox cmb_course_patika;
    private JComboBox cmb_educator;
    private JButton btn_course_add;
    private JButton btn_delete_course;
    private JTextField fld_course_id;
    private JPanel pnl_patika;
    private JPanel pnl_content;
    private JPanel pnl_quiz;
    private JTable tbl_content_list;
    private JTable tbl_quiz_list;
    private JScrollPane scrll_content;
    private JScrollPane scrll_quiz;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;
    private DefaultTableModel mdl_patika_list;
    private Object[] row_patika_list;
    private JPopupMenu patikaMenu;
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;
    private JPopupMenu contentMenu;
    private DefaultTableModel mdl_content_list;
    private Object[] row_content_list;
    private JPopupMenu quizMenu;
    private DefaultTableModel mdl_quiz_list;
    private Object[] row_quiz_list;

    private final Operator operator;

    public OperatorGUI(Operator operator) {
        this.operator = operator;

        add(wrapper);
        setSize(1000, 500);
        int x = Helper.screenCenterPoint("x", getSize());
        int y = Helper.screenCenterPoint("y", getSize());
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText("Hoşgeldiniz, " + operator.getName());

        mdl_user_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_user_list = {"ID", "Ad Soyad", "Kullanıcı Adı", "Şifre", "Üyelik Tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);
        row_user_list = new Object[col_user_list.length];
        loadUserModel();

        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);

        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString();
                fld_user_id.setText(select_user_id);
            } catch (Exception exception) {
            }
        });

        tbl_user_list.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int user_id = Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString());
                String user_name = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 1).toString();
                String user_uname = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 2).toString();
                String user_password = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 3).toString();
                String user_type = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 4).toString();

                if (User.update(user_id, user_name, user_uname, user_password, user_type)) {
                    Helper.showMessage("done");
                }
                loadUserModel();
                loadEducatorCombo();
                loadCourseModel();
            }
        });

        patikaMenu = new JPopupMenu();
        JMenuItem updateMenu = new JMenuItem("Güncelle");
        JMenuItem deleteMenu = new JMenuItem("Sil");
        patikaMenu.add(updateMenu);
        patikaMenu.add(deleteMenu);

        updateMenu.addActionListener(e -> {
            int select_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(), 0).toString());
            UpdatePatikaGUI updateGUI = new UpdatePatikaGUI(Patika.getFetch(select_id));
            updateGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPatikaModel();
                    loadPatikaCombo();
                    loadCourseModel();
                }
            });
        });

        deleteMenu.addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int select_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(), 0).toString());
                if (Patika.delete(select_id)) {
                    Helper.showMessage("done");
                    loadPatikaModel();
                    loadPatikaCombo();
                    loadCourseModel();
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        mdl_patika_list = new DefaultTableModel();
        Object[] col_patika_list = {"ID", "Patika Adı"};
        mdl_patika_list.setColumnIdentifiers(col_patika_list);
        row_patika_list = new Object[col_patika_list.length];
        loadPatikaModel();

        tbl_patika_list.setModel(mdl_patika_list);
        tbl_patika_list.setComponentPopupMenu(patikaMenu);
        tbl_patika_list.getTableHeader().setReorderingAllowed(false);
        tbl_patika_list.getColumnModel().getColumn(0).setMaxWidth(75);

        tbl_patika_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_patika_list.rowAtPoint(point);
                tbl_patika_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });

        mdl_course_list = new DefaultTableModel();
        Object[] col_course_list = {"ID", "Ders Adı", "Programlama Dili", "Patika", "Eğitmen"};
        mdl_course_list.setColumnIdentifiers(col_course_list);
        row_course_list = new Object[col_course_list.length];
        loadCourseModel();

        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);
        loadPatikaCombo();
        loadEducatorCombo();

        btn_add_user.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_uname) || Helper.isFieldEmpty(fld_password) || Helper.isFieldEmpty(fld_name)) {
                Helper.showMessage("fill");
            } else {
                String name = fld_name.getText();
                String uname = fld_uname.getText();
                String pass = fld_password.getText();
                String type = cmb_type.getSelectedItem().toString();
                if (User.add(name, uname, pass, type)) {
                    Helper.showMessage("done");
                    loadUserModel();
                    loadEducatorCombo();
                    fld_name.setText(null);
                    fld_uname.setText(null);
                    fld_password.setText(null);
                }
            }
        });
        btn_user_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_id)) {
                Helper.showMessage("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int user_id = Integer.parseInt(fld_user_id.getText());
                    if (User.delete(user_id)) {
                        Helper.showMessage("done");
                        loadUserModel();
                        loadEducatorCombo();
                        loadCourseModel();
                        fld_user_id.setText(null);
                    } else {
                        Helper.showMessage("error");
                    }
                }
            }
        });
        btn_user_sh.addActionListener(e -> {
            String name = fld_sh_user_name.getText();
            String uname = fld_sh_user_uname.getText();
            String type = cmb_sh_user_type.getSelectedItem().toString();
            String query = User.searchQuery(name, uname, type);
            loadUserModel(User.searchUserList(query));
        });
        btn_logout.addActionListener(e -> {
            dispose();
            LoginGUI login = new LoginGUI();
        });
        btn_patika_Add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_patika_name)) {
                Helper.showMessage("fill");
            } else {
                if (Patika.add(fld_patika_name.getText())) {
                    Helper.showMessage("done");
                    loadPatikaModel();
                    loadPatikaCombo();
                    fld_patika_name.setText(null);
                } else {
                    Helper.showMessage("error");
                }
            }
        });
        btn_course_add.addActionListener(e -> {
            Item patikaItem = (Item) cmb_course_patika.getSelectedItem();
            Item userItem = (Item) cmb_educator.getSelectedItem();
            if (Helper.isFieldEmpty(fld_course_name) || Helper.isFieldEmpty(fld_course_lang)) {
                Helper.showMessage("fill");
            } else {
                if (Course.add(userItem.getKey(), patikaItem.getKey(), fld_course_name.getText(), fld_course_lang.getText())) {
                    Helper.showMessage("done");
                    loadCourseModel();
                    fld_course_lang.setText(null);
                    fld_course_name.setText(null);
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        tbl_course_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_user_id = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 0).toString();
                fld_course_id.setText(select_user_id);
            } catch (Exception exception) {
            }
        });
        btn_delete_course.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_course_id)) {
                Helper.showMessage("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int course_id = Integer.parseInt(fld_course_id.getText());
                    if (Course.delete(course_id)) {
                        Helper.showMessage("done");
                        loadCourseModel();
                        loadEducatorCombo();
                    } else {
                        Helper.showMessage("error");
                    }
                }
            }
        });

        contentMenu = new JPopupMenu();
        JMenuItem updateContentMenu = new JMenuItem("Güncelle");
        JMenuItem deleteContentMenu = new JMenuItem("Sil");
        contentMenu.add(updateContentMenu);
        contentMenu.add(deleteContentMenu);

        updateContentMenu.addActionListener(e -> {
            int select_id = Integer.parseInt(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(), 0).toString());
            UpdateContentbyOpGUI updateGUI = new UpdateContentbyOpGUI(select_id);
            updateGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadContentModel();
                }
            });
        });

        deleteContentMenu.addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int select_id = Integer.parseInt(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(), 0).toString());
                if (ContentCourse.delete(select_id)) {
                    Helper.showMessage("done");
                    loadContentModel();
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        mdl_content_list = new DefaultTableModel();
        Object[] col_content_list = {"ID", "Başlık", "Açıklama", "Link", "Ders Adı"};
        mdl_content_list.setColumnIdentifiers(col_content_list);
        row_content_list = new Object[col_content_list.length];
        loadContentModel();

        tbl_content_list.setModel(mdl_content_list);
        tbl_content_list.setComponentPopupMenu(contentMenu);
        tbl_content_list.getTableHeader().setReorderingAllowed(false);
        tbl_content_list.getColumnModel().getColumn(0).setMaxWidth(75);

        tbl_content_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_content_list.rowAtPoint(point);
                tbl_content_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });

        quizMenu = new JPopupMenu();
        JMenuItem updateQuizMenu = new JMenuItem("Güncelle");
        JMenuItem deleteQuizMenu = new JMenuItem("Sil");
        quizMenu.add(updateQuizMenu);
        quizMenu.add(deleteQuizMenu);

        updateQuizMenu.addActionListener(e -> {
            int select_id = Integer.parseInt(tbl_quiz_list.getValueAt(tbl_quiz_list.getSelectedRow(), 0).toString());
            UpdateQuizbyOpGUI updateGUI = new UpdateQuizbyOpGUI(select_id);
            updateGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadQuizModel();
                }
            });
        });

        deleteQuizMenu.addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int select_id = Integer.parseInt(tbl_quiz_list.getValueAt(tbl_quiz_list.getSelectedRow(), 0).toString());
                if (Quiz.delete(select_id)) {
                    Helper.showMessage("done");
                    loadQuizModel();
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        mdl_quiz_list = new DefaultTableModel();
        Object[] col_quiz_list = {"ID", "Quiz Questions"};
        mdl_quiz_list.setColumnIdentifiers(col_content_list);
        row_quiz_list = new Object[col_quiz_list.length];
        loadQuizModel();

        tbl_quiz_list.setModel(mdl_quiz_list);
        tbl_quiz_list.setComponentPopupMenu(quizMenu);
        tbl_quiz_list.getTableHeader().setReorderingAllowed(false);
        tbl_quiz_list.getColumnModel().getColumn(0).setMaxWidth(75);

        tbl_quiz_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_quiz_list.rowAtPoint(point);
                tbl_quiz_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });
    }

    private void loadQuizModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_quiz_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (Quiz obj : Quiz.getAllQuiz()) {
            i = 0;
            row_quiz_list[i++] = obj.getId();
            row_quiz_list[i++] = obj.getQuiz_questions();
            mdl_quiz_list.addRow(row_quiz_list);
        }
    }

    private void loadContentModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_content_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (ContentCourse obj : ContentCourse.getList()) {
            i = 0;
            row_content_list[i++] = obj.getId();
            row_content_list[i++] = obj.getTitle();
            row_content_list[i++] = obj.getInfo();
            row_content_list[i++] = obj.getLink();
            row_content_list[i++] = obj.getCourse_name();
            mdl_content_list.addRow(row_content_list);
        }
    }

    private void loadCourseModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (Course obj : Course.getList()) {
            i = 0;
            row_course_list[i++] = obj.getId();
            row_course_list[i++] = obj.getName();
            row_course_list[i++] = obj.getLanguage();
            row_course_list[i++] = obj.getPatika().getName();
            row_course_list[i++] = obj.getEducator().getName();
            mdl_course_list.addRow(row_course_list);
        }
    }

    private void loadPatikaModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_patika_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (Patika obj : Patika.getList()) {
            i = 0;
            row_patika_list[i++] = obj.getId();
            row_patika_list[i++] = obj.getName();
            mdl_patika_list.addRow(row_patika_list);
        }
    }

    public void loadUserModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (User obj : getList()) {
            i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUname();
            row_user_list[i++] = obj.getPassword();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }

    public void loadUserModel(ArrayList<User> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        for (User obj : list) {
            int i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUname();
            row_user_list[i++] = obj.getPassword();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }

    public void loadPatikaCombo() {
        cmb_course_patika.removeAllItems();
        for (Patika obj : Patika.getList()) {
            cmb_course_patika.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    public void loadEducatorCombo() {
        cmb_educator.removeAllItems();
        for (User obj : User.getList()) {
            if (obj.getType().equals("educator")) {
                cmb_educator.addItem(new Item(obj.getId(), obj.getName()));
            }
        }
    }

    public static void main(String[] args) {
        Helper.setLayout();
        Operator op = new Operator();
        op.setId(1);
        op.setName("Chris");
        op.setUname("Martin");
        op.setPass("1234");
        op.setType("operator");

        OperatorGUI opGUI = new OperatorGUI(op);
    }
}