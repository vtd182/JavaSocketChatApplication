package org.example.Views;

import org.example.Controllers.LoginScreenListener;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
    private JPanel username_panel;
    private JLabel lb_username;
    private JTextField tf_username;
    private JPanel password_panel;
    private JLabel lb_password;
    private JPasswordField pf_password;
    private JPanel login_panel;
    private JLabel lb_login;
    private JButton btn_login;
    private JButton btn_register;
    private JPanel LoginScreen;

    public LoginScreen() {
        init();
        ActionListener loginScreenListener = new LoginScreenListener(this);
        btn_login.addActionListener(loginScreenListener);
        btn_register.addActionListener(loginScreenListener);
    }

    public String getUsername() {
        return tf_username.getText();
    }
    public String getPassword() {
        return pf_password.getText();
    }
    public void init() {
        this.setTitle("LoginScreen");
        this.setContentPane(LoginScreen);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int defaultWidth = 600;
        int defaultHeight = 400;
        this.setSize(defaultWidth, defaultHeight);

        // auto-fit the size of the frame
        //frame.pack();
        setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
