package patikadev.View;

import patikadev.Helper.Config;
import patikadev.Helper.DBConnector;
import patikadev.Helper.Helper;
import patikadev.Model.User;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignInGUI extends JFrame {
    private JPanel wrapper;
    private JPanel w_top;
    private JPanel w_bottom;
    private JTextField fld_name;
    private JTextField fld_uname;
    private JTextField fld_pass;
    private JTextField fld_pass2;
    private JButton btn_signIn;

    public SignInGUI() {
        add(wrapper);
        setSize(800, 450);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);


        btn_signIn.addActionListener(e -> {
            String pass = fld_pass.getText();
            String pass2 = fld_pass2.getText();
            String name = fld_name.getText();
            String uname = fld_uname.getText();

            if (Helper.isFieldEmpty(fld_name) || Helper.isFieldEmpty(fld_pass) || Helper.isFieldEmpty(fld_pass2) || Helper.isFieldEmpty(fld_uname)) {
                Helper.showMessage("fill");
            } else {
                if (Helper.isFieldEmpty(fld_uname) || Helper.isFieldEmpty(fld_name) || Helper.isFieldEmpty(fld_pass) || Helper.isFieldEmpty(fld_pass2)) {
                    Helper.showMessage("fill");
                } else {
                    if (isPasswordsMatch(pass, pass2)) {
                        addNewStudent(name, uname, pass);
                        LoginGUI loginGUI = new LoginGUI();
                        dispose();
                    }

                }
            }
        });
    }

    private boolean addNewStudent(String name, String uname, String pass) {
        String query = "INSERT INTO user (name, uname, password, type) VALUES (?,?,?,'student')";
        User findUser = User.getFetch(uname);
        if(findUser != null){
            Helper.showMessage("Bu kullanıcı adı zaten mevcut, farklı bir kullanıcı adı girin.");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,uname);
            pr.setString(3,pass);
            Helper.showMessage("done");
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            Helper.showMessage(e.getMessage());
        }
        return true;
    }

    private boolean isPasswordsMatch(String pass, String pass2) {
        if (!(pass.equals(pass2))) {
            Helper.showMessage("Şifreler uyuşmuyor, tekrar deneyin.");
            return false;
        }
        return true;
    }
}