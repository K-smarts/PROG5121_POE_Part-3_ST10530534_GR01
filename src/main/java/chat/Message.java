package chat;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Random;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String text;
    private String messageHash;
    private static int totalMessages = 0;

    // PART 3 - Storage for all messages
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static ArrayList<Message> storedMessages = new ArrayList<>();
    private static ArrayList<String> disregardedMessages = new ArrayList<>();

    public Message(int messageNumber, String recipient, String text) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.text = text;
        this.messageID = createMessageID();
        this.messageHash = createMessageHash();
    }

    public String createMessageID() {
        Random rand = new Random();
        String id = "";
        for (int i = 0; i < 10; i++) {
            id = id + rand.nextInt(10);
        }
        return id;
    }

    public String checkRecipientCell() {
        if (recipient.length() == 12 && recipient.startsWith("+27")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain international code. Please correct the number and try again.";
        }
    }

    public String checkMessageLength() {
        if (text.length() <= 250) {
            return "Message ready to send.";
        } else {
            return "Message exceeds 250 characters by " + (text.length() - 250) + ", please reduce size.";
        }
    }

    public String createMessageHash() {
        String[] words = text.split(" ");
        String first = words[0];
        String last = words[words.length - 1];
        String hash = messageID.substring(0, 2) + ":" + messageNumber + ":" + first.toUpperCase() + last.toUpperCase();
        return hash;
    }

    public String sentMessage() {
        String[] options = {"Send Message", "Disregard Message", "Store Message to send later"};
        int choice = JOptionPane.showOptionDialog(null, "What do you want to do?", "Send Message",
                0, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            totalMessages++;
            sentMessages.add(this);
            return "Message successfully sent.";
        }
        if (choice == 1) {
            disregardedMessages.add(text);
            return "Press 0 to delete the message.";
        }
        if (choice == 2) {
            totalMessages++;
            storedMessages.add(this);
            return "Message successfully stored.";
        }
        return "Message not sent.";
    }

    public String printMessages() {
        return "MessageID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + text;
    }

    // ===== PART 3 METHODS =====

    public static String displaySentMessages() {
        if (sentMessages.isEmpty()) return "No sent messages.";
        String out = "=== Sent Messages ===\n";
        for (Message m : sentMessages) {
            out += m.printMessages() + "\n---\n";
        }
        return out;
    }

    public static String displayLongestMessage() {
        if (sentMessages.isEmpty()) return "No sent messages.";
        Message longest = sentMessages.get(0);
        for (Message m : sentMessages) {
            if (m.text.length() > longest.text.length()) longest = m;
        }
        return "Longest Message:\n" + longest.printMessages();
    }

    public static String searchByMessageID(String id) {
        for (Message m : sentMessages) {
            if (m.messageID.equals(id)) return "Found:\n" + m.printMessages();
        }
        for (Message m : storedMessages) {
            if (m.messageID.equals(id)) return "Found (Stored):\n" + m.printMessages();
        }
        return "Message ID not found.";
    }

    public static String searchByRecipient(String rec) {
        String out = "";
        for (Message m : sentMessages) {
            if (m.recipient.equals(rec)) out += m.printMessages() + "\n---\n";
        }
        if (out.equals("")) return "No messages for " + rec;
        return out;
    }

    public static String deleteByHash(String hash) {
        for (int i = 0; i < sentMessages.size(); i++) {
            if (sentMessages.get(i).messageHash.equals(hash)) {
                sentMessages.remove(i);
                return "Message successfully deleted.";
            }
        }
        return "Message Hash not found.";
    }

    public static String displayReport() {
        if (sentMessages.isEmpty()) return "No sent messages to report.";
        String out = "=== Report ===\n";
        for (Message m : sentMessages) {
            out += "Hash: " + m.messageHash + " | Recipient: " + m.recipient + " | Message: " + m.text + "\n";
        }
        return out;
    }

    public static void clearAllForTesting() {
        sentMessages.clear();
        storedMessages.clear();
        disregardedMessages.clear();
        totalMessages = 0;
    }

    public static ArrayList<Message> getSentMessages() { return sentMessages; }

    public int returnTotalMessages() { return totalMessages; }
    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getText() { return text; }
    public String getMessageHash() { return messageHash; }
}