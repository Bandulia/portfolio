package entity;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Message implements Serializable {
    private User sender;
    private ArrayList<User> recipients;
    private String text;
    private ImageIcon image;
    private Date timestampAtServer;
    private Date timestampAtReceiver;


    public Message(User sender, ArrayList<User> recipients, String text, ImageIcon image) {
        this.sender = sender;
        this.recipients = recipients;
        this.text = text;
        this.image = image;
    }

    public Message(User sender, ArrayList<User> recipients, ImageIcon image) {
        this(sender, recipients,null, image);
    }

    public Message(User sender, ArrayList<User> recipients, String text) {
        this(sender, recipients,text, null);
    }

    public User getSender() {
        return sender;
    }

    public ArrayList<User> getRecipients() {
        return recipients;
    }

    public void setRecipients(ArrayList<User> recipients) {
        this.recipients = recipients;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public Date getTimestampAtServer() {
        return timestampAtServer;
    }

    public void setTimestampAtServer(Date timestamp) {
        this.timestampAtServer = timestamp;
    }

    public Date getTimestampAtReceiver() {
        return timestampAtReceiver;
    }

    public void setTimestampAtReceiver(Date timestamp) {
        this.timestampAtReceiver = timestamp;
    }

    public boolean addRecipient(User user) {
        return recipients.add(user);
    }

    public boolean removeRecipient(User user) {
        return recipients.remove(user);
    }

    public boolean hasImage() {
        return image != null;
    }

    public String toString(){
        String str = String.format("%-20s %s", sender.getUsername(), getText());

        return str;
    }
}

