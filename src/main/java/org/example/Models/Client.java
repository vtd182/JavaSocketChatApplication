package org.example.Models;

import java.io.Serializable;

public class Client implements Serializable {
    private User user;
    private String address;
    private int port;

    public Client(User user, String address, int port) {
        this.user = user;
        this.address = address;
        this.port = port;
    }

    public Client(User user) {
        this.user = user;
        address = "";
        port = 0;
    }

    public User getUser() {
        return user;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return user.getUsername();
    }
    public void setUser(User user) {
        this.user = user;
    }
}
