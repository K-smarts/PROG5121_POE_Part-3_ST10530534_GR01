package login;

import java.util.Scanner;
import javax.swing.JOptionPane;
import chat.Message;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Registration ===");
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter username (must contain '_' and be <= 5 characters): ");
        String username = scanner.nextLine();
        System.out.print("Enter password (8+ chars, capital, number, special char): ");
        String password = scanner.nextLine();
        System.out.print("Enter SA cell phone number (e.g. +27838968976): ");
        String cellPhoneNumber = scanner.nextLine();

        Login login = new Login(firstName, lastName, username, password, cellPhoneNumber);
        System.out.println(login.registerUser());

        System.out.println();
        System.out.println("=== Login ===");
        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();

        boolean loggedIn = login.loginUser(loginUsername, loginPassword);
        System.out.println(login.returnLoginStatus(loggedIn));

        if (!loggedIn) {
            System.out.println("Login failed. Goodbye!");
            return;
        }

        // ===== MENU =====
        boolean running = true;
        int messageCount = 0;

        while (running) {
            String[] menu = {"1) Send Message", "2) Show Sent Messages", "3) Show Longest Message",
                    "4) Search by Message ID", "5) Search by Recipient",
                    "6) Delete by Hash", "7) Display Report", "8) Quit"};

            String choice = (String) JOptionPane.showInputDialog(null, "Welcome to QuickChat.\nChoose an option:",
                    "QuickChat", JOptionPane.QUESTION_MESSAGE, null, menu, menu[0]);

            if (choice == null || choice.startsWith("8)")) {
                JOptionPane.showMessageDialog(null, "Total messages sent: " + messageCount + "\nGoodbye!");
                running = false;
            }
            else if (choice.startsWith("1)")) {
                String recipient = JOptionPane.showInputDialog("Enter recipient (+27...):");
                if (recipient == null) continue;
                String text = JOptionPane.showInputDialog("Enter message (max 250 chars):");
                if (text == null) continue;
                Message m = new Message(messageCount, recipient, text);
                JOptionPane.showMessageDialog(null, m.checkRecipientCell());
                if (m.checkRecipientCell().startsWith("Cell phone number successfully")) {
                    JOptionPane.showMessageDialog(null, m.checkMessageLength());
                    if (m.checkMessageLength().equals("Message ready to send.")) {
                        JOptionPane.showMessageDialog(null, m.sentMessage());
                        JOptionPane.showMessageDialog(null, m.printMessages());
                        messageCount++;
                    }
                }
            }
            else if (choice.startsWith("2)")) {
                JOptionPane.showMessageDialog(null, Message.displaySentMessages());
            }
            else if (choice.startsWith("3)")) {
                JOptionPane.showMessageDialog(null, Message.displayLongestMessage());
            }
            else if (choice.startsWith("4)")) {
                String id = JOptionPane.showInputDialog("Enter Message ID to search:");
                if (id != null) JOptionPane.showMessageDialog(null, Message.searchByMessageID(id));
            }
            else if (choice.startsWith("5)")) {
                String rec = JOptionPane.showInputDialog("Enter recipient (+27...) to search:");
                if (rec != null) JOptionPane.showMessageDialog(null, Message.searchByRecipient(rec));
            }
            else if (choice.startsWith("6)")) {
                String hash = JOptionPane.showInputDialog("Enter Message Hash to delete:");
                if (hash != null) JOptionPane.showMessageDialog(null, Message.deleteByHash(hash));
            }
            else if (choice.startsWith("7)")) {
                JOptionPane.showMessageDialog(null, Message.displayReport());
            }
        }
        scanner.close();
    }
}