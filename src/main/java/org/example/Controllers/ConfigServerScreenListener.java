package org.example.Controllers;

import org.example.Threads.ClientThread;
import org.example.Views.ConfigServerScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConfigServerScreenListener implements ActionListener {
    ConfigServerScreen configServerScreen;
    private final AtomicBoolean running = new AtomicBoolean(false);
    public ServerSocket myServerSocket;
    public Thread serverThread;
    public static ExecutorService threadPool = Executors.newFixedThreadPool(100);

    public ArrayList<ClientThread> clientThreads = new ArrayList<>();

    public ConfigServerScreenListener(ConfigServerScreen configServerScreen) {
        this.configServerScreen = configServerScreen;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        System.out.println(action);
        if (!configServerScreen.isServerRunning)
        {
            // Start server
            try {
                int PORT = configServerScreen.getPort();
                configServerScreen.writeToLogs("Starting server...");
                startServer(PORT);
            } catch (NumberFormatException exception) {
                configServerScreen.writeToLogs("Cannot start server: Invalid port");
                return;
            }
        } else {
            // Stop server
            configServerScreen.writeToLogs("Stopping server...");
            stopServer();
        }
        configServerScreen.isServerRunning = !configServerScreen.isServerRunning;
        configServerScreen.updateButtonText();
    }

    public void startServer(int PORT) {
        System.out.println("Starting server...");
        running.set(true);
        serverThread = new Thread(() -> {
            System.out.println("Server is running...");
            try
            {
                myServerSocket = new ServerSocket(PORT);
                configServerScreen.writeToLogs("Server listening on port " + PORT);
                try {
                    while (running.get()) {
                        configServerScreen.writeToLogs("Waiting for client...");
                        configServerScreen.validate();
                        Socket clientSocket = myServerSocket.accept();
                        configServerScreen.writeToLogs("A user connected!");
                        configServerScreen.validate();
                        ClientThread client = new ClientThread(configServerScreen, clientSocket, clientThreads);
                        System.out.println("Executing client thread...");
                        clientThreads.add(client);
                        threadPool.execute(client);
                    }
                } catch (Exception e) {
                    if (!myServerSocket.isClosed()) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        });
        serverThread.start();
    }

    public void stopServer() {
        running.set(false);
        try {
            for (ClientThread client : clientThreads) {
                client.getClientSocket().close();
            }
            System.out.println("Closing server socket...");
            myServerSocket.close();
            configServerScreen.writeToLogs("Server stopped!");
            configServerScreen.validate();
        } catch (IOException e) {
            configServerScreen.writeToLogs("Error while stopping server: " + e.getMessage());
        }
    }
}
