package org.example.Views;

import org.example.Controllers.AllServersScreenListener;
import org.example.Helpers.ServerFileHandler;
import org.example.Models.Server;
import org.example.Models.ServerRendered;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import static org.example.Helpers.ServerFileHandler.checkAndCreateFile;
import static org.example.Helpers.ServerFileHandler.toArray;

public class AllServersScreen extends JFrame {

    private final String defautIcon = "/Assets/Images/serverIcon.png";
    private JPanel AllServersScreen;
    private JList<Server> ServerList;
    private JLabel lb_title;
    private JButton btn_add;
    private JButton btn_delete;
    private JButton btn_edit;
    private JButton btn_connect;

    DefaultListModel<Server> serverListModel;

    public AllServersScreen() {
        this.setTitle("AllServersScreen");
        this.setContentPane(AllServersScreen);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int defaultWidth = 600;
        int defaultHeight = 400;
        this.setSize(defaultWidth, defaultHeight);
        setLocationRelativeTo(null);
        this.setVisible(true);

        // Khởi tạo DefaultListModel cho JList
        serverListModel = new DefaultListModel<>();
        ServerList.setModel(serverListModel);
        ServerList.setCellRenderer(new ServerRendered());

        ActionListener allServersScreenListener = new AllServersScreenListener(this);
        btn_add.addActionListener(allServersScreenListener);
        btn_delete.addActionListener(allServersScreenListener);
        btn_edit.addActionListener(allServersScreenListener);
        btn_connect.addActionListener(allServersScreenListener);

        loadServerList();
    }

    public void loadServerList() {
        checkAndCreateFile();
        var loadedServerList = ServerFileHandler.readServerList();
        serverListModel.clear();
        serverListModel.addAll(loadedServerList);
    }

    public void showAddServerDialog() {
        JPanel panel = new JPanel(new GridLayout(2, 2)); // 2 dòng, 2 cột
        JTextField addressField = new JTextField();
        JTextField portField = new JTextField();

        panel.add(new JLabel("Address:"));
        panel.add(addressField);
        panel.add(new JLabel("Port:"));
        panel.add(portField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add Server",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String address = addressField.getText();
            String port = portField.getText();
            if (!address.isEmpty() && !port.isEmpty()) {
                try {
                    int portInt = Integer.parseInt(port);
                    Server newServer = new Server(address, portInt, "");
                    serverListModel.addElement(newServer);

                    Server[] serverArray = toArray(serverListModel);
                    ServerFileHandler.writeServerList(List.of(serverArray));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid port number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void showEditServerDialog(int selectedIndex, Server selectedValue) {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField addressField = new JTextField(selectedValue.getHostName());
        JTextField portField = new JTextField(String.valueOf(selectedValue.getPort()));

        panel.add(new JLabel("Address:"));
        panel.add(addressField);
        panel.add(new JLabel("Port:"));
        panel.add(portField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit Server",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String newAddress = addressField.getText();
            String newPortText = portField.getText();

            if (!newAddress.isEmpty() && !newPortText.isEmpty()) {
                try {
                    int newPort = Integer.parseInt(newPortText);
                    Server updatedServer = new Server(newAddress, newPort, "");
                    serverListModel.setElementAt(updatedServer, selectedIndex);
                    Server[] serverArray = toArray(serverListModel);
                    ServerFileHandler.writeServerList(List.of(serverArray));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid port number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteSelectedServer(int selectedIndex) {
        int option = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this server?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            serverListModel.removeElementAt(selectedIndex);
        }
    }

    public void deleteSelectedServer() {
        // Đảm bảo lấy chỉ mục của phần tử được chọn trong JList
        int selectedIndex = ServerList.getSelectedIndex();
        if (selectedIndex != -1) {
            deleteSelectedServer(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a server to delete.", "Delete Server", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void showEditServerDialog() {
        // Đảm bảo lấy chỉ mục của phần tử được chọn trong JList
        int selectedIndex = ServerList.getSelectedIndex();
        if (selectedIndex != -1) {
            Server selectedValue = serverListModel.getElementAt(selectedIndex);
            System.out.println("selectedIndex: " + selectedIndex);
            System.out.println("selectedValue: " + selectedValue);
            showEditServerDialog(selectedIndex, selectedValue);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a server to edit.", "Edit Server", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public Server getSelectedServer() {
        int selectedIndex = ServerList.getSelectedIndex();
        if (selectedIndex != -1) {
            return serverListModel.getElementAt(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a server to connect.", "Connect to Server", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
    }
}
