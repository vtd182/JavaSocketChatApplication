package org.example.Views;

import javax.swing.*;

public class ConfigServerScreen extends JFrame {
    private JPanel jp_config_port;
    private JLabel lb_port;
    private JLabel lb_title;
    private JPanel ConfigServerScreen;
    private JTextField tf_port;
    private JPanel jp_logs;
    private JTextField tf_logs;
    private JLabel lb_logs;
    private JPanel logs;
    private JPanel user;
    private JTable table_client;
    private JButton button1;

    public ConfigServerScreen() {
        this.setTitle("ConfigServerScreen");
        this.setContentPane(ConfigServerScreen);
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
