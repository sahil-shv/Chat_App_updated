package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private final String sender;
    private final String text;
    private final String timestamp;

    public Message(String sender, String text) {
        this.sender = sender;
        this.text = text;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String toFileString() {
        return "[" + timestamp + "] " + sender + ": " + text;
    }
}
