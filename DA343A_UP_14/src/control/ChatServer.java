package control;

import boundary.TrafficGUI;
import entity.Message;
import entity.ServerSide.Clients;
import entity.ServerSide.MessageLogger;
import entity.ServerSide.UnsentMessages;
import entity.User;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatServer{

    private final ServerSocket serverSocket;
    private final Clients clients;
    private MessageLogger messageLogger;
    private final UnsentMessages unsentMessages;
    private PropertyChangeSupport propertyChangeSupport;


    public ChatServer(int port){
        clients = new Clients();
        messageLogger = new MessageLogger();
        unsentMessages = new UnsentMessages();
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        new TrafficGUI(this);

        try {

            this.serverSocket = new ServerSocket(port);
            new ClientListener().start();

        } catch (IOException e) {
            messageLogger.writeMessagesToFile();
            throw new RuntimeException(e);

        }
    }

    private void handleMessage(Message message){

        message.setTimestampAtServer(new Date());

        for (User user: message.getRecipients()) {
            if (clients.getActiveUserList().contains(user)){
                clients.get(user).send(message);
                message.setTimestampAtReceiver(new Date());
                messageLogger.appendMessages(message);
            } else {
                unsentMessages.put(user, message);
            }
        }
    }

    private void broadCastOnlineUsers(ArrayList<User> onlineUsers) {
        for (User user:clients.getActiveUserList()) {
            clients.getClients().get(user).send(onlineUsers);
        }
    }

    public ArrayList<String> getAllTraffic(){
        ArrayList<String> messages = new ArrayList<>();
        for (Message message: messageLogger.getMessages()){
            String text = String.format("%-40s %-40s", message.getSender().getUsername(), message.getTimestampAtServer());
            String text2 = String.format("%-40s %-40s", message.getRecipients().get(0).getUsername(), message.getTimestampAtReceiver());
            String text3 = String.format("%-40s %-40s", text, text2);
            messages.add(text3);
        }
        return messages;
    }

    public ArrayList<String> getSpecificTrafficStringArray(String from, String to){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ArrayList<Message> specificMessages = null;
        try {
            specificMessages = messageLogger.getSpecificMessages(dateFormat.parse(from),dateFormat.parse(to));
        } catch (ParseException e) {
            TrafficGUI.wrongDateFormat();
        }
        if (specificMessages == null){
            ArrayList<String> noMessages = new ArrayList<>();
            noMessages.add("No messages has been received in the given time frame");
            System.out.println(noMessages.get(0));
            return noMessages;
        }
        else {
            ArrayList<String> messages = new ArrayList<>();
            for (Message message: specificMessages){
                String text = String.format("%-40s %-40s", message.getSender().getUsername(), message.getTimestampAtServer());
                String text2 = String.format("%-40s %-40s", message.getRecipients().get(0).getUsername(), message.getTimestampAtReceiver());
                String text3 = String.format("%-40s %-40s", text, text2);
                messages.add(text3);
            }
            return messages;
        }
    }

    public String[] getMessageStringArray(Message message){
        String[] messageString = new String[6];
        messageString[0] = "Sender: " + message.getSender().getUsername();
        messageString[1] = "Receiver/recipients: " + message.getRecipients().toString();
        messageString[2] = "Timestamp at server: " + message.getTimestampAtServer().toString();
        messageString[3] = "Timestamp at receiver: " + message.getTimestampAtReceiver().toString();
        messageString[4] = "Text message: " + message.getText();
        if (message.getImage() == null) {
            messageString[5] = "No inserted image";
        }
        else {
            messageString[5] = "Inserted image: " + message.getImage().toString();
        }

        return messageString;
    }

    public Message getMessageFromAllTraffic(int index){
        return messageLogger.getMessages().get(index);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl){
        propertyChangeSupport.addPropertyChangeListener(pcl);
    }

    public void disconnect() {
        messageLogger.writeMessagesToFile();
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }

    private class ClientListener extends Thread{

        @Override
        public void run() {
            Socket socket = null;
            try {
            while(!Thread.interrupted()){

                    socket = serverSocket.accept();

                    Client client = new Client(socket);
                    client.start();

                }
            } catch (IOException e) {
                System.out.println("Client disconnected: " + e.getMessage());
                try {
                    if (socket != null){
                        socket.close();
                    }
                    disconnect();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
    }

    public class Client extends Thread{

        private User user;
        private ObjectInputStream in;
        private ObjectOutputStream out;
        public Client(Socket clientSocket) {

                initStreams(clientSocket);

                receiveUser();

                clients.put(user,this);

                broadCastOnlineUsers(clients.getActiveUserList());

                sendUnsentMessages();

                System.out.println("new client connected with user: " + user.getUsername());
        }

        private void receiveUser() {
            try {
                this.user = (User) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        private void initStreams(Socket clientSocket) {
            try {
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void sendUnsentMessages() {
            int size = unsentMessages.getNumberOfUnsentMessages(user);
            for (int i = 0; i <size ; i++){
                Message unsentMessage = unsentMessages.getNextUnsentMessage(user);
                send(unsentMessage);
                unsentMessage.setTimestampAtReceiver(new Date());
                messageLogger.appendMessages(unsentMessage);
            }
        }

        public void send(Object obj){
            try {
                out.writeObject(obj);
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        @Override
        public void run() {
            Message incomeMessage;

            while(!interrupted()){
                try {
                    incomeMessage = (Message) in.readObject();

                    handleMessage(incomeMessage);

                    propertyChangeSupport.firePropertyChange("new message", false, incomeMessage);

                } catch (IOException e) {

                    interrupt();

                    clients.remove(user);

                    broadCastOnlineUsers(clients.getActiveUserList());

                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
