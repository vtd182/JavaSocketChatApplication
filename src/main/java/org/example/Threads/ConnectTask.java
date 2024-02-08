package org.example.Threads;

import org.example.Models.Server;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.function.Consumer;
import javax.swing.SwingWorker;

public class ConnectTask extends SwingWorker<Socket, Void> {
    private final Server selectedServer;
    private final Consumer<String> errorCallback;
    private final Consumer<Socket> successCallback;

    public ConnectTask(Server selectedServer, Consumer<String> errorCallback, Consumer<Socket> successCallback) {
        this.selectedServer = selectedServer;
        this.errorCallback = errorCallback;
        this.successCallback = successCallback;
    }

    @Override
    protected Socket doInBackground() {
        try {
            System.out.println("Connecting to server: " + selectedServer);

            // set timeout for connection
            int timeout = 5000; // 5 seconds
            Socket clientSocket = new Socket();
            clientSocket.connect(new InetSocketAddress(selectedServer.getHostName(), selectedServer.getPort()), timeout);


            System.out.println("Connected to server!");

            // Call the success callback
            successCallback.accept(clientSocket);

            // return the client socket
            return clientSocket;
        } catch (Exception e) {
            // Handle the error and call the error callback
            String errorMessage = "Cannot connect to server: " + e.getMessage();
            System.err.println(errorMessage);
            errorCallback.accept(errorMessage);
            return null;
        }
    }
}
