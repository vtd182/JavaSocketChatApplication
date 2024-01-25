package org.example.Models;

import javax.swing.*;
import java.awt.*;

// this class is used to display the server list item with the server icon
public class ServerRendered extends DefaultListCellRenderer {
    private final String defautIcon = "Assets/serverIcon.png";
    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        // Extract server information
        Server server = (Server) value;
        String address = server.getHostName();
        String port = String.valueOf(server.getPort());

        // Set text and icon for the JLabel
        label.setText("<html><b>" + address + "</b><br>Port: " + port + "</html>");

        // Load the original image
        ImageIcon originalIcon = new ImageIcon(defautIcon);

        // Get the image and scale it to a smaller size (e.g., 32x32)
        Image scaledImage = originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);

        // Set the scaled image as the icon
        label.setIcon(new ImageIcon(scaledImage));

        return label;
    }
}
