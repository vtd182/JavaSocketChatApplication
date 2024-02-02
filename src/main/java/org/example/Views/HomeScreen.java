package org.example.Views;

import org.example.Controllers.HomeScreenListener;
import org.example.Models.Message;
import org.example.Models.Server;
import org.example.Models.User;
import org.example.Threads.ReadThread;
import org.example.Threads.WriteThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.net.Socket;

public class HomeScreen extends JFrame{
    private JPanel HomeScreen;
    private JTextPane display_message;
    private JTextArea type_message;
    private JButton btn_send;
    private JPanel jp_user;
    private JPanel jp_msg;
    private JList ls_user_list;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private Socket clientSocket;
    private Server server;
    private User currentUser;

    private ObjectOutputStream writer;

    public ObjectOutputStream getObjectOutputStream() {
        return this.writer;
    }

    public HomeScreen(Socket clientSocket, Server server, User currentUser) throws IOException {
        init();
        this.clientSocket = clientSocket;
        this.server = server;
        this.currentUser = currentUser;
        this.setTitle("Bạn đang đăng nhập với tên: " + currentUser.getDisplayName());
        ActionListener homeScreenListener = new HomeScreenListener(this);
        btn_send.addActionListener(homeScreenListener);

        // ------
        // Initialize writer
        try {
            writer = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ------
        // Add listener for window
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Message disConnect = new Message("USER_DISCONNECT", currentUser);
                Thread privateThread = new Thread(new WriteThread(HomeScreen.this, clientSocket, currentUser, disConnect, writer));
                privateThread.start();
                System.out.println("Close");
            }
        });

        Thread readThread = new Thread(new ReadThread(this, clientSocket));
        readThread.start();
        Message sessionEvent = new Message("SESSION", currentUser);
        Thread writeThread = new Thread(new WriteThread(HomeScreen.this, clientSocket, currentUser, sessionEvent, writer));
        writeThread.start();
    }

    public void init() {
        this.setContentPane(HomeScreen);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int defaultWidth = 900;
        int defaultHeight = 600;
        this.setSize(defaultWidth, defaultHeight);

        ImageIcon originalIcon = new ImageIcon("Assets/send-message.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        btn_send.setIcon(new ImageIcon(scaledImage));
        btn_send.setOpaque(false);
        btn_send.setBorderPainted(false);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
