package org.example.Views;

import org.example.Controllers.ConfigServerScreenListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

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
    private JButton btn_process;
    private JTextArea txt_logs;
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
}
