package org.example.Models;

import javax.swing.*;
import java.awt.*;

// this class is used to display the user list item with the user icon
public class UserRendered extends JPanel implements ListCellRenderer<User> {
    private JLabel lbIcon = new JLabel();
    private JLabel lbUsername = new JLabel();
    private JLabel lbStatus = new JLabel();
    private JLabel lbNewMessage = new JLabel();
    private User currentUser;

    private final String defaultIcon = "Assets/user_profile.png";
    public UserRendered(User currentUser) {
        this.currentUser = currentUser;
        setLayout(new BorderLayout(10, 10));
        //setBorder(BorderFactory.createMatteBorder(0, 0 , 1, 0, Color.gray));
        setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
        JPanel panelText = new JPanel(new GridLayout(0, 1));
        panelText.add(lbUsername);
        panelText.add(lbStatus);
        panelText.add(lbNewMessage);
        add(lbIcon, BorderLayout.WEST);
        add(panelText, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends User> list, User value, int index, boolean isSelected, boolean cellHasFocus) {
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(defaultIcon)
                .getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        lbIcon.setIcon(imageIcon);
        if(value.getUsername().equals(currentUser.getUsername())) {
            lbUsername.setText(value.getDisplayName() + " (You)");
        } else {
            lbUsername.setText(value.getDisplayName());
        }
        if(value.hasNewMessage()) {
            lbNewMessage.setText("You have new message!");
        } else {
            lbNewMessage.setText("");
        }
        lbStatus.setText(value.isConnected() ? "Online" : "Offline");
        lbStatus.setForeground(Color.GREEN);
        lbNewMessage.setForeground(Color.RED);
        lbNewMessage.setOpaque(true);
        lbUsername.setOpaque(true);
        lbStatus.setOpaque(true);
        lbIcon.setOpaque(true);
        // when select item
        if(isSelected) {
            Color selectionColor = list.getSelectionBackground();

            // Sử dụng AlphaComposite để đè lên màu trong suốt
            float alpha = 0.5f; // Điều chỉnh độ đậm của màu trong suốt
            Color translucentColor = new Color(selectionColor.getRed(), selectionColor.getGreen(), selectionColor.getBlue(), (int) (alpha * 255));

            float alpha2 = 0.44f; // Điều chỉnh độ đậm của màu trong suốt
            Color translucentColor1 = new Color(selectionColor.getRed(), selectionColor.getGreen(), selectionColor.getBlue(), (int) (alpha2 * 255));

            float alpha3 = 0f; // Điều chỉnh độ đậm của màu trong suốt
            Color translucentColor2 = new Color(selectionColor.getRed(), selectionColor.getGreen(), selectionColor.getBlue(), (int) (alpha3 * 255));

            lbUsername.setBackground(translucentColor1);
            lbStatus.setBackground(translucentColor1);
            lbIcon.setBackground(translucentColor2);
            lbNewMessage.setBackground(translucentColor1);

            setBackground(translucentColor);
        } else {
            // when don't select
            lbUsername.setBackground(list.getBackground());
            lbStatus.setBackground(list.getBackground());
            lbIcon.setBackground(list.getBackground());
            lbNewMessage.setBackground(list.getBackground());
            setBackground(list.getBackground());
        }
        return this;
    }
}
