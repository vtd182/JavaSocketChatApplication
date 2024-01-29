package org.example.Models;

public class User {
    private String username;
    private String password;
    private String displayName;
    private boolean isConnected;
    private boolean hasNewMessage;

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public boolean hasNewMessage() {
        return hasNewMessage;
    }

    public void setHasNewMessage(boolean hasNewMessage) {
        this.hasNewMessage = hasNewMessage;
    }
    public User(String name, String username, String password) {
        this.displayName = name;
        this.username = username;
        this.password = password;
        hasNewMessage = false;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }
    @Override
    public String toString() {
        return this.displayName + " - " + this.isConnected;
    }

}
