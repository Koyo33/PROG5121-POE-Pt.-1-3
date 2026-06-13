/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poe_pt.login_and_registration;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;




/**
 *
 * @author Vusi Mnisi
 */
public class POE_pt1Login_and_Registration {
    public static void main(String[] args) {

        System.out.println("Hello!");

        registerUser();

        if (loginUser()) {
            messageMenu();
        } else {
            System.out.println("Login failed. Please try again");
        }
        
    }

    //Main menu
    private static void messageMenu() {

        int choice;

        System.out.print("How many messages do you want to send?");
        int maxMessages = Integer.parseInt(userInput.nextLine());

        int sent = 0;

        while (true) {

            System.out.println("--- MENU ---");
            System.out.println("1) Send Message");
            System.out.println("2) Show Recently Sent Messages");
            System.out.println("3) Quit");
            //UPDT
            System.out.println("4) Stored Messages");
            System.out.print("Choose: ");

            choice = Integer.parseInt(userInput.nextLine());

            switch (choice) {

                case 1:

                    if (sent >= maxMessages) {
                        System.out.println("Message limit reached.");
                        break;
                    }

                    System.out.print("Recipient (+code): ");
                    String recipient = userInput.nextLine();

                    System.out.print("Message (max 250 chars): ");
                    String text = userInput.nextLine();

                    Message msg = new Message(recipient, text);

                    if (!msg.checkMessageLength()) {
                        System.out.println("Please enter a message of less than 250 characters");
                        break;
                    }

                    System.out.println("Message sent");
                    System.out.println(msg.printMessage());
                    msg.storeMessage();
                    sent++;
                    //part 3 update (UPDT)
                    sentMessages.add(msg);
                    messageHashes.add(msg.getMessageHash());
                    messageIDs.add(msg.getMessageID());

                    System.out.println("Total messages sent: " + Message.returnTotalMessages());

                    break;

                case 2:
                    System.out.println("Coming Soon.");
                    break;

                case 3:
                    System.out.println("Exiting...");
                    return;
                
                case 4:
                    Menu menu = new Menu(sentMessages, userInput);
                    menu.storedMessagesMenu();
                    break;    

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    //Decleration
    static class User {

        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String phoneNumber;

        public User(String firstName, String lastName,
                    String username, String password,
                    String phoneNumber) {

            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.password = password;
            this.phoneNumber = phoneNumber;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
    
    static ArrayList<User> savedUsers = new ArrayList<>();
    static Scanner userInput = new Scanner(System.in);
    //UPDT
        static ArrayList<Message> sentMessages = new ArrayList<>();
        static ArrayList<Message> disregardedMessages = new ArrayList<>();
        static ArrayList<Message> storedMessages = new ArrayList<>();

        static ArrayList<String> messageHashes = new ArrayList<>();
        static ArrayList<String> messageIDs = new ArrayList<>();

    //Data validation logic
    public static boolean checkPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\+[0-9]{6,15}$");
    }

    public static boolean checkUsername(String username) {
        return username.length() == 5 && username.contains("_");
    }

    public static boolean checkPasswordComplexity(String password) {
        return password.length() >= 8
                && password.matches(".*[A-Z].*")
                && password.matches(".*[0-9].*")
                && password.matches(".*[^a-zA-Z0-9].*");
    }

    //Registering user
    private static void registerUser() {

        System.out.println("--- Registration ---");

        System.out.print("Name: ");
        String firstName = userInput.nextLine();

        System.out.print("Surname: ");
        String lastName = userInput.nextLine();

        String phoneNumber;
        while (true) {
            System.out.print("Enter cellphone number (+Area code): ");
            phoneNumber = userInput.nextLine();

            if (checkPhoneNumber(phoneNumber)) break;
            else System.out.println("Invalid phone number format.");
        }

        String username;
        while (true) {
            System.out.print("Username: ");
            username = userInput.nextLine();

            if (checkUsername(username)) break;
            else System.out.println("Invalid username.");
        }

        String password;
        while (true) {
            System.out.print("Password: ");
            password = userInput.nextLine();

            if (checkPasswordComplexity(password)) break;
            else System.out.println("Invalid password.");
        }

        User user = new User(firstName, lastName, username, password, phoneNumber);
        savedUsers.add(user);

        System.out.println("User registered successfully!");
    }

    //User login
    private static boolean loginUser() {

        System.out.println("--- Login ---");

        System.out.print("Username: ");
        String username = userInput.nextLine();

        System.out.print("Password: ");
        String password = userInput.nextLine();

        for (User u : savedUsers) {

            if (u.getUsername().equals(username)
                    && u.getPassword().equals(password)) {

                System.out.println("Login successful!");
                System.out.println("Welcome to QuickChat: " + username);
                return true;
            }
        }

        System.out.println("Login failed: incorrect username or password.");
        return false;
    }
}