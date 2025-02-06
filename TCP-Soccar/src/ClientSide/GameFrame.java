
package ClientSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The frame showed during the active game.
 * @author julia
 */
        public class GameFrame extends javax.swing.JFrame {
            private javax.swing.JButton backwardButton;
            private javax.swing.JButton cButton;
            private javax.swing.JButton forwardButton;
            private javax.swing.JPanel jPanel1;
            private javax.swing.JPanel jPanel2;
            private javax.swing.JPanel jPanel3;
            private javax.swing.JButton leftButton;
            private javax.swing.JButton pauseButton;
            private javax.swing.JButton rightButton;
            private javax.swing.JButton spaceButton;
            private javax.swing.JButton xButton;
            private javax.swing.JButton zButton;
            private final int id;
            private final Client client;
            private JLabel timeLabel;
            private ClientTimer gamingTimer;
            private int gameMode;
            private int round = 7;
            private JLabel scoreLabel;
            private String score;
            private int powerUps;
            private JLabel powerUpLabel;

            /**
             * Constructor that needs the various parameters to show up in the right format.
             * @param location of the screen
             * @param width of the frame
             * @param height of the frame
             * @param client the client controller class
             * @param id the player number
             */
            public GameFrame(Point location, int width, int height, Client client, int id, int gameMode) {
                this.id = id;
                this.client = client;
                this.gameMode = gameMode;
                this.score = "0:0";
                this.powerUps = 3;
                initComponents(location, width, height);
                setGameMode(gameMode);
            }

            private void initComponents(Point location, int width, int height) {

                jPanel1 = new javax.swing.JPanel();
                JLabel playerLabel = new JLabel();
                JLabel offLabel = new JLabel();
                JLabel offPic = new JLabel();
                backwardButton = new javax.swing.JButton();
                forwardButton = new javax.swing.JButton();
                leftButton = new javax.swing.JButton();
                rightButton = new javax.swing.JButton();
                spaceButton = new javax.swing.JButton();
                jPanel2 = new javax.swing.JPanel();
                JLabel scoreboardLabel = new JLabel();
                scoreLabel = new JLabel();
                powerUpLabel = new JLabel();
                JLabel switchLabel = new JLabel();
                JLabel switchPic = new JLabel();
                xButton = new javax.swing.JButton();
                zButton = new javax.swing.JButton();
                cButton = new javax.swing.JButton();
                jPanel3 = new javax.swing.JPanel();
                timeLabel = new JLabel();
                JLabel goalLabel = new JLabel();
                JLabel goalPic = new JLabel();
                pauseButton = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setPreferredSize(new Dimension(width, height));
                setLocation(location);

                jPanel1.setPreferredSize(new Dimension(width/3, height));
                jPanel2.setPreferredSize(new Dimension(width/3, height));
                jPanel3.setPreferredSize(new Dimension(width/3, height));

                jPanel1.setBackground(new java.awt.Color(0, 51, 153));

                playerLabel.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
                playerLabel.setForeground(Color.WHITE);
                playerLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
                playerLabel.setText("Player " + id + " ");

                offLabel.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
                offLabel.setForeground(Color.WHITE);
                offLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                offLabel.setText("Shut down");

                offPic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                offPic.setIcon(new ImageIcon("files/car.PNG")); // NOI18N

                backwardButton.setFont(new java.awt.Font("OCR A Extended", 1, 18)); // NOI18N
                backwardButton.setText("v");
                backwardButton.setBorderPainted(false);
                backwardButton.setBackground(new Color(0,0,0,0));

                forwardButton.setFont(new java.awt.Font("OCR A Extended", 1, 18)); // NOI18N
                forwardButton.setText("^");
                forwardButton.setBorderPainted(false);
                forwardButton.setBackground(new Color(0,0,0,0));

                leftButton.setFont(new java.awt.Font("OCR A Extended", 1, 18)); // NOI18N
                leftButton.setText("<");
                leftButton.setBorderPainted(false);
                leftButton.setBackground(new Color(0,0,0,0));

                rightButton.setFont(new java.awt.Font("OCR A Extended", 1, 18)); // NOI18N
                rightButton.setText(">");
                rightButton.setBorderPainted(false);
                rightButton.setBackground(new Color(0,0,0,0));

                spaceButton.setFont(new java.awt.Font("OCR A Extended", 1, 18)); // NOI18N
                spaceButton.setText("---");
                spaceButton.setBorderPainted(false);
                spaceButton.setBackground(new Color(0,0,0,0));

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap(101, Short.MAX_VALUE)
                                        .addComponent(spaceButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(leftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(forwardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(backwardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(rightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(16, 16, 16))
                                .addComponent(playerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(offLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(offPic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(playerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(221, 221, 221)
                                        .addComponent(offLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(offPic, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                        .addComponent(forwardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(spaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(rightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(backwardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(leftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap())
                );

                jPanel2.setBackground(new java.awt.Color(0, 51, 153));

                scoreboardLabel.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
                scoreboardLabel.setForeground(Color.WHITE);
                scoreboardLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                scoreboardLabel.setText("Scoreboard");
                scoreboardLabel.setToolTipText("");

                scoreLabel.setFont(new java.awt.Font("OCR A Extended", 1, 100)); // NOI18N
                scoreLabel.setForeground(new java.awt.Color(255, 53, 0));
                scoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                scoreLabel.setText(score);

                powerUpLabel.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
                powerUpLabel.setForeground(new java.awt.Color(255, 255, 255));
                powerUpLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                powerUpLabel.setText("Power ups: " + powerUps);

                switchLabel.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
                switchLabel.setForeground(Color.WHITE);
                switchLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                switchLabel.setText("Switch steering");

                switchPic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                switchPic.setIcon(new ImageIcon("files/switch.png")); // NOI18N

                xButton.setFont(new java.awt.Font("OCR A Extended", 1, 18)); // NOI18N
                xButton.setText("X");
                xButton.setBorderPainted(false);
                xButton.setBackground(new Color(0,0,0,0));

                zButton.setFont(new java.awt.Font("OCR A Extended", 1, 18)); // NOI18N
                zButton.setText("Z");
                zButton.setBorderPainted(false);
                zButton.setBackground(new Color(0,0,0,0));

                cButton.setFont(new java.awt.Font("OCR A Extended", 1, 18)); // NOI18N
                cButton.setText("C");
                cButton.setBorderPainted(false);
                cButton.setBackground(new Color(0,0,0,0));

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(zButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(xButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(scoreboardLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scoreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(switchLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(switchPic, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                                .addComponent(powerUpLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(scoreboardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(scoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(powerUpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(switchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(switchPic, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(xButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(zButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap())
                );

                jPanel3.setBackground(new java.awt.Color(0, 51, 153));

                timeLabel.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
                timeLabel.setForeground(Color.WHITE);
                timeLabel.setText("Time 00:00");

                goalLabel.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
                goalLabel.setForeground(Color.WHITE);
                goalLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                goalLabel.setText("Close goal");

                goalPic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                goalPic.setIcon(new ImageIcon("files/goal.PNG")); // NOI18N

                pauseButton.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
                pauseButton.setForeground(new java.awt.Color(255, 53, 0));
                pauseButton.setText("PAUSE");
                pauseButton.setBorderPainted(false);
                pauseButton.setBackground(new Color(0,0,0,0));

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(timeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(goalLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(goalPic, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                );
                jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(224, 224, 224)
                                        .addComponent(goalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(goalPic, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                addKeyListener(new MyKeyListener());
                setFocusable(true);
                requestFocus();
                pack();
            }

            public void updateTimeLabel(String timeString){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        timeLabel.setText(timeString);
                    }
                });
            }

            private void updateButton(Color color, JButton button){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        button.setForeground(color);
                    }
                });
            }

            public void pause(){
                gamingTimer.pause();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        PopUpPanel newPanel = new PopUpPanel(getFrame());

                        setLayout(new BorderLayout());

                        add(newPanel, BorderLayout.CENTER);

                        jPanel1.setVisible(false);
                        jPanel2.setVisible(false);
                        jPanel3.setVisible(false);

                        newPanel.setLabelText("The game is paused", "Press enter to resume", "||");
                        newPanel.setPanelColor(new Color(153,153,153));
                        newPanel.setBlackFont();
                        newPanel.setVisible(true);

                        revalidate();
                    }
                });

            }

            public void resumeGameFrame(){
                client.sendInformationToServer("E");
            }

            public void resume(){
                gamingTimer.resume();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        getContentPane().removeAll();
                        initComponents(getLocation(), getWidth(), getHeight());
                        revalidate();
                        repaint();
                    }
                });
            }

            public void setWinnerPanel(Boolean isWinner){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        PopUpPanel newPanel = new PopUpPanel(getFrame());

                        setLayout(new BorderLayout());

                        add(newPanel, BorderLayout.CENTER);

                        jPanel1.setVisible(false);
                        jPanel2.setVisible(false);
                        jPanel3.setVisible(false);

                        if (isWinner) {
                            newPanel.setLabelText("Better luck next time...", "The count down starts when both cars is on the start panels", "5");
                            newPanel.setVisible(true);
                        } else {
                            newPanel.setLabelText("NICE YOU SCORED!", "The count down starts when both cars is on the start panels", "5");
                            newPanel.setVisible(true);
                        }
                        revalidate();
                    }
                });
            }

            private GameFrame getFrame(){
                return this;
            }

            public void updateScores(String score){

                this.score = score;

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (gameMode == 1){
                            round--;
                            timeLabel.setText( round + " round left");
                        }
                        scoreLabel.setText(score);
                        PopUpPanel newPanel = new PopUpPanel(getFrame());

                        setLayout(new BorderLayout());

                        add(newPanel, BorderLayout.CENTER);

                        jPanel1.setVisible(false);
                        jPanel2.setVisible(false);
                        jPanel3.setVisible(false);


                        newPanel.setLabelText("Return to your start position", "The lights will turn green when ready", "((O))");
                        newPanel.setBackground(new java.awt.Color(0, 51, 153));
                        newPanel.setVisible(true);

                        revalidate();
                    }
                });
            }

            public void updatePowerUpLabel(){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        powerUpLabel.setText("Power ups: " + powerUps);
                    }
                });
            }

            public void countDown(){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        getContentPane().removeAll();
                        initComponents(getLocation(), getWidth(), getHeight());
                        revalidate();
                        repaint();
                    }
                });
            }
            public void setGameMode(int mode){
                switch (mode){
                    case 2:
                        gamingTimer = new ClientTimer(this);
                        gamingTimer.start(0, 10);
                        break;
                    case 0:
                        timeLabel.setText("First to 3-mode");
                        break;
                    case 1:
                        timeLabel.setText( round + " round left");
                        break;
                }
            }

            public void endGame(){
                client.sendInformationToServer("EndGame");
            }

            public void endGame(int winner) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        PopUpPanel newPanel = new PopUpPanel(getFrame());

                        setLayout(new BorderLayout());

                        add(newPanel, BorderLayout.CENTER);

                        jPanel1.setVisible(false);
                        jPanel2.setVisible(false);
                        jPanel3.setVisible(false);

                        if(winner == 1){
                            newPanel.setLabelText("Congratulations you are the winner!", "You won the game", "0:0");
                            newPanel.setBackground(Color.green);
                        } else if (winner == 0){
                            newPanel.setLabelText("Sorry you're the loser...", "The game is over", "0:0");
                            newPanel.setBackground(Color.red);
                        } else {
                            newPanel.setLabelText("Tie", "The game is over", "0:0");
                            newPanel.setBackground(Color.blue);
                        }

                        newPanel.setBlackFont();
                        newPanel.setVisible(true);

                        revalidate();
                    }
                });
            }

    public class MyKeyListener implements KeyListener {

                @Override
                public void keyTyped(KeyEvent e) {}

                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            client.sendInformationToServer("A/F/1");
                            updateButton(new java.awt.Color(255, 53, 0), forwardButton);
                            break;
                        case KeyEvent.VK_ENTER:
                            client.sendInformationToServer("E");
                            break;
                        case KeyEvent.VK_DOWN:
                            client.sendInformationToServer("A/B/1");
                            updateButton(new java.awt.Color(255, 53, 0), backwardButton);
                            break;
                        case KeyEvent.VK_LEFT:
                            client.sendInformationToServer("A/L/1");
                            updateButton(new java.awt.Color(255, 53, 0), leftButton);
                            break;
                        case KeyEvent.VK_RIGHT:
                            client.sendInformationToServer("A/R/1");
                            updateButton(new java.awt.Color(255, 53, 0), rightButton);
                            break;
                        case KeyEvent.VK_Z:
                            updateButton(new java.awt.Color(255, 53, 0), zButton);
                            if(powerUps > 0) {
                                client.sendInformationToServer("Z");
                                updatePowerUpLabel();
                                powerUps--;
                            }
                            break;
                        case KeyEvent.VK_X:
                            updateButton(new java.awt.Color(255, 53, 0), xButton);
                            if (powerUps > 0) {
                                client.sendInformationToServer("X");
                                updatePowerUpLabel();
                                powerUps--;
                            }
                            break;
                        case KeyEvent.VK_C:
                            updateButton(new java.awt.Color(255, 53, 0), cButton);
                            if (powerUps > 0) {
                                client.sendInformationToServer("C");
                                updatePowerUpLabel();
                                powerUps--;
                            }
                            break;
                        case KeyEvent.VK_SPACE:
                            client.sendInformationToServer("S/1");
                            updateButton(new java.awt.Color(255, 53, 0), spaceButton);
                            break;
                    }
                }


                @Override
                public void keyReleased(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            client.sendInformationToServer("A/F/0");
                            updateButton(Color.BLACK, forwardButton);
                            break;
                        case KeyEvent.VK_DOWN:
                            client.sendInformationToServer("A/B/0");
                            updateButton(Color.BLACK, backwardButton);
                            break;
                        case KeyEvent.VK_LEFT:
                            client.sendInformationToServer("A/L/0");
                            updateButton(Color.BLACK, leftButton);
                            break;
                        case KeyEvent.VK_RIGHT:
                            client.sendInformationToServer("A/R/0");
                            updateButton(Color.BLACK, rightButton);
                            break;
                        case KeyEvent.VK_Z:
                            updateButton(Color.BLACK, zButton);
                            break;
                        case KeyEvent.VK_X:
                            updateButton(Color.BLACK, xButton);
                            break;
                        case KeyEvent.VK_C:
                            updateButton(Color.BLACK, cButton);
                            break;
                        case KeyEvent.VK_SPACE:
                            client.sendInformationToServer("A/S/0");
                            updateButton(Color.BLACK, spaceButton);
                            break;
                    }
                }
            }
        }