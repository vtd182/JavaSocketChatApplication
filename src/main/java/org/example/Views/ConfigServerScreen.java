package org.example.Views;

import org.example.Controllers.ConfigServerScreenListener;
import org.example.Models.Client;
import org.example.Models.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ConfigServerScreen extends JFrame {
    private JPanel jp_config_port;
    private JLabel lb_port;
    private JLabel lb_title;
    private JPanel ConfigServerScreen;
    private JTextField tf_port;
    private JPanel jp_logs;
    private JLabel lb_logs;
    private JPanel logs;
    private JPanel user;
    private JTable table_client;
    private List<Client> clientsList;
    private JButton btn_process;
    private JTextArea txt_logs;
    private JLabel jl_users;
    private DefaultTableModel clientModel;
    public int getPort() {
        return Integer.parseInt(tf_port.getText());
    }
    public boolean isServerRunning = false;

    public void init() {
        this.setTitle("ConfigServerScreen");
        this.setContentPane(ConfigServerScreen);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int defaultWidth = 600;
        int defaultHeight = 400;
        this.setSize(defaultWidth, defaultHeight);
        btn_process.setText("Start server");
        setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public ConfigServerScreen() {
        init();
        clientsList = new ArrayList<>();
        clientModel = (DefaultTableModel) table_client.getModel();
        clientModel.setColumnIdentifiers(new Object[] {
                "STT", "IP address", "Port", "Username"
        });
        table_client.setModel(clientModel);
        ActionListener configServerScreenListener = new ConfigServerScreenListener(this);
        btn_process.addActionListener(configServerScreenListener);
    }

    public void writeToLogs(String log) {
        txt_logs.append(log + "\n");
    }
    public void updateButtonText() {
        if (isServerRunning) {
            btn_process.setText("Stop server");
        } else {
            btn_process.setText("Start server");
        }
    }

    public void addClient(Client client) {
        clientsList.add(client);
        jl_users.setText("Online Users: " + clientsList.size());
        updateClientTable();
    }

    public void removeClient(User user) {
        for (Client client : clientsList) {
            if (client.getUsername().equals(user.getUsername())) {
                clientsList.remove(client);
                jl_users.setText("Online Users: " + clientsList.size());
                updateClientTable();
                return;
            }
        }
    }
    public void updateClientTable() {
        clientModel.setRowCount(0);
        int clientIndex = 1;
        for (Client client : clientsList) {
            clientModel.addRow(new Object[] {
                clientIndex++, client.getAddress(), client.getPort(), client.getUsername()
            });
        }
    }
}
