package boundary;

import control.ChatClient;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BroadcastGUI extends javax.swing.JFrame {
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private final ChatClient client;

    public BroadcastGUI(ChatClient client) {
        this.client = client;
    }

    public void UserWentOnline() {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                setVisible(true);

                jLabel1 = new javax.swing.JLabel();
                jButton1 = new javax.swing.JButton();

                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                jLabel1.setFont(new java.awt.Font("OCR A Extended", 1, 18)); // NOI18N
                jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel1.setText(client.getBroadcastUser().getUsername() + " is online, say hi!");


                jButton1.setFont(new java.awt.Font("OCR A Extended", 1, 12)); // NOI18N
                jButton1.setText("Enter chat");
                jButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ChatroomGUI chatroomGUI = client.getChatroomGUI();
                        chatroomGUI.setChatUser(client.getBroadcastUser());
                        chatroomGUI.updateTextAreas();
                        chatroomGUI.updateLists();
                        chatroomGUI.updateImages();

                        dispose();
                    }
                });


                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(160, 160, 160)
                                        .addComponent(jButton1)
                                        .addContainerGap(159, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1)
                                        .addContainerGap(23, Short.MAX_VALUE))
                );

                pack();
            }

        });
    }

    public void UserWentOffline() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                setVisible(true);

                JLabel jLabel1 = new javax.swing.JLabel();

                        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                        jLabel1.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
                        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel1.setText(client.getBroadcastUser().getUsername() + " went offline!");

                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                        getContentPane().setLayout(layout);
                        layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                                                .addContainerGap())
                        );
                        layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addComponent(jLabel1)
                                                .addContainerGap(50, Short.MAX_VALUE))
                        );

                        pack();
            }

        });
    }

}
