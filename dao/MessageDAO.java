package dao;

import model.Message;

import java.io.*;

public class MessageDAO {
    private static final String FILE = "chatlog.txt";

    public static synchronized void saveMessage(Message msg) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE, true))) {
            writer.write(msg.toFileString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("❌ Could not save message.");
        }
    }
}
