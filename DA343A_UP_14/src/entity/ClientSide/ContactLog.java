package entity.ClientSide;

import entity.User;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ContactLog implements Serializable {

    private final String filename;
    private HashMap<User, ArrayList<User>> contactLog;

    public ContactLog(){
        this.filename = "files/ContactLog.dat";
        this.contactLog = new HashMap<>();

        try {
            readContactLog();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void readContactLog() throws IOException, ClassNotFoundException {

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        contactLog = (HashMap<User, ArrayList<User>>) in.readObject();
        in.close();
    }

    public synchronized void writeContactLog() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(contactLog);
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void addContactForUser(User currentUser, User newContact){
        if(!contactLog.containsKey(currentUser)){
            contactLog.put(currentUser, new ArrayList<>());
        }
        if (!contactLog.get(currentUser).contains(newContact)) {
            contactLog.get(currentUser).add(newContact);
        }
    }

    public synchronized void removeContact(User currentUser, User contact){
        contactLog.get(currentUser).remove(contact);
    }

    public synchronized ArrayList<User> getContactsBelongingToUser(User user){
        ArrayList<User> loggedContacts = contactLog.get(user);
        if (loggedContacts == null){
            loggedContacts = new ArrayList<>();
        }
        return loggedContacts;
    }
}
