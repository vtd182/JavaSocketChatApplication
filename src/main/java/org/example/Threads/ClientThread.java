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
        try {
            this.clientSocket = clientSocket;
            this.clientThreads = clientThreads;
            this.configServerScreen = configServerScreen;
            System.out.println("ClientThread constructor: Initializing streams...");

            out = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
            System.out.println("ClientThread constructor: Output stream initialized successfully.");
            System.out.println("Socket isClose: " + clientSocket.isClosed());
            if (clientSocket.getInputStream() == null) {
                System.out.println("ClientThread constructor: Input stream is null.");
            } else {
                System.out.println("ClientThread constructor: Input stream is not null.");
            }
            System.out.println("Socket isClose: " + clientSocket.isClosed());
            in = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            System.out.println("ClientThread constructor: Input stream initialized successfully.");

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
        System.out.println("Client thread running...");
    }

}
