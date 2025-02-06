package entity;

import javax.swing.*;
import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private ImageIcon userImage;

    public User(String username, ImageIcon userImage) {
        this.username = username;
        this.userImage = userImage;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj!=null && obj instanceof User)
            return username.equals(((User)obj).getUsername());
        return false;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ImageIcon getUserImage() {
        return userImage;
    }

    public void setUserImage(ImageIcon userImage) {
        this.userImage = userImage;
    }

    public String toString(){
        return username;
    }
}
