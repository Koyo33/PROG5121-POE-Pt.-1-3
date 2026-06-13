/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe_pt.login_and_registration;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Vusi Mnisi
 */
public class Menu {

    private ArrayList<Message> sentMessages;
    private Scanner userInput;
    //List passing
    public Menu(ArrayList<Message> sentMessages, Scanner userInput) {
        this.sentMessages = sentMessages;
        this.userInput = userInput;
    }

    public void storedMessagesMenu() {

        while (true) {

            System.out.println("--- STORED MESSAGES MENU ---");
            System.out.println("a) Display sender & recipient");
            System.out.println("b) Show longest message");
            System.out.println("c) Search by Message ID");
            System.out.println("d) Search by recipient");
            System.out.println("e) Delete by message hash");
            System.out.println("f) Full report");
            System.out.println("g) Back");

            String choice = userInput.nextLine();

            switch (choice.toLowerCase()) {

                case "a":
                    for (Message m : sentMessages) {
                        System.out.println("Recipient: " + m.getRecipient());
                    }
                    break;

                case "b":
                    Message longest = null;

                    for (Message m : sentMessages) {
                        if (longest == null ||
                                m.getMessageText().length() > longest.getMessageText().length()) {
                            longest = m;
                        }
                    }

                    if (longest != null) {
                        System.out.println("Longest Message: " + longest.printMessage());
                    }
                    break;

                case "c":
                    System.out.print("Enter Message ID: ");
                    String id = userInput.nextLine();

                    for (Message m : sentMessages) {
                        if (m.getMessageID().equals(id)) {
                            System.out.println(m.printMessage());
                        }
                    }
                    break;

                case "d":
                    System.out.print("Enter recipient: ");
                    String rec = userInput.nextLine();

                    for (Message m : sentMessages) {
                        if (m.getRecipient().equals(rec)) {
                            System.out.println(m.printMessage());
                        }
                    }
                    break;

                case "e":
                    System.out.print("Enter message hash: ");
                    String hash = userInput.nextLine();

                    sentMessages.removeIf(m -> m.getMessageHash().equals(hash));
                    System.out.println("Message deleted (if found).");
                    break;

                case "f":
                    System.out.println("FULL MESSAGE REPORT:");
                    for (Message m : sentMessages) {
                        System.out.println(m.printMessage());
                        System.out.println("-------------------");
                    }
                    break;

                case "g":
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
    

