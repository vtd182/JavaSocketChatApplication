package org.example.Views;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class HomeScreen {
    private JPanel HomeScreen;
    private JTextPane textPane1;
    private JTextArea textArea1;
    private JButton btn_send;
    private JPanel jp_user;
    private JPanel jp_msg;
    private JList list1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;

    public HomeScreen() {
        init();
    }

    public void init() {
        JFrame frame = new JFrame("HomeScreen");
        frame.setContentPane(HomeScreen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int defaultWidth = 900;
        int defaultHeight = 600;
        frame.setSize(defaultWidth, defaultHeight);

        ImageIcon originalIcon = new ImageIcon("Assets/send-message.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        btn_send.setIcon(new ImageIcon(scaledImage));
        btn_send.setOpaque(false);
        btn_send.setBorderPainted(false);

        // auto-fit the size of the frame
        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
