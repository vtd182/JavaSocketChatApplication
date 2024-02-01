package org.example.Threads;

import org.example.Models.User;
import org.example.Views.AllServersScreen;
import org.example.Views.ConfigServerScreen;

import java.io.*;
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

    public ClientThread(ConfigServerScreen configServerScreen, Socket clientSocket, ArrayList<ClientThread> clientThreads)
            throws IOException {
        this.clientSocket = clientSocket;
        this.clientThreads = clientThreads;
        this.configServerScreen = configServerScreen;

        in = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
        out = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }


    @Override
    public void run() {
        configServerScreen.writeToLogs("Client connected: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
        System.out.println("Client connected: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
    }
}
