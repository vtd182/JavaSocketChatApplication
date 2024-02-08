package org.example.Threads;

import org.example.Models.Message;
import org.example.Models.User;
import org.example.Views.HomeScreen;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

// this class is used to read (received) the messages from the server or the client
public class ReadThread implements Runnable{
    private ObjectInputStream reader;
    private Socket clientSocket;
    private HomeScreen homeScreen;
    public ReadThread(HomeScreen homeScreen, Socket clientSocket) {
        this.homeScreen = homeScreen;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            reader = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        Message response = null;
        do {
            try {
                response = (Message) reader.readObject();
                switch (response.getType()) {
                    case "FETCH_USERS" -> {
                        System.out.println(response.getType());
                        ArrayList<User> users = (ArrayList<User>) response.getPayload();
                        for (User user : users) {
                            System.out.println(user.getDisplayName());
                        }
                        homeScreen.updateListUsers(users);
                        homeScreen.setDefaultUserSelection();
                        break;
                    }
                    case "USER_CONN" -> {
                        System.out.println("USER_CONN");
                        homeScreen.setUserOnline((User) response.getPayload());
                        break;
                    }
                    case "PRIVATE_MESSAGE" -> {
                        System.out.println("Has new message");
                        //client.onPrivateMessage(response);
                        break;
                    }
                    case "PRIVATE_FILE_MESSAGE" -> {
                        System.out.println("Has new file");
                        //User from = new DAO().getUserById(response.getFrom());
                        //client.onPrivateFileMessage(reader, from);
                        break;
                    }
                    case "USER_DISCONNECT" -> {
                        System.out.println("A user disconnected");
                        //client.setUserOffLine((User) response.getPayload());
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }

        } while (true);
    }
}
