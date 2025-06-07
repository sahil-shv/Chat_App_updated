package client;

import java.io.*;
import java.net.*;
import javax.swing.*;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ChatGUI gui;

    public Client(String username) {
        try {
            int port = readPortFromFile();
            socket = new Socket("localhost", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(username);
            gui = new ChatGUI(this, username);

            new Thread(() -> {
                String msg;
                try {
                    while ((msg = in.readLine()) != null) {
                        gui.appendMessage(msg);
                    }
                } catch (IOException e) {
                    gui.appendMessage("❌ Connection lost.");
                }
            }).start();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "❌ Cannot connect to server. Is it running?");
        }
    }

    private int readPortFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("port.config"))) {
            return Integer.parseInt(reader.readLine().trim());
        } catch (Exception e) {
            throw new IOException("⚠️ Could not read port from config.");
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void disconnect() {
        try {
            if (socket != null) socket.close();
        } catch (IOException ignored) {}
    }

    public static void main(String[] args) {
        String username = JOptionPane.showInputDialog("Enter your username:");
        if (username == null || username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username is required.");
            return;
        }
        new Client(username.trim());
    }
}
