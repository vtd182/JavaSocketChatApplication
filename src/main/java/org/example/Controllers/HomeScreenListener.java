package org.example.Controllers;

import org.example.Views.HomeScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreenListener implements ActionListener {
    HomeScreen homeScreen;
    public HomeScreenListener(HomeScreen homeScreen) {
        this.homeScreen = homeScreen;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == homeScreen.getBtn_send()) {
            System.out.println("Send button clicked");
        }
        else if (e.getSource() == homeScreen.getBtn_like()) {
            System.out.println("Like button clicked");
        }
        else if (e.getSource() == homeScreen.getBtn_shock()) {
            System.out.println("Shock button clicked");
        }
        else if (e.getSource() == homeScreen.getBtn_happy()) {
            System.out.println("Happy button clicked");
        }
        else if (e.getSource() == homeScreen.getBtn_sad()) {
            System.out.println("Sad button clicked");
        }
        else if (e.getSource() == homeScreen.getBtn_smile()) {
            System.out.println("Smile button clicked");
        }
    }
}
