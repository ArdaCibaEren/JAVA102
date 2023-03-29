package patikadev.View;

import patikadev.Helper.Config;
import patikadev.Helper.Helper;
import patikadev.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class StudentGUI extends JFrame {
    private JPanel wrapper;
    private JPanel w_top;
    private JButton btn_logout;
    private JTabbedPane pnl_student;
    private JTable tbl_patika_list;
    private JTextField txt_srch_patika;
    private JPanel w_ptk;
    private JScrollPane scrll_patika_list;
    private JLabel lbl_welcome;
    private JButton btn_sh_patika;
    private JTable tbl_course_list;
    private JScrollPane scrll_course_list;
    private JTable tbl_quiz_list;
    private JScrollPane scrll_quiz;
    private Operator student;
    private DefaultTableModel mdl_patika_list;
    private Object[] row_patika_list;
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;
    private JPopupMenu courseMenu;
    private DefaultTableModel mdl_quiz_list;
    private Object[] row_quiz_list;
    private JPopupMenu commentMenu;

    public StudentGUI(Operator student) {
        this.student = student;
        add(wrapper);
        setSize(800, 700);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(true);
        setVisible(true);

        lbl_welcome.setText("Hoşgeldiniz, " + student.getName());

        btn_logout.addActionListener(e -> {
            dispose();
            LoginGUI logIn = new LoginGUI();
        });

        mdl_patika_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        courseMenu = new JPopupMenu();
        JMenuItem joinCourse = new JMenuItem("Kayıt Ol");
        courseMenu.add(joinCourse);

        joinCourse.addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int i = 0;
                String select_course_name = tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(), 1).toString();
                for (ContentCourse obj : Student.getList(student.getId())) {
                    String course_name = obj.getCourse_name();
                    if(course_name.equals(select_course_name)){
                        i = 1;
                    }
                }
                if(i==1){
                    Helper.showMessage("Bu patikaya zaten kayıt olmuşsunuz!");
                }else{
                    Helper.showMessage("done");
                    Student.saveStudentCourse(student.getId(), Course.getIDbyName(select_course_name));
                    loadContentModel();
                    loadQuizModel();
                }
            }
        });

        mdl_patika_list = new DefaultTableModel();
        Object[] col_patika_list = {"Patika Adı", "Ders Adı"};
        mdl_patika_list.setColumnIdentifiers(col_patika_list);
        row_patika_list = new Object[col_patika_list.length];
        loadPatikaModel();

        tbl_patika_list.setModel(mdl_patika_list);
        tbl_patika_list.setComponentPopupMenu(courseMenu);
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

        btn_sh_patika.addActionListener(e -> {
            String patika_adi = txt_srch_patika.getText();
            String query = Course.searchQuery(patika_adi);
            loadPatikaModel(Course.mainSearch(query));
            txt_srch_patika.setText(null);
        });

        commentMenu = new JPopupMenu();
        JMenuItem commentCourse = new JMenuItem("Yorum yap ve değerlendir");
        commentMenu.add(commentCourse);

        commentCourse.addActionListener(e -> {
            String content_name = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 1).toString();
            int content_id = ContentCourse.getIDbyName(content_name);
            new CommentCourseGUI(content_id);
            loadContentModel();
        });

        mdl_course_list = new DefaultTableModel();
        Object[] col_course_list = {"Ders Adı", "İçerik Adı", "Açıklama", "Youtube Linki"};
        mdl_course_list.setColumnIdentifiers(col_course_list);
        row_course_list = new Object[col_course_list.length];
        loadContentModel();

        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.setComponentPopupMenu(commentMenu);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(75);

        tbl_course_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_course_list.rowAtPoint(point);
                tbl_course_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });

        mdl_quiz_list = new DefaultTableModel();
        Object[] col_quiz_list = {"Ders Adı", "Quiz Soruları"};
        mdl_quiz_list.setColumnIdentifiers(col_quiz_list);
        row_quiz_list = new Object[col_quiz_list.length];
        loadQuizModel();

        tbl_quiz_list.setModel(mdl_quiz_list);
    }

    public void loadPatikaModel(ArrayList<Course> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_patika_list.getModel();
        clearModel.setRowCount(0);
        for (Course obj : list) {
            int i = 0;
            row_patika_list[i++] = Patika.getPatikaNamebyID(obj.getPatika_id());
            row_patika_list[i++] = obj.getName();
            mdl_patika_list.addRow(row_patika_list);
        }
    }

    private void loadPatikaModel() {
        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_patika_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Course obj : Course.getList()) {
            i = 0;
            row_patika_list[i++] = Patika.getPatikaNamebyID(obj.getPatika_id());
            row_patika_list[i++] = obj.getName();
            mdl_patika_list.addRow(row_patika_list);
        }
    }

    private void loadContentModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (ContentCourse obj : Student.getList(student.getId())) {
            i = 0;
            row_course_list[i++] = obj.getCourse_name();
            row_course_list[i++] = obj.getTitle();
            row_course_list[i++] = obj.getInfo();
            row_course_list[i++] = obj.getLink();
            mdl_course_list.addRow(row_course_list);
        }
    }

    private void loadQuizModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_quiz_list.getModel();
        clearModel.setRowCount(0);
        for (Quiz obj : Quiz.getList(student.getId())) {
            int i = 0;
            row_quiz_list[i++] = ContentCourse.getContentNamebyID(obj.getContent_id());
            row_quiz_list[i++] = obj.getQuiz_questions();
            mdl_quiz_list.addRow(row_quiz_list);
        }
    }
}