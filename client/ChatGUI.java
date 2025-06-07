package client;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import javax.swing.*;

public class ChatGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;
    private String username;

    public ChatGUI() {
        setupUI();
        setupConnection();
        setupListener();
    }

    private void setupUI() {
        setTitle("Java Chat App");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        inputField = new JTextField();
        inputField.addActionListener(e -> sendMessage());

        add(new JScrollPane(chatArea), BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void setupConnection() {
        try {
            String[] options = {"Login", "Register"};
            int choice = JOptionPane.showOptionDialog(null, "Login or Register",
                    "Authentication", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == -1) System.exit(0);

            username = JOptionPane.showInputDialog("Username:");
            String password = JOptionPane.showInputDialog("Password:");
            if (username == null || password == null || username.isBlank()) System.exit(0);

            String command = (choice == 0 ? "LOGIN" : "REGISTER") + ":" + username + ":" + password;

            socket = new Socket("localhost", 12345);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            if (!"LOGIN_OR_REGISTER".equals(in.readLine())) throw new IOException("Handshake failed.");
            out.println(command);

            String result = in.readLine();
            if ("AUTH_FAILED".equals(result)) {
                JOptionPane.showMessageDialog(this, "Login/Register failed.");
                System.exit(0);
            }
            chatArea.append("Welcome " + username + "!\n");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Connection failed.");
            System.exit(1);
        }
    }

    private void setupListener() {
        new Thread(() -> {
            String msg;
            try {
                while ((msg = in.readLine()) != null) {
                    chatArea.append(msg + "\n");
                }
            } catch (IOException ignored) {}
        }).start();
    }

    private void sendMessage() {
        String text = inputField.getText().trim();
        if (!text.isEmpty()) {
            out.println(text);
            inputField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatGUI::new);
    }
}
