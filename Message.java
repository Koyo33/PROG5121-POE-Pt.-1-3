/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe_pt.login_and_registration;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author Vusi Mnisi
 */
public class Message {
    private static int messageCount = 0;

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

     public Message(String recipient, String messageText) {
        this.messageID = generateMessageID();
        this.messageNumber = ++messageCount;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    public String generateMessageID() {
        long id = (long) (Math.random() * 1_000_000_0000L);
        return String.valueOf(id);
    }

    public boolean checkMessageLength() {
        return messageText.length() <= 250;
    }

    public String createMessageHash() {

        String first2 = messageID.length() >= 2 ? messageID.substring(0, 2) : "00";
        String[] words = messageText.trim().split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        return (first2 + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    public void storeMessage() {

        try (FileWriter writer = new FileWriter("messages.json", true)) {

            writer.write("{\"id\":\"" + messageID + "\",");
            writer.write("\"recipient\":\"" + recipient + "\",");
            writer.write("\"message\":\"" + messageText + "\"}\n");

        } catch (IOException e) {
            System.out.println("Error storing message.");
        }
    }

    public String printMessage() {
        return "ID: " + messageID +
                "\nHash: " + messageHash +
                "\nRecipient: " + recipient +
                "\nMessage: " + messageText;
    }

    public static int returnTotalMessages() {
        return messageCount;
    }

    public String getMessageHash() {
        return messageHash;
    }  
    //UPDT
    public String getMessageID() {
        return messageID;
    }
    public String getRecipient() {
        return recipient;
    }
    public String getMessageText() {
        return messageText;
    }
}
    

