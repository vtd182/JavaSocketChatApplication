package org.example.Controllers;

import org.example.Models.Server;
import org.example.Threads.ConnectTask;
import org.example.Views.AllServersScreen;
import org.example.Views.HomeScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

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
                onConnect();
                break;
            default:
                System.out.println("Nothing");
                break;
        }
    }


    // todo: implement timeout for connect, and show error message, UI if timeout
    private void onConnect() {
        Server selectedServer = allServersScreen.getSelectedServer();
        if (selectedServer == null) {
            return;
        }

        ConnectTask connectTask = new ConnectTask(selectedServer,
                errorMessage -> System.out.println(errorMessage),
                () -> navigateToHomeScreen());

        connectTask.execute();
    }

    private void navigateToHomeScreen() {
        HomeScreen homeScreen = new HomeScreen();
        allServersScreen.setVisible(false);
    }
}
