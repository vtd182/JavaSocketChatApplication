package org.example.Views;

import org.example.Controllers.RegisterScreenListener;

import javax.swing.*;

public class RegisterScreen extends JFrame {
    private JPanel RegisterScreen;
    private JPanel jp_username;
    private JTextField tf_username;
    private JLabel lb_username;
    private JPasswordField pwf_password;
    private JPasswordField pwf_confirmPassword;
    private JButton btn_register;
    private JButton btn_back;
    private JTextField tf_name;
    private JLabel lb_password;
    private JLabel lb_confirmPassword;
    private JLabel lb_register;
    private JLabel lb_name;


    public RegisterScreen() {
        RegisterScreenListener registerScreenListener = new RegisterScreenListener(this);
        btn_register.addActionListener(registerScreenListener);
        btn_back.addActionListener(registerScreenListener);
    }

    public String getUsername() {
        return tf_username.getText();
    }

    public String getPassword() {
        return pwf_password.getText();
    }

    public String getConfirmPassword() {
        return pwf_confirmPassword.getText();
    }

    public String getName() {
        return tf_name.getText();
    }

    public void init() {
        this.setTitle("RegisterScreen");
        this.setContentPane(RegisterScreen);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int defaultWidth = 600;
        int defaultHeight = 400;
        this.setSize(defaultWidth, defaultHeight);
        setLocationRelativeTo(null);
        // auto-fit the size of the frame
        //frame.pack();
        this.setVisible(true);
    }
}
