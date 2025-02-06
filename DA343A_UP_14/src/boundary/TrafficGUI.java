package boundary;

import control.ChatServer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class TrafficGUI extends JFrame implements PropertyChangeListener {
    private javax.swing.JRadioButton allTrafficRadioButton;
    private javax.swing.JLabel endDateLabel;
    private javax.swing.JTextField endDateTextField;
    private javax.swing.JLabel fromDateLabel;
    private javax.swing.JTextField fromDateTextField;
    private javax.swing.JLabel headlinedLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton onlyTrafficRadioButton;
    private javax.swing.JList<String> trafficList;
    private ChatServer chatServer;


    public TrafficGUI(ChatServer chatServer) {
        this.chatServer = chatServer;
        chatServer.addPropertyChangeListener(this);
        initComponents();
        setVisible(true);
    }



    private void initComponents() {

        allTrafficRadioButton = new javax.swing.JRadioButton();
        onlyTrafficRadioButton = new javax.swing.JRadioButton();
        fromDateLabel = new javax.swing.JLabel();
        fromDateTextField = new javax.swing.JTextField();
        endDateLabel = new javax.swing.JLabel();
        endDateTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        trafficList = new javax.swing.JList<>();
        headlinedLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                exitApplication();
            }
            private void exitApplication() {
                chatServer.disconnect();
                dispose();
            }
        });

        allTrafficRadioButton.setText("All traffic");
        allTrafficRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allTrafficRadioButtonActionPerformed();
            }
        });

        onlyTrafficRadioButton.setText("Only traffic in time window ");
        onlyTrafficRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specificDateActionPerformed();
            }
        });

        ButtonGroup group = new ButtonGroup();
        group.add(allTrafficRadioButton);
        group.add(onlyTrafficRadioButton);

        allTrafficRadioButton.setSelected(true);

        fromDateLabel.setText("From date:");

        fromDateTextField.setText("2023-03-13 00:00:00");
        fromDateTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    specificDateActionPerformed();
                }
            }
        });

        endDateLabel.setText("End date:");

        endDateTextField.setText("2023-03-13 18:00:00");
        endDateTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    specificDateActionPerformed();
                }
            }
        });


        trafficList.setModel(new javax.swing.AbstractListModel<String>() {
            ArrayList<String> strings = chatServer.getAllTraffic();

            public int getSize() {
                return strings.size();
            }

            public String getElementAt(int i) {
                return strings.get(i);
            }
        });
        jScrollPane1.setViewportView(trafficList);
        trafficList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            }
        });
        trafficList.addMouseListener(new java.awt.event.MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if ((e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1)) {
                    showMessage();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        headlinedLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        headlinedLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headlinedLabel.setText("Traffic - server ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(allTrafficRadioButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(onlyTrafficRadioButton)
                                                                .addGap(214, 214, 214)
                                                                .addComponent(fromDateLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(fromDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(54, 54, 54)
                                                                .addComponent(endDateLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(endDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addComponent(headlinedLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addContainerGap())))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(headlinedLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(allTrafficRadioButton)
                                        .addComponent(onlyTrafficRadioButton)
                                        .addComponent(fromDateLabel)
                                        .addComponent(fromDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(endDateLabel)
                                        .addComponent(endDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16))
        );
        setLocationRelativeTo(null);
        pack();
        allTrafficRadioButtonActionPerformed();
    }

    public static void wrongDateFormat() {
        new JOptionPane("Wrong date format! Enter date in format yyyy-MM-dd HH:mm:ss");
    }
    private void allTrafficRadioButtonActionPerformed() {
        trafficList.setModel(new javax.swing.AbstractListModel<String>() {
            ArrayList<String> strings = chatServer.getAllTraffic();

            public int getSize() {
                return strings.size();
            }

            public String getElementAt(int i) {
                return strings.get(i);
            }
        });
    }

    private void specificDateActionPerformed() {

        if(onlyTrafficRadioButton.isSelected()) {
            trafficList.setModel(new javax.swing.AbstractListModel<String>() {
                ArrayList<String> strings = chatServer.getSpecificTrafficStringArray(fromDateTextField.getText(), endDateTextField.getText());


                public int getSize() {
                    return strings.size();
                }

                public String getElementAt(int i) {
                    return strings.get(i);
                }
            });
        }
    }


    private void showMessage(){
        if(allTrafficRadioButton.isSelected()) {
            new MessageGUI(chatServer.getMessageStringArray(chatServer.getMessageFromAllTraffic(trafficList.getSelectedIndex())), chatServer.getMessageFromAllTraffic(trafficList.getSelectedIndex()).getImage());
        }
        if (onlyTrafficRadioButton.isSelected()){
            new MessageGUI(chatServer.getMessageStringArray(chatServer.getMessageFromAllTraffic(trafficList.getSelectedIndex())), chatServer.getMessageFromAllTraffic(trafficList.getSelectedIndex()).getImage());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (onlyTrafficRadioButton.isSelected()){
                    specificDateActionPerformed();
                } else if (allTrafficRadioButton.isSelected()) {
                    allTrafficRadioButtonActionPerformed();
                }
            }
        });
    }


    private class MessageGUI extends JFrame{
        private javax.swing.JList<String> jList1;
        private javax.swing.JScrollPane jScrollPane1;
        private String[] message;
        private ImageIcon image;


        public MessageGUI(String[] message, ImageIcon image) {
            this.message = message;
            this.image = image;
            initComponents();
        }

        private void initComponents() {

            this.setVisible(true);

            jScrollPane1 = new javax.swing.JScrollPane();
            jList1 = new javax.swing.JList<>();

            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            jList1.setModel(new javax.swing.AbstractListModel<String>() {
                String[] strings = message;
                public int getSize() { return strings.length; }
                public String getElementAt(int i) { return strings[i]; }
            });
            jList1.addMouseListener(new java.awt.event.MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
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
            jScrollPane1.setViewportView(jList1);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            );

            pack();
        }
    }
}


