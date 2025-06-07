package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ChatServer {
    public static void main(String[] args) {
        Map<String, ClientHandler> clients = new HashMap<>();
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Chat Server started on port 12345.");
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(socket, clients);
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
