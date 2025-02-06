package entity.ClientSide;

import entity.Message;
import entity.User;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatToGUIInfo {

    private ArrayList<User> onlineUsers;
    private final HashMap<User, ArrayList<Message>> messages;
    private User selectedUser;
    private User user;
    private User broadCastUser;

    public ChatToGUIInfo(){
        this.messages = new HashMap<>();
        this.onlineUsers = new ArrayList<>();
    }

    public ArrayList<User> getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(ArrayList<User> onlineUsers){
        this.onlineUsers = onlineUsers;
    }

    public void receiveMessage(Message message){

        User sender = message.getSender();

        if (!messages.containsKey(sender)){
            messages.put(sender, new ArrayList<>());
        }
            messages.get(sender).add(message);
    }

    public ArrayList<Message> getMessages(User user){
        if (!messages.containsKey(user)){
            messages.put(user,new ArrayList<>());
        }
        return this.messages.get(user);
    }

    public void handleMessage(Message message){
        for (User user: message.getRecipients()) {
            if (messages.containsKey(user)) {
                messages.get(user).add(message);
            }
        }
    }

    public User getSelectedUser() {
        return this.selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setBroadcastUser(User broadcastUser) {
        this.broadCastUser = broadcastUser;
    }

    public User getBroadcastUser() {
        return this.broadCastUser;
    }
}
