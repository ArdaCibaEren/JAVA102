package turizmacentesistemi.View;

import turizmacentesistemi.Helper.Config;
import turizmacentesistemi.Helper.Helper;
import turizmacentesistemi.Model.Operator;

import javax.swing.*;

public class LogInGUI extends JFrame {
    private JPanel wrapper;
    private JPanel w_top;
    private JPanel w_bottom;
    private JTextField fld_mail;
    private JButton btn_login;
    private JButton btn_signin;
    private JPasswordField fld_password;

    public LogInGUI() {
        add(wrapper);
        setSize(400, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        btn_login.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_mail) || Helper.isFieldEmpty(fld_password)) {
                Helper.showMessage("fill");
            } else {
                Operator u = Operator.getFetch(fld_mail.getText(), String.valueOf(fld_password.getPassword()));
                if (u == null) {
                    Helper.showMessage("Kullanıcı bulunamadı");
                } else {
                    OperatorGUI opGUI = new OperatorGUI(u);
                    dispose();
                }
            }
        });
        btn_signin.addActionListener(e -> {
            SignInGUI signInGUI = new SignInGUI();
            dispose();
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        LogInGUI login = new LogInGUI();
    }
}