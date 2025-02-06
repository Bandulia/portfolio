package entity.ServerSide;

import entity.Message;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;


public class MessageLogger {
    private ArrayList<Message> messages;
    private final String filename;

    public MessageLogger() {
        this.messages = new ArrayList<>();
        this.filename = "files/MessageLog.dat";;
        loadMessagesFromFile();
    }

    private void loadMessagesFromFile(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            messages = (ArrayList<Message>) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeMessagesToFile() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(messages);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public synchronized void appendMessages(Message message){
        messages.add(message);
    }

    public ArrayList<Message> getSpecificMessages(Date fromTime, Date toTime){
        ArrayList<Message> specificMessages = new ArrayList<>();
        for (Message message : messages){
            if (message.getTimestampAtServer().after(fromTime) && message.getTimestampAtServer().before(toTime)){
                specificMessages.add(message);
            }
        }

        return specificMessages;
    }
}
