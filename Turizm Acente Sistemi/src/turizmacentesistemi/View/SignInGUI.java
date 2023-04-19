package turizmacentesistemi.View;

import turizmacentesistemi.Helper.Config;
import turizmacentesistemi.Helper.DBConnector;
import turizmacentesistemi.Helper.Helper;
import turizmacentesistemi.Model.Operator;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignInGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_email;
    private JButton btn_signin;
    private JTextField fld_name;
    private JPasswordField fld_pass;
    private JPasswordField fld_pass2;

    public SignInGUI() {
        add(wrapper);
        setSize(400, 400);
        setLocation(Helper.screenCenterPoint("x", getSize()), (Helper.screenCenterPoint("y", getSize())));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);


        btn_signin.addActionListener(e -> {
            String name = fld_name.getText();
            String email = fld_email.getText();
            String pass = String.valueOf(fld_pass.getPassword());
            String pass2 = String.valueOf(fld_pass2.getPassword());

            if (Helper.isFieldEmpty(fld_name) || Helper.isFieldEmpty(fld_email) || Helper.isFieldEmpty(fld_pass) || Helper.isFieldEmpty(fld_pass2)) {
                Helper.showMessage("fill");
            } else {
                if (Helper.isFieldEmpty(fld_name) || Helper.isFieldEmpty(fld_email) || Helper.isFieldEmpty(fld_pass) || Helper.isFieldEmpty(fld_pass2)) {
                    Helper.showMessage("fill");
                } else {
                    if (isPasswordsMatch(pass, pass2)) {
                        addNewOp(name, email, pass);
                        LogInGUI loginGUI = new LogInGUI();
                        dispose();
                    }
                }
            }
        });
    }

    private boolean isPasswordsMatch(String pass, String pass2) {
        if (!(pass.equals(pass2))) {
            Helper.showMessage("Şifreler uyuşmuyor, tekrar deneyin.");
            return false;
        }
        return true;
    }

    private boolean addNewOp(String name, String email, String pass) {
        String query = "INSERT INTO user (name, email, password) VALUES (?,?,?)";
        Operator findUser = Operator.getFetch(email);
        if (findUser != null) {
            Helper.showMessage("Bu email sistemde zaten mevcut!");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, email);
            pr.setString(3, pass);
            Helper.showMessage("done");
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            Helper.showMessage(e.getMessage());
        }
        return true;
    }
}