package chat;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ChatMain {

    // lists to keep messages - part 3 needs this
    static ArrayList<Message> sent = new ArrayList<>();
    static ArrayList<Message> stored = new ArrayList<>();
    static ArrayList<Message> disregarded = new ArrayList<>();

    public static void startChat() {
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        // how many messages?
        String input = JOptionPane.showInputDialog("How many messages do you want to send?");
        int howMany = Integer.parseInt(input);

        int count = 0;
        boolean quit = false;

        while (quit == false) {
            String menu = "Choose an option:\n"
                    + "1) Send Messages\n"
                    + "2) Show Recently Sent Messages\n"
                    + "3) Show Longest Message\n"
                    + "4) Search by Message ID\n"
                    + "5) Search by Recipient\n"
                    + "6) Delete by Hash\n"
                    + "7) Show Report\n"
                    + "8) Quit";
            String pick = JOptionPane.showInputDialog(menu);
            int option = Integer.parseInt(pick);

            if (option == 1) {
                // check if we already sent enough
                if (count >= howMany) {
                    JOptionPane.showMessageDialog(null, "You have sent all your messages.");
                } else {
                    // get recipient
                    String cell = JOptionPane.showInputDialog("Enter recipient number (with +27):");
                    Message temp = new Message(0, cell, "test");
                    String cellCheck = temp.checkRecipientCell();
                    if (cellCheck.startsWith("Cell phone number is incorrectly")) {
                        JOptionPane.showMessageDialog(null, cellCheck);
                    } else {
                        // get message text
                        String text = JOptionPane.showInputDialog("Enter your message:");
                        Message m = new Message(count, cell, text);

                        // check length
                        String lenCheck = m.checkMessageLength();
                        if (lenCheck.startsWith("Message exceeds")) {
                            JOptionPane.showMessageDialog(null, lenCheck);
                        } else {
                            JOptionPane.showMessageDialog(null, m.printMessages());
                            String result = m.sentMessage();
                            JOptionPane.showMessageDialog(null, result);

                            if (result.equals("Message successfully sent.")) {
                                sent.add(m);
                                count++;
                            }
                            if (result.equals("Message successfully stored.")) {
                                stored.add(m);
                                count++;
                            }
                            if (result.equals("Press 0 to delete the message.")) {
                                disregarded.add(m);
                                count++;
                            }
                            JOptionPane.showMessageDialog(null, "Total messages sent: " + m.returnTotalMessages());
                        }
                    }
                }
            }

            if (option == 2) {
                if (sent.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Coming Soon.");
                } else {
                    String out = "Sent Messages:\n";
                    for (int i = 0; i < sent.size(); i++) {
                        out = out + sent.get(i).printMessages() + "\n---\n";
                    }
                    JOptionPane.showMessageDialog(null, out);
                }
            }

            if (option == 3) {
                if (sent.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No messages yet.");
                } else {
                    Message longest = sent.get(0);
                    for (int i = 0; i < sent.size(); i++) {
                        if (sent.get(i).getText().length() > longest.getText().length()) {
                            longest = sent.get(i);
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Longest Message:\n" + longest.printMessages());
                }
            }

            if (option == 4) {
                String searchID = JOptionPane.showInputDialog("Enter Message ID to search:");
                boolean found = false;
                for (int i = 0; i < sent.size(); i++) {
                    if (sent.get(i).getMessageID().equals(searchID)) {
                        JOptionPane.showMessageDialog(null, "Found:\nRecipient: " + sent.get(i).getRecipient()
                                + "\nMessage: " + sent.get(i).getText());
                        found = true;
                    }
                }
                if (found == false) {
                    JOptionPane.showMessageDialog(null, "Message ID not found.");
                }
            }

            if (option == 5) {
                String searchCell = JOptionPane.showInputDialog("Enter recipient to search:");
                String out = "Messages to " + searchCell + ":\n";
                boolean found = false;
                for (int i = 0; i < sent.size(); i++) {
                    if (sent.get(i).getRecipient().equals(searchCell)) {
                        out = out + sent.get(i).getText() + "\n---\n";
                        found = true;
                    }
                }
                if (found == false) {
                    out = "No messages to that person.";
                }
                JOptionPane.showMessageDialog(null, out);
            }

            if (option == 6) {
                String searchHash = JOptionPane.showInputDialog("Enter Message Hash to delete:");
                boolean deleted = false;
                for (int i = 0; i < sent.size(); i++) {
                    if (sent.get(i).getMessageHash().equals(searchHash)) {
                        sent.remove(i);
                        JOptionPane.showMessageDialog(null, "Message successfully deleted.");
                        deleted = true;
                        break;
                    }
                }
                if (deleted == false) {
                    JOptionPane.showMessageDialog(null, "Hash not found.");
                }
            }

            if (option == 7) {
                if (sent.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No messages to show.");
                } else {
                    String out = "REPORT:\n";
                    for (int i = 0; i < sent.size(); i++) {
                        out = out + "Hash: " + sent.get(i).getMessageHash()
                                + "\nRecipient: " + sent.get(i).getRecipient()
                                + "\nMessage: " + sent.get(i).getText() + "\n---\n";
                    }
                    JOptionPane.showMessageDialog(null, out);
                }
            }

            if (option == 8) {
                quit = true;
                JOptionPane.showMessageDialog(null, "Total messages sent: " + sent.size());
            }
        }
    }
}