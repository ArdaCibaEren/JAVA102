package patikadev.View;

import patikadev.Helper.Config;
import patikadev.Helper.Helper;
import patikadev.Model.Course;

import javax.swing.*;

public class CommentCourseGUI extends JFrame {
    private JPanel wrapper;
    private JTextField txt_comment;
    private JButton btn_save;
    private JSlider sldr_point;
    private JLabel lbl_value;
    private Course comment;
    private int id;


    public CommentCourseGUI(int id) {
        this.id = id;
        add(wrapper);
        setSize(300, 180);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        txt_comment.setText(null);
        sldr_point.addChangeListener(ce -> {
            int a = sldr_point.getValue();
            lbl_value.setText(Integer.toString(a));
        });

        btn_save.addActionListener(e -> {
            if (Helper.isFieldEmpty(txt_comment) || sldr_point.equals(0)) {
                Helper.showMessage("fill");
            } else {
                if(Course.addComment(txt_comment.getText(), sldr_point.getValue(), id));
                Helper.showMessage("done");
                dispose();
            }
        });
    }
}
