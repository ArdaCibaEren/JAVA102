package patikadev.View;

import javax.swing.*;

import patikadev.Helper.Config;
import patikadev.Helper.Helper;
import patikadev.Model.ContentCourse;

public class UpdateContentGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_content_name;
    private JButton btn_update;
    private ContentCourse content;

    public UpdateContentGUI(ContentCourse content) {
        this.content = content;
        add(wrapper);
        setSize(300, 150);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        fld_content_name.setText(content.getTitle());
        btn_update.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_content_name)) {
                Helper.showMessage("fill");
            } else {
                if (ContentCourse.update(content.getId(), fld_content_name.getText())) {
                    Helper.showMessage("done");
                }
                dispose();
            }
        });
    }
}
