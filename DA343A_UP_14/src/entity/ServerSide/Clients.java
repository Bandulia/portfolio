package entity.ServerSide;

import control.ChatServer;
import entity.User;
import java.util.ArrayList;
import java.util.HashMap;

public class Clients {
    private final HashMap<User, ChatServer.Client> clients;

    public Clients() {
        clients = new HashMap<>();
    }

    public synchronized HashMap<User,ChatServer.Client> getClients(){
        return this.clients;
    }

    public synchronized void put(User user, ChatServer.Client client) {
        clients.put(user,client);
    }

    public synchronized ChatServer.Client get(User user) {
        return clients.get(user);
    }

    public synchronized void remove(User user){
        clients.remove(user);
    }

    public synchronized ArrayList<User> getActiveUserList() {
        return new ArrayList<>(clients.keySet());
    }
}