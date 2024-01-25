package org.example.Helpers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import org.example.Models.Server;

import javax.swing.*;

public class ServerFileHandler {

    // Đường dẫn đến tệp lưu trữ danh sách máy chủ
    private static final String FILE_PATH = "serverList.txt";

    public static void writeServerList(List<Server> serverList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Server server : serverList) {
                // Ghi thông tin máy chủ vào tệp
                writer.write(server.getHostName() + "," + server.getPort() + "," + server.getIconName());
                writer.newLine(); // Xuống dòng cho máy chủ tiếp theo
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Server> readServerList() {
        List<Server> serverList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Phân tách thông tin máy chủ từ dòng
                String[] parts = line.split(",");
                String hostName = parts[0];
                int port = Integer.parseInt(parts[1]);
                Server server = new Server(hostName, port, "");
                serverList.add(server);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return serverList;
    }

    public static Server[] toArray(DefaultListModel<Server> model) {
        Server[] array = new Server[model.size()];

        for (int i = 0; i < model.size(); i++) {
            array[i] = model.getElementAt(i);
        }

        return array;
    }

    public static void checkAndCreateFile() {
        File file = new File(FILE_PATH);

        // Kiểm tra xem tệp tồn tại hay không
        if (!file.exists()) {
            try {
                // Tạo tệp nếu nó không tồn tại
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
