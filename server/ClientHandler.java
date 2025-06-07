package server;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ClientHandler extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String username;
    private final Map<String, ClientHandler> clients;

    public ClientHandler(Socket socket, Map<String, ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Authentication
            out.println("LOGIN_OR_REGISTER");
            String line = in.readLine();
            String[] parts = line.split(":", 3);
            if (parts.length != 3) {
                out.println("AUTH_FAILED");
                socket.close();
                return;
            }

            String action = parts[0];
            String user = parts[1];
            String pass = parts[2];

            boolean success = false;
            if ("LOGIN".equals(action)) {
                success = UserDAO.login(user, pass);
            } else if ("REGISTER".equals(action)) {
                success = UserDAO.register(user, pass);
            }

            if (!success || clients.containsKey(user)) {
                out.println("AUTH_FAILED");
                socket.close();
                return;
            }

            username = user;
            clients.put(username, this);
            out.println("AUTH_SUCCESS");
            broadcast(username + " joined the chat.");

            // Main loop
            String msg;
            while ((msg = in.readLine()) != null) {
                if (msg.equals("/users")) {
                    sendOnlineUsers();
                } else if (msg.startsWith("@")) {
                    handlePrivateMessage(msg);
                } else {
                    broadcast(username + ": " + msg);
                }
            }

        } catch (IOException e) {
            System.out.println("Error with client " + username);
        } finally {
            try {
                if (username != null) {
                    clients.remove(username);
                    broadcast(username + " left the chat.");
                }
                socket.close();
            } catch (IOException ignored) {}
        }
    }

    private void broadcast(String message) {
        for (ClientHandler client : clients.values()) {
            client.out.println(message);
        }
    }

    private void handlePrivateMessage(String msg) {
        int spaceIdx = msg.indexOf(' ');
        if (spaceIdx == -1) {
            out.println("Invalid private message format. Use @username message");
            return;
        }

        String targetUser = msg.substring(1, spaceIdx);
        String message = msg.substring(spaceIdx + 1);

        ClientHandler target = clients.get(targetUser);
        if (target != null) {
            target.out.println("[Private] " + username + ": " + message);
            out.println("[Private to " + targetUser + "]: " + message);
        } else {
            out.println("User '" + targetUser + "' is not online.");
        }
    }

    private void sendOnlineUsers() {
        StringBuilder sb = new StringBuilder("Online users: ");
        for (String user : clients.keySet()) {
            sb.append(user).append(" ");
        }
        out.println(sb.toString().trim());
    }
}
