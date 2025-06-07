package server;

import java.io.*;
import java.util.*;

public class UserDAO {
    private static final String USER_FILE = "users.txt";

    public static boolean register(String username, String password) throws IOException {
        Map<String, String> users = loadUsers();
        if (users.containsKey(username)) return false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(username + "," + password);
            writer.newLine();
        }
        return true;
    }

    public static boolean login(String username, String password) throws IOException {
        Map<String, String> users = loadUsers();
        return password.equals(users.get(username));
    }

    private static Map<String, String> loadUsers() throws IOException {
        Map<String, String> users = new HashMap<>();
        File file = new File(USER_FILE);
        if (!file.exists()) return users;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) users.put(parts[0], parts[1]);
            }
        }
        return users;
    }
}
