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

    }
}
