package boundary;

import control.ChatClient;
import entity.Message;
import entity.User;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;


public class ChatroomGUI extends javax.swing.JFrame implements PropertyChangeListener {
    private JLabel chatterImage;
    private javax.swing.JList<String> onlineList;
    private javax.swing.JList<String> contactList;
    private javax.swing.JList<Message> jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private final ChatClient chatClient;
    private ImageIcon messageImage;
    private JLabel profileImage;
    private JLabel chatroomLabel;
    private ArrayList<User> receivers;


    public ChatroomGUI(ChatClient chatClient) {

        this.chatClient = chatClient;

        this.messageImage = null;

        this.receivers = null;

        chatClient.addPropertyChangeListener(this);

        setUpGUI();
    }

    private void setUpGUI() {
        SwingUtilities.invokeLater(this::initComponents);
    }

    private void initComponents() {

        JScrollPane jScrollPane1 = new JScrollPane();
        onlineList = new javax.swing.JList<>();
        JScrollPane jScrollPane2 = new JScrollPane();
        contactList = new javax.swing.JList<>();
        JScrollPane jScrollPane3 = new JScrollPane();
        jTextArea1 = new JList<>();
        JButton sendButton = new JButton();
        JButton imageButton = new JButton();
        JScrollPane jScrollPane4 = new JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        JLabel onlineLabel = new JLabel();
        JLabel contactsLabel = new JLabel();
        chatroomLabel = new JLabel();
        chatterImage = new JLabel();
        profileImage = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                exitApplication();
            }
            private void exitApplication() {
                chatClient.disconnect();
                System.exit(0);
            }
        });

        onlineList.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        onlineList.setModel(new javax.swing.AbstractListModel<String>() {

            String[] strings = getStringArrayForUserNames(chatClient.getOnlineUsers());
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });

        onlineList.addMouseListener(new java.awt.event.MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if ((e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1)) {
                    if (!(chatClient.getSelectedUser().equals(chatClient.getClientUser()))){
                        chatClient.getContactLog().addContactForUser(chatClient.getClientUser(), chatClient.getSelectedUser());
                    }
                    updateLists();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        jScrollPane1.setViewportView(onlineList);

        onlineList.addListSelectionListener(e -> {
            if (onlineList.getSelectedIndex() != -1){
                chatClient.setSelectedUser(chatClient.getOnlineUsers().get(onlineList.getSelectedIndex()));
                updateTextAreas();
                updateLists();
                chatterImage.setIcon(chatClient.getSelectedUser().getUserImage());
                chatroomLabel.setText(chatClient.getClientUser().getUsername() + "'s Chatroom - receiver: " + chatClient.getSelectedUser());
            }

        });

        contactList.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        contactList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = getStringArrayForUserNames(chatClient.getContacts());
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        contactList.addMouseListener(new java.awt.event.MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    chatClient.getContactLog().removeContact(chatClient.getClientUser(), chatClient.getSelectedUser());
                    updateLists();
                    setChatUser(chatClient.getClientUser());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        jScrollPane2.setViewportView(contactList);

        contactList.addListSelectionListener(e -> {
            if(contactList.getSelectedIndex() != -1){
                setChatUser(chatClient.getContacts().get(contactList.getSelectedIndex()));
                updateTextAreas();
                updateLists();
                chatterImage.setIcon(chatClient.getSelectedUser().getUserImage());
                chatroomLabel.setText(chatClient.getClientUser().getUsername() + "'s Chatroom - receiver: " + chatClient.getSelectedUser());
            }
        });

        jTextArea1.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jTextArea1.addMouseListener(new java.awt.event.MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    ImageIcon image = chatClient.getImageFromMessage(jTextArea1.getSelectedIndex());
                    JLabel label = new JLabel(image);
                    JOptionPane.showMessageDialog(null, label, "The message image", JOptionPane.PLAIN_MESSAGE);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        jScrollPane3.setViewportView(jTextArea1);

        sendButton.setFont(new java.awt.Font("OCR A Extended", 1, 18)); // NOI18N
        sendButton.setText("Send");
        sendButton.addActionListener(e -> {
            if (receivers == null) {
                ArrayList<User> receiver = new ArrayList<>();
                receiver.add(chatClient.getSelectedUser());
                chatClient.sendMessage(receiver, jTextArea2.getText(), getMessageImage());
            } else{
                chatClient.sendMessage(receivers, jTextArea2.getText(), getMessageImage());
            }
            receivers = null;
            updateTextAreas();
            setMessageImage(null);
        });
        chatterImage.addMouseListener(new java.awt.event.MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                new Receivers();
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        imageButton.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        imageButton.setText("Add image");
        imageButton.addActionListener(e -> addImageActionPerformed());


        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jTextArea2.setRows(5);
        jScrollPane4.setViewportView(jTextArea2);

        onlineLabel.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        onlineLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        onlineLabel.setText("Online users");

        contactsLabel.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        contactsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contactsLabel.setText("Contacts");

        chatroomLabel.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        chatroomLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chatroomLabel.setText(chatClient.getClientUser().getUsername() + "'s Chatroom!");

        chatterImage.setIcon(chatClient.getSelectedUser().getUserImage());

        profileImage.setIcon(chatClient.getSelectedUser().getUserImage());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1)
                                        .addComponent(onlineLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2)
                                        .addComponent(contactsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                        .addComponent(chatterImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(chatroomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(175, 175, 175)
                                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(imageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(18, 18, 18))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(profileImage, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(chatroomLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                                                .addComponent(imageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jScrollPane4)
                                                        .addComponent(profileImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(chatterImage, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(onlineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(contactsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(8, 8, 8)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(14, 14, 14))
        );

        pack();
    }

    private String[] getStringArrayForUserNames(ArrayList<User> list) {
        String[] stringArray = new String[list.size()];
        for (int i = 0; i < list.size(); i++){
            stringArray[i] = list.get(i).getUsername();
            if (stringArray[i].equals(chatClient.getClientUser().getUsername())){
                stringArray[i] += (" (me)");
            }
        }
        return stringArray;
    }

    public void updateGUI(){
        SwingUtilities.invokeLater(() -> {
            updateLists();
            updateTextAreas();
            updateImages();
        });
    }

    public void updateLists(){
        onlineList.setModel(new javax.swing.AbstractListModel<String>() {
            ArrayList<User> onlineUsers = chatClient.getOnlineUsers();
            String[] strings = getStringArrayForUserNames(chatClient.getOnlineUsers());
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        contactList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = getStringArrayForUserNames(chatClient.getContacts());
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });

        jTextArea1.setModel(new AbstractListModel<Message>() {
            ArrayList<Message> strings = chatClient.getMessages(chatClient.getSelectedUser());
            public int getSize() { return strings.size(); }
            public Message getElementAt(int i) { return strings.get(i); }
        });
    }

    public void updateTextAreas(){

        jTextArea1.setModel(new AbstractListModel<Message>() {
            ArrayList<Message> strings = chatClient.getMessages(chatClient.getSelectedUser());
            public int getSize() { return strings.size(); }
            public Message getElementAt(int i) { return strings.get(i); }
        });
        jTextArea2.setText("");
    }

    public void addImageActionPerformed(){
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("files"));
        int result = jFileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String imageAddress = jFileChooser.getSelectedFile().getAbsolutePath();
            if (!(imageAddress.endsWith(".png") || imageAddress.endsWith(".jpeg") || imageAddress.endsWith(".jpg"))) {
                JOptionPane.showMessageDialog(this, "Please enter a suitable image address");
            } else {
                messageImage = new ImageIcon(imageAddress);
                setMessageImage(messageImage);
                jTextArea2.setText(jTextArea2.getText() + " + inserted image: " + imageAddress);
            }
        }
    }

    public void updateImages(){
        chatterImage.setIcon(chatClient.getSelectedUser().getUserImage());
    }

    public void setChatUser(User user) {
            chatClient.setSelectedUser(user);
    }

    public ImageIcon getMessageImage() {
        return messageImage;
    }

    public void setMessageImage(ImageIcon messageImage) {
        this.messageImage = messageImage;
        if (messageImage == null){
            profileImage.setIcon(chatClient.getClientUser().getUserImage());
        }else {
            profileImage.setIcon(messageImage);
        }
    }

    public void setReceivers(ArrayList<User> list){
        this.receivers = list;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("SomethingsChangedMaybeItsYouMaybeItsMe".equals(evt.getPropertyName())){
            updateGUI();
        }
    }



    public class Receivers extends javax.swing.JFrame {
        private javax.swing.JList<String> contactsList;
        private javax.swing.JLabel instructionsLabel;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JScrollPane jScrollPane3;
        private javax.swing.JList<String> onlineUserList;
        private javax.swing.JList<String> receiverList;
        private javax.swing.JLabel receiversLabel;
        private javax.swing.JButton submitButton;
        private javax.swing.JLabel usersLabel;
        private ArrayList<User> receivers;


        public Receivers() {
            initComponents();
            setVisible(true);
        }

        private void initComponents() {

            jScrollPane1 = new javax.swing.JScrollPane();
            onlineUserList = new javax.swing.JList<>();
            jScrollPane2 = new javax.swing.JScrollPane();
            contactsList = new javax.swing.JList<>();
            jScrollPane3 = new javax.swing.JScrollPane();
            receiverList = new javax.swing.JList<>();
            submitButton = new javax.swing.JButton();
            instructionsLabel = new javax.swing.JLabel();
            usersLabel = new javax.swing.JLabel();
            receiversLabel = new javax.swing.JLabel();
            receivers = new ArrayList<>();

            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            onlineUserList.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
            jScrollPane1.setViewportView(onlineUserList);

            contactsList.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
            jScrollPane2.setViewportView(contactsList);

            receiverList.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
            jScrollPane3.setViewportView(receiverList);

            submitButton.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
            submitButton.setText("Submit receivers");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setReceiverLabel();
                    setReceivers(receivers);
                    dispose();
                }
            });

            instructionsLabel.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
            instructionsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            instructionsLabel.setText("Dubbelclick to add/remov from list");

            usersLabel.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
            usersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            usersLabel.setText("Users");

            receiversLabel.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
            receiversLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            receiversLabel.setText("Receivers");

            onlineUserList.addMouseListener(new java.awt.event.MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                        receivers.add(chatClient.getOnlineUsers().get(onlineUserList.getSelectedIndex()));
                        updateAllLists();
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
            });

            contactsList.addMouseListener(new java.awt.event.MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                        receivers.add(chatClient.getContacts().get(onlineUserList.getSelectedIndex()));
                        updateAllLists();
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
            });

            receiverList.addMouseListener(new java.awt.event.MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                        receivers.remove(receiverList.getSelectedIndex());
                        updateAllLists();
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
            });

            updateAllLists();

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                    .addGap(6, 6, 6)
                                                                    .addComponent(usersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                            .addGroup(layout.createSequentialGroup()
                                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addGap(0, 0, Short.MAX_VALUE)))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(jScrollPane3)
                                                            .addComponent(receiversLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)))
                                            .addComponent(instructionsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap(89, Short.MAX_VALUE)
                                    .addComponent(submitButton)
                                    .addGap(79, 79, 79))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(instructionsLabel)
                                    .addGap(5, 5, 5)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(usersLabel)
                                            .addComponent(receiversLabel))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane3)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(submitButton)
                                    .addContainerGap(19, Short.MAX_VALUE))
            );

            pack();
        }

        private void setReceiverLabel() {
            if (receivers.size() == 1){
                chatroomLabel.setText(chatClient.getClientUser().getUsername() + "'s Chatroom - receiver: " + receivers.get(0).getUsername());
            }
            if (receivers.size() == 2){
                chatroomLabel.setText(chatClient.getClientUser().getUsername() + "'s Chatroom - receivers: " + receivers.get(0).getUsername() + " " + receivers.get(1).getUsername());
            }
            if (receivers.size() == 3){
                chatroomLabel.setText(chatClient.getClientUser().getUsername() + "'s Chatroom - receivers: " + receivers.get(0).getUsername() + " " + receivers.get(1).getUsername() + " " + receivers.get(2).getUsername());
            }
            if (receivers.size()>= 4){
                chatroomLabel.setText(chatClient.getClientUser().getUsername() + "'s Chatroom - receivers: " + receivers.get(0).getUsername() + " " + receivers.get(1).getUsername() + " " + receivers.get(2).getUsername() + "...");
            }
        }


        private void updateAllLists(){
            onlineUserList.setModel(new javax.swing.AbstractListModel<String>() {
                ArrayList<User> onlineUsers = chatClient.getOnlineUsers();
                String[] strings = getStringArrayForUserNames(chatClient.getOnlineUsers());
                public int getSize() { return strings.length; }
                public String getElementAt(int i) { return strings[i]; }
            });
            contactsList.setModel(new javax.swing.AbstractListModel<String>() {
                String[] strings = getStringArrayForUserNames(chatClient.getContacts());
                public int getSize() { return strings.length; }
                public String getElementAt(int i) { return strings[i]; }
            });
            receiverList.setModel(new javax.swing.AbstractListModel<String>() {
                String[] strings = getReceiversStringArray();
                public int getSize() { return strings.length; }
                public String getElementAt(int i) { return strings[i]; }
            });
        }

        private String[] getReceiversStringArray(){
            String[] list = new String[receivers.size()];
            for (int i = 0; i < list.length; i++){
                list[i] = receivers.get(i).getUsername();
            }
            return list;
        }
    }
}
