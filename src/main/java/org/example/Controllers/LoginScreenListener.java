package org.example.Controllers;

import org.example.Views.LoginScreen;
import org.example.Views.RegisterScreen;

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
                System.out.println("Login");
                System.out.println(loginScreen.getUsername());
                System.out.println(loginScreen.getPassword());
                break;
            case "Register":
                System.out.println("Register");
                navigateToRegisterScreen();
                loginScreen.setVisible(false);
                break;
            default:
                System.out.println("Nothing");
                break;
        }
    }

    private void navigateToRegisterScreen() {
        System.out.println("navigateToRegisterScreen");
        RegisterScreen registerScreen = new RegisterScreen();
        registerScreen.init();
    }
}
