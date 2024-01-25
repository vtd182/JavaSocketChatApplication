package org.example.Controllers;

import org.example.Views.AllServersScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AllServersScreenListener implements ActionListener {
    private AllServersScreen allServersScreen;

    public AllServersScreenListener(AllServersScreen allServersScreen) {
        this.allServersScreen = allServersScreen;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        switch (src) {
            case "Add":
                allServersScreen.showAddServerDialog();
                break;
            case "Delete":
                allServersScreen.deleteSelectedServer();
                break;
            case "Edit":
                allServersScreen.showEditServerDialog();
                break;
            case "Connect":
                break;
            default:
                System.out.println("Nothing");
                break;
        }
    }
}
