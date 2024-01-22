package org.example.Views;

import javax.swing.*;

public class LoginScreen {
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginScreen");
        frame.setContentPane(new LoginScreen().LoginScreen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int defaultWidth = 600;
        int defaultHeight = 400;
        frame.setSize(defaultWidth, defaultHeight);

        // auto-fit the size of the frame
        //frame.pack();

        frame.setVisible(true);
    }
}
