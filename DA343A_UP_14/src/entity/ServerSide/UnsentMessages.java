package entity.ServerSide;

import entity.Message;
import entity.User;
import java.util.HashMap;
import java.util.LinkedList;

public class UnsentMessages {

    private final HashMap<User, LinkedList<Message>> unsent;
    public UnsentMessages(){
        unsent = new HashMap<>();
    }

    public synchronized void put(User user , Message message) {
        if (unsent.containsKey(user)){
            unsent.get(user).addLast(message);
        }
        else {
            unsent.put(user , new LinkedList<Message>());
            unsent.get(user).addLast(message);
        }
    }

    public synchronized int getNumberOfUnsentMessages(User user){
        if (unsent.containsKey(user)){
            int size = unsent.get(user).size();
            return size;
        }
        else {
            return 0;
        }
    }

    public synchronized Message getNextUnsentMessage(User user){
        return unsent.get(user).removeFirst();
    }
}
