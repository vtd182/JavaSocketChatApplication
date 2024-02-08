package org.example.Threads;

import org.example.Models.Client;
import org.example.Models.Message;
import org.example.Models.User;
import org.example.Views.ConfigServerScreen;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;


/// This class is used to handle the client socket
public class ClientThread implements Runnable{
    private Socket clientSocket;
    private ArrayList<ClientThread> clientThreads;
    private User currentUser;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ConfigServerScreen configServerScreen;


    // todo: Khi user đăng nhập, lập tức thông báo và đồng bộ hóa list user cho từng thằng.
    public ClientThread(ConfigServerScreen configServerScreen, Socket clientSocket, ArrayList<ClientThread> clientThreads)
            throws IOException {
        try {
            this.clientSocket = clientSocket;
            this.clientThreads = clientThreads;
            this.configServerScreen = configServerScreen;
            out = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
            in = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in ClientThread constructor: " + e.getMessage());
        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            Message request = null;
            do {
                request = (Message) in.readObject();
                var type = request.getType();
                switch (type) {
                    case "SESSION": {
                        // Get the user from the payload
                        currentUser = (User) request.getPayload();
                        configServerScreen.writeToLogs("User " + currentUser.getDisplayName() + " is trying to connect");

                        String address = ((InetSocketAddress) clientSocket.getRemoteSocketAddress()).getAddress().toString();
                        int port = clientSocket.getPort();
                        Client client = new Client(currentUser, address, port);

                        // Add client to the list of clients
                        configServerScreen.writeToLogs("User " + currentUser.getDisplayName() + " has connected");
                        configServerScreen.addClient(client);

                        Message userConnMsg = new Message("USER_CONN", currentUser);
                        broadcastAll(userConnMsg);

                        // Send the list of users to the client
                        Message fetchUserMsg = new Message("FETCH_USERS", getAllUsers());
                        sendMessage(fetchUserMsg);

                        break;
                    }
                    case "USER_DISCONNECT": {
                        configServerScreen.writeToLogs("User " + currentUser.getDisplayName() + " has disconnected");
                        configServerScreen.removeClient(currentUser);
                        break;
                    }
                }
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in ClientThread run: " + e.getMessage());
        } finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error in ClientThread run finally: " + e.getMessage());
            }
        }
    }

    public void sendMessage(Message serverMessage) {
        try {
            out.writeObject(serverMessage);
            out.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public User getUser() {
        return currentUser;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        for (ClientThread aClient : clientThreads) {
            User user = aClient.getUser();
            user.setConnected(true);
            users.add(user);
        }
        return users;
    }

    // This method is used to broadcast a message to all the clients
    public void broadcastAll(Message message) {
        for (ClientThread aClient : clientThreads) {
            if (!aClient.getUser().getUsername().equals(this.getUser().getUsername())) {
                aClient.sendMessage(message);
            }
        }
    }
}
