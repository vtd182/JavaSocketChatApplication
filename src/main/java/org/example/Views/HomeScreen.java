package org.example.Views;

import org.example.Controllers.HomeScreenListener;
import org.example.Models.Message;
import org.example.Models.Server;
import org.example.Models.User;
import org.example.Models.UserRendered;
import org.example.Threads.ReadThread;
import org.example.Threads.WriteThread;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends JFrame{
    private JPanel HomeScreen;
    private JTextPane display_message;
    private JTextArea type_message;
    private JButton btn_send;
    private JPanel jp_user;
    private JPanel jp_msg;
    private JList ls_user_list;
    private JButton btn_like;
    private JButton btn_shock;
    private JButton btn_happy;
    private JButton btn_sad;
    private JButton btn_smile;
    private Socket clientSocket;
    private Server server;
    private User currentUser;
    private DefaultListModel<User> listModel;

    private HTMLDocument htmlDocument;

    private ObjectOutputStream writer;
    private List<User> usersList;
    public ObjectOutputStream getObjectOutputStream() {
        return this.writer;
    }

    public HomeScreen(Socket clientSocket, Server server, User currentUser) throws IOException {
        initComponents();
        initListeners();

        this.usersList = new ArrayList<>();
        this.clientSocket = clientSocket;
        this.server = server;
        this.currentUser = currentUser;
        this.setTitle("Current user's name: " + currentUser.getDisplayName());

        // ------
        // Initialize writer
        try {
            writer = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ------
        // Initialize listModel
        listModel = new DefaultListModel<>();
        ls_user_list.setModel(listModel);
        ls_user_list.setCellRenderer(new UserRendered(currentUser));


        // ------
        // Initialize threads
        Thread readThread = new Thread(new ReadThread(this, clientSocket));
            readThread.start();
        Message sessionEvent = new Message("SESSION", currentUser);
        Thread writeThread = new Thread(new WriteThread(HomeScreen.this, clientSocket, currentUser, sessionEvent, writer));
        writeThread.start();
    }

    private void initListeners() {
        ActionListener homeScreenListener = new HomeScreenListener(this);
        btn_send.addActionListener(homeScreenListener);
        btn_like.addActionListener(homeScreenListener);
        btn_shock.addActionListener(homeScreenListener);
        btn_happy.addActionListener(homeScreenListener);
        btn_sad.addActionListener(homeScreenListener);
        btn_smile.addActionListener(homeScreenListener);

        // Add listener for window
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Message disConnect = new Message("USER_DISCONNECT", currentUser);
                Thread privateThread = new Thread(
                        new WriteThread(HomeScreen.this,
                                clientSocket, currentUser, disConnect, writer)
                );
                privateThread.start();
                System.out.println("Close");
            }
        });
    }

    public void initComponents() {
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

        htmlDocument = (HTMLDocument) display_message.getDocument();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setUserOnline(User u) {
        u.setConnected(true);
        usersList.add(u);
        listModel.addElement(u);
    }
    public void updateListUsers(ArrayList<User> users) {
        // put current user at the top of the list
        usersList = users;
        listModel.clear();
        ArrayList<User> temp = new ArrayList<>();
        for (User user : users) {
            if (!user.getUsername().equals(currentUser.getUsername())) {
                temp.add(user);
            } else {
                listModel.add(0, user);
            }
        }
        listModel.addAll(temp);
    }

    public void setDefaultUserSelection() {
        ls_user_list.setSelectedIndex(0);
    }

    public JButton getBtn_send() {
        return btn_send;
    }

    public JButton getBtn_like() {
        return btn_like;
    }

    public JButton getBtn_shock() {
        return btn_shock;
    }

    public JButton getBtn_happy() {
        return btn_happy;
    }

    public JButton getBtn_sad() {
        return btn_sad;
    }

    public JButton getBtn_smile() {
        return btn_smile;
    }
}
