package patikadev.View;

import patikadev.Helper.*;
import patikadev.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class EducatorGUI extends JFrame {
    private JPanel wrapper;
    private JButton btn_logout;
    private JTabbedPane pnl_educator;
    private JTable tbl_course_list;
    private JScrollPane scrll_course_list;
    private JLabel lbl_welcome;
    private JPanel w_top;
    private JTextField txt_content_title;
    private JTextField txt_content_info;
    private JTextField txt_yt_link;
    private JComboBox cmb_course_name2;
    private JButton btn_add_content;
    private JPanel w_content;
    private JScrollPane scrll_content;
    private JTable tbl_content_list;
    private JPanel w_mid;
    private JButton btn_filter;
    private JComboBox cmb_coursename;
    private JComboBox cmb_contentname;
    private JEditorPane pane_quiz;
    private JComboBox cmb_content_quiz;
    private JButton btn_add_quiz;
    private JScrollPane scrll_quiz_list;
    private JTable tbl_quiz_list;
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;
    private DefaultTableModel mdl_content_list;
    private Object[] row_content_list;
    private Operator educator;
    private JPopupMenu contentMenu;
    private DefaultTableModel mdl_quiz_list;
    private Object[] row_quiz_list;


    public EducatorGUI(Operator educator) {
        this.educator = educator;
        add(wrapper);
        setSize(800, 700);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(true);
        setVisible(true);
        lbl_welcome.setText("Hoşgeldiniz, " + educator.getName());

        btn_logout.addActionListener(e -> {
            dispose();
            LoginGUI login = new LoginGUI();
        });


        mdl_course_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_course_list = {"Patika", "Ders adı"};
        mdl_course_list.setColumnIdentifiers(col_course_list);
        row_course_list = new Object[col_course_list.length];
        loadEducatorModel();

        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);


        contentMenu = new JPopupMenu();
        JMenuItem updateMenu = new JMenuItem("Güncelle");
        JMenuItem deleteMenu = new JMenuItem("Sil");
        contentMenu.add(updateMenu);
        contentMenu.add(deleteMenu);

        updateMenu.addActionListener(e -> {
            int select_id = Integer.parseInt(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(), 0).toString());
            UpdateContentGUI updateGUI = new UpdateContentGUI(ContentCourse.getFetch(select_id));
            updateGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadContentModel();
                    loadContentQuiz();
                }
            });
        });

        deleteMenu.addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int select_id = Integer.parseInt(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(), 0).toString());
                if (ContentCourse.delete(select_id)) {
                    Helper.showMessage("done");
                    loadContentModel();
                    loadContentQuiz();
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        mdl_content_list = new DefaultTableModel();
        Object[] col_content_list = {"ID", "Başlık", "Açıklama", "Youtube Linki", "Ders Bilgisi"};
        mdl_content_list.setColumnIdentifiers(col_content_list);
        row_content_list = new Object[col_content_list.length];
        loadContentModel();
        loadCourseCombo();
        loadCourseCombo2();
        loadContentCombo();
        loadContentQuiz();
        tbl_content_list.setModel(mdl_content_list);
        tbl_content_list.setComponentPopupMenu(contentMenu);
        tbl_content_list.getTableHeader().setReorderingAllowed(false);
        tbl_content_list.getColumnModel().getColumn(0).setMaxWidth(75);

        mdl_quiz_list = new DefaultTableModel();
        Object[] col_quiz_list = {"Content Title", "Quiz Questions"};
        mdl_quiz_list.setColumnIdentifiers(col_quiz_list);
        row_quiz_list = new Object[col_quiz_list.length];
        loadContentQuiz();
        loadQuizModel();
        loadCourseCombo();
        tbl_quiz_list.setModel(mdl_quiz_list);
        tbl_quiz_list.getTableHeader().setReorderingAllowed(false);
        tbl_quiz_list.getColumnModel().getColumn(0).setMaxWidth(75);

        btn_add_content.addActionListener(e -> {
            if (Helper.isFieldEmpty(txt_content_title) || Helper.isFieldEmpty(txt_content_info) || Helper.isFieldEmpty(txt_yt_link)) {
                Helper.showMessage("fill");
            } else {
                String title = txt_content_title.getText();
                String info = txt_content_info.getText();
                String link = txt_yt_link.getText();
                String course_name = cmb_course_name2.getSelectedItem().toString();
                if (ContentCourse.add(title, info, link, course_name)) {
                    Helper.showMessage("done");
                    loadContentModel();
                    loadEducatorModel();
                    txt_content_title.setText(null);
                    txt_content_info.setText(null);
                    txt_yt_link.setText(null);
                }
            }
        });

        cmb_coursename.addActionListener(e -> loadContentCombo());

        tbl_content_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_content_list.rowAtPoint(point);
                tbl_content_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });

        btn_filter.addActionListener(e -> {
            String title = cmb_contentname.getSelectedItem().toString();
            if (title != "") {
                ContentCourse obj = ContentCourse.getContentbyTitle(title);
                DefaultTableModel clearModel = (DefaultTableModel) tbl_content_list.getModel();
                clearModel.setRowCount(0);
                    row_content_list[0] = obj.getId();
                    row_content_list[1] = obj.getTitle();
                    row_content_list[2] = obj.getInfo();
                    row_content_list[3] = obj.getLink();
                    row_content_list[4] = obj.getCourse_name();
                    mdl_content_list.addRow(row_content_list);
            }
        });

        btn_add_quiz.addActionListener(e -> {
            loadContentModel();
            String content_title = cmb_content_quiz.getSelectedItem().toString();
            String quizText = pane_quiz.getText();
            if (ContentCourse.addQuiz(Quiz.getIDbyTitle(content_title), quizText)) {
                Helper.showMessage("done");
                pane_quiz.setText(null);
            } else {
                Helper.showMessage("error");
                pane_quiz.setText(null);
            }
            loadQuizModel();
        });
    }

    private void loadContentModel(ArrayList<ContentCourse> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_content_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (ContentCourse obj : ContentCourse.getList((educator.getId()))) {
            i = 0;
            row_content_list[i++] = obj.getId();
            row_content_list[i++] = obj.getTitle();
            row_content_list[i++] = obj.getInfo();
            row_content_list[i++] = obj.getLink();
            row_content_list[i++] = obj.getCourse_name();
            mdl_content_list.addRow(row_content_list);
        }
    }

    private void loadContentModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_content_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (ContentCourse obj : ContentCourse.getList(educator.getId())) {
            i = 0;
            row_content_list[i++] = obj.getId();
            row_content_list[i++] = obj.getTitle();
            row_content_list[i++] = obj.getInfo();
            row_content_list[i++] = obj.getLink();
            row_content_list[i++] = obj.getCourse_name();
            mdl_content_list.addRow(row_content_list);
        }
    }

    private void loadEducatorModel() {
        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_course_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (Course obj : Educator.getListByEducator(educator.getId())) {
            i = 0;
            row_course_list[i++] = Patika.getPatikaNamebyID(obj.getPatika_id());
            row_course_list[i++] = obj.getName();
            mdl_course_list.addRow(row_course_list);
        }
    }

    public void loadCourseCombo() {
        cmb_coursename.removeAllItems();
        for (Course obj : Educator.getListByEducator(educator.getId())) {
            cmb_coursename.addItem(obj.getName());
        }
    }

    public void loadCourseCombo2() {
        cmb_course_name2.removeAllItems();
        for (Course obj : Educator.getListByEducator(educator.getId())) {
            cmb_course_name2.addItem(obj.getName());
        }
    }

    public void loadContentCombo() {
        cmb_contentname.removeAllItems();
        String course_name = cmb_coursename.getSelectedItem().toString();
        for (ContentCourse obj : ContentCourse.getListbyCourse(course_name)) {
            cmb_contentname.addItem(obj.getTitle());
        }
    }

    public void loadContentQuiz() {
        cmb_content_quiz.removeAllItems();
        for (ContentCourse obj : ContentCourse.getList(educator.getId())) {
            cmb_content_quiz.addItem(obj.getTitle());
        }
    }

    public void loadQuizModel() {
        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_quiz_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (Quiz obj : Quiz.getQuizList(educator.getId())) {
            i = 0;
            row_quiz_list[i++] = Quiz.getTitlebyID(obj.getContent_id());
            row_quiz_list[i++] = obj.getQuiz_questions();
            mdl_quiz_list.addRow(row_quiz_list);
        }
    }
}
//www.patika.dev