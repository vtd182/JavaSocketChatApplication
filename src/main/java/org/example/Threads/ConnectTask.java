package org.example.Threads;

import org.example.Models.Server;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.function.Consumer;
import javax.swing.SwingWorker;

public class ConnectTask extends SwingWorker<Void, Void> {
    private final Server selectedServer;
    private final Consumer<String> errorCallback;
    private final Runnable successCallback;

    public ConnectTask(Server selectedServer, Consumer<String> errorCallback, Runnable successCallback) {
        this.selectedServer = selectedServer;
        this.errorCallback = errorCallback;
        this.successCallback = successCallback;
    }

    @Override
    protected Void doInBackground() {
        try {
            System.out.println("Connecting to server: " + selectedServer);

            // Thiết lập timeout cho kết nối
            int timeout = 5000; // Timeout là 5 giây (có thể điều chỉnh theo nhu cầu)
            Socket clientSocket = new Socket();
            clientSocket.connect(new InetSocketAddress(selectedServer.getHostName(), selectedServer.getPort()), timeout);

            // Thực hiện các thao tác cần thiết sau khi kết nối thành công.
            System.out.println("Connected to server!");

            // Gọi callback khi kết nối thành công
            successCallback.run();
        } catch (Exception e) {
            // Xử lý lỗi và thông báo cho listener
            String errorMessage = "Cannot connect to server: " + e.getMessage();
            System.err.println(errorMessage);
            errorCallback.accept(errorMessage);
        }
        return null;
    }
}

