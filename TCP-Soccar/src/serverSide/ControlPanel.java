package serverSide;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author julia
 */
public class ControlPanel extends javax.swing.JFrame {


    /**
     * Creates new form Controllpanel
     */
    public ControlPanel(Server server) {

        this.server = server;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initComponents();
            }
        });
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        leftButton1 = new javax.swing.JButton();
        backwardButton1 = new javax.swing.JButton();
        rightButton1 = new javax.swing.JButton();
        forwardButton1 = new javax.swing.JButton();
        spaceR1 = new javax.swing.JLabel();
        spaceL1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        leftButton2 = new javax.swing.JButton();
        backwardButton2 = new javax.swing.JButton();
        forwardButton2 = new javax.swing.JButton();
        rightButton2 = new javax.swing.JButton();
        spaceR2 = new javax.swing.JLabel();
        spaceL2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        //jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 153));

        jPanel1.setBackground(new java.awt.Color(0, 51, 153));

        leftButton1.setFont(new java.awt.Font("OCR A Extended", 0, 36)); // NOI18N
        leftButton1.setText("<");
        leftButton1.setBackground(new Color(0,0,0,0));

        backwardButton1.setFont(new java.awt.Font("OCR A Extended", 0, 36)); // NOI18N
        backwardButton1.setText("v");
        backwardButton1.setBackground(new Color(0,0,0,0));

        rightButton1.setFont(new java.awt.Font("OCR A Extended", 0, 36)); // NOI18N
        rightButton1.setText(">");
        rightButton1.setBackground(new Color(0,0,0,0));

        forwardButton1.setFont(new java.awt.Font("OCR A Extended", 0, 36)); // NOI18N
        forwardButton1.setText("^");
        forwardButton1.setBackground(new Color(0,0,0,0));

        spaceR1.setFont(new java.awt.Font("OCR A Extended", 1, 36)); // NOI18N
        spaceR1.setText("|");
        spaceR1.setBackground(new Color(0,0,0,0));

        spaceL1.setFont(new java.awt.Font("OCR A Extended", 1, 36)); // NOI18N
        spaceL1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        spaceL1.setText("|");
        spaceL1.setBackground(new Color(0,0,0,0));

        jCheckBox1.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jCheckBox1.setText("Player 2 connected ");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox3.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jCheckBox3.setText("Player 1 connected");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jToggleButton1.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jToggleButton1.setText("Car 1 on");

        jToggleButton2.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jToggleButton2.setText("Car 1 on");

        jRadioButton1.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jRadioButton1.setText("Steering 1 switched");

        jRadioButton2.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jRadioButton2.setText("Steering 2 switched");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBox3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(spaceL1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(leftButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(forwardButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(backwardButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(rightButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(spaceR1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(66, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(184, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(jToggleButton1)
                                .addGap(54, 54, 54)
                                .addComponent(jRadioButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(forwardButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(leftButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(backwardButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(rightButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(spaceR1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spaceL1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(148, 148, 148)
                                        .addComponent(jToggleButton2)
                                        .addContainerGap(418, Short.MAX_VALUE)))
        );

        jPanel2.setBackground(new java.awt.Color(0, 51, 153));

        leftButton2.setFont(new java.awt.Font("OCR A Extended", 0, 36)); // NOI18N
        leftButton2.setText("<");
        leftButton2.setBackground(new Color(0,0,0,0));

        backwardButton2.setFont(new java.awt.Font("OCR A Extended", 0, 36)); // NOI18N
        backwardButton2.setText("v");
        backwardButton2.setBackground(new Color(0,0,0,0));

        forwardButton2.setFont(new java.awt.Font("OCR A Extended", 0, 36)); // NOI18N
        forwardButton2.setText("^");
        forwardButton2.setBackground(new Color(0,0,0,0));

        rightButton2.setFont(new java.awt.Font("OCR A Extended", 0, 36)); // NOI18N
        rightButton2.setText(">");
        rightButton2.setBackground(new Color(0,0,0,0));

        spaceR2.setFont(new java.awt.Font("OCR A Extended", 1, 36)); // NOI18N
        spaceR2.setText("|");
        spaceR2.setBackground(new Color(0,0,0,0));

        spaceL2.setFont(new java.awt.Font("OCR A Extended", 1, 36)); // NOI18N
        spaceL2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        spaceL2.setText("|");
        spaceL2.setBackground(new Color(0,0,0,0));

        jLabel2.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel2.setText("Goal sensor 1");

        jLabel3.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel3.setText("Hatch 2");

        jLabel4.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel4.setText("Sensor 1");

        jLabel5.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel5.setText("Goal senor 2");

        jLabel6.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel6.setText("Goal 1");

        jLabel7.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel7.setText("Hatch 1");

        jLabel8.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel8.setText("Sensor 2");

        jLabel9.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel9.setText("Elevetor ");

        jLabel10.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel10.setText("Goal 2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(spaceL2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(leftButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(forwardButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(backwardButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(7, 7, 7)
                                                .addComponent(rightButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spaceR2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(21, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(forwardButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(leftButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(rightButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(backwardButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(spaceR2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spaceL2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public void updateButton(String action, int id){

        String[] expression = action.split("/");

        switch (expression[1]){
            case "F":
                updateForwardButton(expression[2], id);
                break;
            case "B":
                updateBackwardButton(expression[2], id);
                break;
            case "L":
                updateLeftButton(expression[2], id);
                break;
            case "R":
                updateRightButton(expression[2], id);
                break;
            case "S":
                updateSpaceButton(expression[2], id);
                break;
        }
    }

    private void updateForwardButton(String action, int id){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (id == 1){
                    if (action.equals("1")){
                        forwardButton1.setForeground(new java.awt.Color(255, 0, 51));
                    } else {
                        forwardButton1.setForeground(Color.BLACK);
                    }
                } else {
                    if (action.equals("1")){
                        forwardButton2.setForeground(new java.awt.Color(255, 0, 51));
                    } else {
                        forwardButton2.setForeground(Color.BLACK);
                    }
                }
            }
        });
    }

    private void updateBackwardButton(String action, int id){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (id == 1){
                    if (action.equals("1")){
                        backwardButton1.setForeground(new java.awt.Color(255, 0, 51));
                    } else {
                        backwardButton1.setForeground(Color.BLACK);
                    }
                } else {
                    if (action.equals("1")){
                        backwardButton2.setForeground(new java.awt.Color(255, 0, 51));
                    } else {
                        backwardButton2.setForeground(Color.BLACK);
                    }
                }
            }
        });
    }

    private void updateLeftButton(String action, int id){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (id == 1){
                    if (action.equals("1")){
                        leftButton1.setForeground(new java.awt.Color(255, 0, 51));
                    } else {
                        leftButton1.setForeground(Color.BLACK);
                    }
                } else {
                    if (action.equals("1")){
                        leftButton2.setForeground(new java.awt.Color(255, 0, 51));
                    } else {
                        leftButton2.setForeground(Color.BLACK);
                    }
                }
            }
        });
    }

    private void updateRightButton(String action, int id){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (id == 1){
                    if (action.equals("1")){
                        rightButton1.setForeground(new java.awt.Color(255, 0, 51));
                    } else {
                        rightButton1.setForeground(Color.BLACK);
                    }
                } else {
                    if (action.equals("1")){
                        rightButton2.setForeground(new java.awt.Color(255, 0, 51));
                    } else {
                        rightButton2.setForeground(Color.BLACK);
                    }
                }
            }
        });
    }

    private void updateSpaceButton(String action, int id){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (id == 1){
                    if (action.equals("1")){
                        spaceL1.setForeground(new java.awt.Color(255, 0, 51));
                        spaceR1.setForeground(new Color(255,0,51));
                    } else {
                        spaceR1.setForeground(Color.BLACK);
                        spaceL1.setForeground(Color.BLACK);
                    }
                } else {
                    if (action.equals("1")){
                        spaceL2.setForeground(new java.awt.Color(255, 0, 51));
                        spaceR2.setForeground(new Color(255,0,51));
                    } else {
                        spaceR2.setForeground(Color.BLACK);
                        spaceL2.setForeground(Color.BLACK);
                    }
                }
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton backwardButton1;
    private javax.swing.JButton backwardButton2;
    private javax.swing.JButton forwardButton1;
    private javax.swing.JButton forwardButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JButton leftButton1;
    private javax.swing.JButton leftButton2;
    private javax.swing.JButton rightButton1;
    private javax.swing.JButton rightButton2;
    private javax.swing.JLabel spaceL1;
    private javax.swing.JLabel spaceL2;
    private javax.swing.JLabel spaceR1;
    private javax.swing.JLabel spaceR2;
    private Server server;
    // End of variables declaration
}

