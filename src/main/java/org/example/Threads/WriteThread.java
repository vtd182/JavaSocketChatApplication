package org.example.Threads;

import org.example.Models.Message;
import org.example.Models.User;
import org.example.Views.HomeScreen;

import java.io.ObjectOutputStream;
import java.net.Socket;

// this class is used to write (send) the messages from the server or the client
public class WriteThread implements Runnable {
    private HomeScreen homeScreen;
    private Socket clientSocket;
    private User currentUser;
    private Message message;
    private ObjectOutputStream writer;


    public WriteThread(HomeScreen homeScreen, Socket clientSocket, User currentUser, Message message, ObjectOutputStream writer) {
        this.homeScreen = homeScreen;
        this.clientSocket = clientSocket;
        this.currentUser = currentUser;
        this.message = message;
        this.writer = writer;
    }
    @Override
    public void run() {

    }
}
