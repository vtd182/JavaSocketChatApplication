package org.example.Models;

public class User {

    private String id;
    private String username;
    private String password;
    private String name;
    private boolean isConnected;
    private boolean hasNewMessage;

    public User(String id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        hasNewMessage = false;
    }

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        hasNewMessage = false;
    }

    public String getUsername() {
        return username;
    }
    @Override
    public String toString() {
        return this.name + " - " + this.isConnected;
    }

}
