package org.example.Controllers;

import org.example.Views.AllServersScreen;
import org.example.Views.LoginScreen;
import org.example.Views.RegisterScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreenListener implements ActionListener {

    private LoginScreen loginScreen;

    public LoginScreenListener(LoginScreen loginScreen) {
        this.loginScreen = loginScreen;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        switch (src) {
            case "Login":
                onLogin();
                break;
            case "Register":
                navigateToRegisterScreen();
                loginScreen.setVisible(false);
                break;
            default:
                System.out.println("Nothing");
                break;
        }
    }

    private void navigateToRegisterScreen() {
        RegisterScreen registerScreen = new RegisterScreen();
        registerScreen.init();
    }

    private void navigateToAllServersScreen() {
        AllServersScreen allServersScreen = new AllServersScreen();
    }

    private void onLogin() {
        String username = loginScreen.getUsername();
        String password = loginScreen.getPassword();
        System.out.println("onLogin");
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        if (validateLogin(username, password)) {
            JOptionPane.showMessageDialog(loginScreen, "Login successfully");
            navigateToAllServersScreen();
            loginScreen.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(loginScreen, "Login failed");
        }
    }

    private boolean validateLogin(String username, String password) {
        boolean isValid = false;
        try {
            // TODO: validate username and password
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
