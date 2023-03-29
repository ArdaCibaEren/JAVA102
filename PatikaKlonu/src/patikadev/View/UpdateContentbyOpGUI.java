package patikadev.View;

import patikadev.Helper.Config;
import patikadev.Helper.Helper;
import patikadev.Model.ContentCourse;

import javax.swing.*;

public class UpdateContentbyOpGUI extends JFrame{
    private JPanel wrapper;
    private JTextField txt_title;
    private JTextField txt_info;
    private JTextField txt_yt_link;
    private JButton btn_update;
    private int content_id;
    private ContentCourse content;

    public UpdateContentbyOpGUI(int content_id) {
        this.content_id = content_id;
        add(wrapper);
        setSize(300, 450);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        content = ContentCourse.getFetch(content_id);
        txt_title.setText(content.getTitle());
        txt_info.setText(content.getInfo());
        txt_yt_link.setText(content.getLink());
        btn_update.addActionListener(e -> {
            if (Helper.isFieldEmpty(txt_title) || Helper.isFieldEmpty(txt_info) ||Helper.isFieldEmpty(txt_yt_link)) {
                Helper.showMessage("fill");
            } else {
                if (ContentCourse.update(content_id, txt_title.getText(), txt_info.getText(), txt_yt_link.getText())) {
                    Helper.showMessage("done");
                }
                dispose();
            }
        });
    }
}
