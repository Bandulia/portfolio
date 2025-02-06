package boundary;

import control.ChatClient;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.File;

public class EntryGUI extends javax.swing.JFrame {
    private javax.swing.JTextField imageField;
    private javax.swing.JTextField usernameField;
    private final ChatClient chatClient;

    public EntryGUI(ChatClient chatClient) {
        this.chatClient = chatClient;
        initComponents();
    }

    private void initComponents() {

        JLabel welcomeLabel = new JLabel();
        JLabel usernameLabel = new JLabel();
        usernameField = new javax.swing.JTextField();
        JLabel imageLabel = new JLabel();
        imageField = new javax.swing.JTextField();
        JLabel submitLabel = new JLabel();
        JButton submitButton = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        welcomeLabel.setFont(new java.awt.Font("OCR A Extended", 1, 36)); // NOI18N
        welcomeLabel.setText("Welcome to the OJ chatroom!");

        usernameLabel.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        usernameLabel.setText("Enter username:");

        usernameField.setFont(new java.awt.Font("OCR A Extended", 0, 24)); // NOI18N
        usernameField.setText("mauStudent123");

        imageLabel.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        imageLabel.setText("Choose user avatar:");

        imageField.setFont(new java.awt.Font("OCR A Extended", 0, 24)); // NOI18N
        imageField.setText("Browse for image");
        imageField.addMouseListener(new java.awt.event.MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                imageFieldActionPerformed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        submitLabel.setFont(new java.awt.Font("OCR A Extended", 1, 18)); // NOI18N
        submitLabel.setText("When content with username and avatar, press submit. ");

        submitButton.setFont(new java.awt.Font("OCR A Extended", 1, 18)); // NOI18N
        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(57, 57, 57)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(usernameLabel)
                                                        .addComponent(imageLabel))
                                                .addGap(30, 30, 30)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(usernameField)
                                                        .addComponent(imageField, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(105, 105, 105)
                                                .addComponent(welcomeLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(140, 140, 140)
                                                .addComponent(submitLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(383, 383, 383)
                                                .addComponent(submitButton)))
                                .addContainerGap(124, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(welcomeLabel)
                                .addGap(72, 72, 72)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(usernameLabel))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(imageLabel)
                                        .addComponent(imageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(73, 73, 73)
                                .addComponent(submitLabel)
                                .addGap(35, 35, 35)
                                .addComponent(submitButton)
                                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }

    private void imageFieldActionPerformed(MouseEvent evt) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("files"));
        int result = jFileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            imageField.setText(jFileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {

        String username = usernameField.getText();
        String imageAddress = imageField.getText();

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a username");
            return;
        }
        if (!(imageAddress.endsWith(".png") || imageAddress.endsWith(".jpeg") || imageAddress.endsWith(".jpg"))) {
            JOptionPane.showMessageDialog(this, "Please enter a suitable image address");
            return;
        }

        ImageIcon avatar = new ImageIcon(imageField.getText());
        chatClient.setClientUser(username, avatar);
        chatClient.connectAndSetup();
        this.dispose();
    }
}
