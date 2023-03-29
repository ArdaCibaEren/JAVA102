package patikadev.View;

import patikadev.Helper.Config;
import patikadev.Helper.Helper;
import patikadev.Model.Operator;
import patikadev.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JPanel wrapper;
    private JPanel w_top;
    private JPanel w_bottom;
    private JTextField fld_user_uname;
    private JPasswordField fld_user_password;
    private JButton btn_login;
    private JButton btn_sign_in;

    public LoginGUI() {
        add(wrapper);
        setSize(400, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        btn_login.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_uname) || Helper.isFieldEmpty(fld_user_password)) {
                Helper.showMessage("fill");
            } else {
                User u = User.getFetch(fld_user_uname.getText(), String.valueOf(fld_user_password.getPassword()));
                if (u == null) {
                    Helper.showMessage("Kullanıcı bulunamadı!");
                } else {
                    switch (u.getType()) {
                        case "operator":
                            OperatorGUI opGUI = new OperatorGUI((Operator) u);
                            break;
                        case "educator":
                            EducatorGUI edGUI = new EducatorGUI((Operator) u);
                            break;
                        case "student":
                            StudentGUI stGUI = new StudentGUI((Operator) u);
                            break;
                    }
                    dispose();
                }
            }
        });
        btn_sign_in.addActionListener(e -> {
            SignInGUI signInGUI = new SignInGUI();
            dispose();
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        LoginGUI login = new LoginGUI();
    }

}
