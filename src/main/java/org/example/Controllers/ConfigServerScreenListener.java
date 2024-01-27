package org.example.Controllers;

import org.example.Views.ConfigServerScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigServerScreenListener implements ActionListener {
    ConfigServerScreen configServerScreen;

    public ConfigServerScreenListener(ConfigServerScreen configServerScreen) {
        this.configServerScreen = configServerScreen;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        System.out.println(action);
        if (!configServerScreen.isServerRunning) {
            try {
                configServerScreen.writeToLogs("Server is running on port " + configServerScreen.getPort());
            } catch (NumberFormatException exception) {
                configServerScreen.writeToLogs("Cannot start server: Invalid port");
                return;
            }
        } else {
            configServerScreen.writeToLogs("Stopping server...");
        }
        configServerScreen.isServerRunning = !configServerScreen.isServerRunning;
        configServerScreen.updateButtonText();
    }
}
