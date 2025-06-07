package utils;

public class Validator {
    public static boolean isValidMessage(String msg) {
        return msg != null && !msg.trim().isEmpty() && msg.length() <= 200;
    }
}
