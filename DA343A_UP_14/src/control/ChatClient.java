package control;

import boundary.BroadcastGUI;
import boundary.ChatroomGUI;
import boundary.EntryGUI;
import entity.ClientSide.ChatToGUIInfo;
import entity.ClientSide.ContactLog;
import entity.Message;
import entity.User;
import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ChatClient{

    private final String address;
    private final int port;
    private final ChatToGUIInfo chatToGUIInfo;
    private final ContactLog contactLog;
    private Socket socket;
    private final PropertyChangeSupport propertyChangeSupport;
    private Communicator communicator;
    private ChatroomGUI chatroomGUI;



    public ChatClient(String address, int port) {
        this.address = address;
        this.port = port;
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.chatToGUIInfo = new ChatToGUIInfo();
        this.contactLog = new ContactLog();

        EntryGUI entryGUI = new EntryGUI(this);
        entryGUI.setVisible(true);
    }

    public void setBroadcastUser(User broadcastUser) {
        chatToGUIInfo.setBroadcastUser(broadcastUser);
    }

    public User getBroadcastUser() {
        return chatToGUIInfo.getBroadcastUser();
    }

    public void connectAndSetup(){

        try{

            socket = new Socket(address,port);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.communicator = new Communicator(this);
        communicator.start();

        chatroomGUI = new ChatroomGUI(this);
        chatroomGUI.setVisible(true);

    }

    public User getSelectedUser() {
        return chatToGUIInfo.getSelectedUser();
    }

    public void setSelectedUser(User selectedUser) {
        chatToGUIInfo.setSelectedUser(selectedUser);
    }

    public void sendMessage(ArrayList<User> receivers, String text, ImageIcon image){
        communicator.sendMessageToServer(new Message(chatToGUIInfo.getUser(),receivers,text,image));
    }

    public ArrayList<Message> getMessages(User user){
        return chatToGUIInfo.getMessages(user);
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener){
        propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }

    public void setClientUser(String name, ImageIcon icon){
        chatToGUIInfo.setUser(new User(name,icon));
        chatToGUIInfo.setSelectedUser(chatToGUIInfo.getUser());
    }

    public ChatroomGUI getChatroomGUI(){
        return this.chatroomGUI;
    }

    public ArrayList<User> getContacts(){
        return contactLog.getContactsBelongingToUser(chatToGUIInfo.getUser());
    }

    public ContactLog getContactLog(){
        return this.contactLog;
    }

    public ArrayList<User> getOnlineUsers(){
        return chatToGUIInfo.getOnlineUsers();
    }

    public User getClientUser(){
        return chatToGUIInfo.getUser();
    }

    public void disconnect() {
        contactLog.writeContactLog();
        System.out.println(contactLog.getContactsBelongingToUser(chatToGUIInfo.getUser()));
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }

    public ImageIcon getImageFromMessage(int selectedIndex) {
        return chatToGUIInfo.getMessages(chatToGUIInfo.getSelectedUser()).get(selectedIndex).getImage();
    }

    private class Communicator extends Thread{

        ObjectInputStream in;
        ObjectOutputStream out;
        private boolean init;

        private ChatClient chatClient;

        public Communicator(ChatClient chatClient){
                this.init = true;
                this.chatClient = chatClient;
                initCommunicator();
                System.out.println(getOnlineUsers());
                System.out.println(getContacts());
        }

        private void initCommunicator() {
            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());


                out.writeObject(chatToGUIInfo.getUser());
                //out.writeObject(user);

                out.flush();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void handleReadObject() {

            Object receivedObject;

            try {
                receivedObject = in.readObject();

            } catch (IOException | ClassNotFoundException e) {
                disconnect();
                throw new RuntimeException(e);
            }
            if (receivedObject instanceof Message){

                    chatToGUIInfo.receiveMessage((Message) receivedObject);

            }
            else if (receivedObject instanceof ArrayList<?>) {
                broadCastGUIConfig(receivedObject);
                chatToGUIInfo.setOnlineUsers((ArrayList<User>) receivedObject);
            }
            propertyChangeSupport.firePropertyChange("SomethingsChangedMaybeItsYouMaybeItsMe",null, receivedObject);
            this.init = false;
        }

        private void broadCastGUIConfig(Object receivedObject) {
            if (!init) {
                if (((ArrayList<?>) receivedObject).size() < getOnlineUsers().size()) {
                    for (User user : getOnlineUsers()) {
                        if (!(((ArrayList<?>) receivedObject).contains(user))) {
                            setBroadcastUser(user);
                            new BroadcastGUI(chatClient).UserWentOffline();
                        }
                    }
                }
                if (((ArrayList<?>) receivedObject).size() > getOnlineUsers().size()) {

                    for (User user : (ArrayList<User>) receivedObject) {
                        if (!getOnlineUsers().contains(user)) {
                            if (!(user.getUsername().equals(getClientUser().getUsername()))) {
                                setBroadcastUser(user);
                                new BroadcastGUI(chatClient).UserWentOnline();
                            }
                        }
                    }

                }
            }
        }

        public void sendMessageToServer(Message message){
            try {
                out.writeObject(message);
                out.flush();
                chatToGUIInfo.handleMessage(message);
                propertyChangeSupport.firePropertyChange("SomethingsChangedMaybeItsYouMaybeItsMe",null,message);
            } catch (IOException e) {
                System.out.println("Couldn't send message to server");
            }
        }

        @Override
        public void run() {

            while (!interrupted()) {

                handleReadObject();

            }
        }
    }
}
