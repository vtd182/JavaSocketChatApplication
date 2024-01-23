package org.example.Controllers;

import org.example.Views.LoginScreen;
import org.example.Views.RegisterScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterScreenListener implements ActionListener {
    private RegisterScreen registerScreen;

    public RegisterScreenListener(RegisterScreen registerScreen) {
        this.registerScreen = registerScreen;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        switch (src) {
            case "Register":
                System.out.println("Register");
                onRegister(registerScreen.getUsername(),
                        registerScreen.getPassword(),
                        registerScreen.getConfirmPassword(),
                        registerScreen.getName());
                break;
            case "Back to login":
                System.out.println("Back");
                navigateToLoginScreen();
                registerScreen.setVisible(false);
                break;
            default:
                System.out.println("Nothing");
                break;
        }
    }

    private void navigateToLoginScreen() {
        System.out.println("navigateToLoginScreen");
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.init();
    }

    private void onRegister(String username, String password, String confirmPassword, String name) {
        System.out.println("onRegister");
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(registerScreen, "Please fill all the fields");
        } else if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(registerScreen, "Password and Confirm Password are not the same");
        } else {
            // TODO: register from database
            JOptionPane.showMessageDialog(registerScreen, "Register successfully");
            navigateToLoginScreen();
            registerScreen.setVisible(false);
        }
    }
}
