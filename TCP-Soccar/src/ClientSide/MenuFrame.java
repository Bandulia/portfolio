package ClientSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The frame displaying the menu options.
 * @author julia
 */
public class MenuFrame extends javax.swing.JFrame {
    private javax.swing.JLabel explanationLabel;
    private javax.swing.JButton gameTypeButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton readyButton;
    private javax.swing.JButton rulesButton;
    private int gameType;
    private final int id;
    private final Client client;
    private int powerUps;

    /**
     * Constructor that initializes the controller class "client" and the players number
     * @param client the client controller class
     * @param id the player number
     */
    public MenuFrame(Client client, int id) {
        this.client = client;
        this.id = id;
        this.gameType = 0;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initComponents();
            }
        });
    }

    private void initComponents() {


        jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new JLabel();
        jPanel2 = new javax.swing.JPanel();
        JLabel menuLabel = new JLabel();
        readyButton = new javax.swing.JButton();
        gameTypeButton = new javax.swing.JButton();
        rulesButton = new javax.swing.JButton();
        explanationLabel = new javax.swing.JLabel();
        JLabel playerLabel = new JLabel();
        JLabel gameLabel = new JLabel();
        jPanel3 = new javax.swing.JPanel();
        JLabel jLabel2 = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(230, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setIcon(new javax.swing.ImageIcon("files/vänster.png")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 51, 153));
        jPanel2.setPreferredSize(new java.awt.Dimension(230, 0));

        menuLabel.setFont(new java.awt.Font("OCR A Extended", 1, 36)); // NOI18N
        menuLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuLabel.setText("Menu");
        menuLabel.setForeground(new java.awt.Color(255, 53, 0));


        readyButton.setBorderPainted(false);
        readyButton.setBackground(new java.awt.Color(0, 0, 0, 0));
        readyButton.setFont(new java.awt.Font("OCR A Extended", Font.BOLD, 18)); // NOI18N
        readyButton.setText("Ready");
        readyButton.setForeground(Color.WHITE);
        readyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (readyButton.isEnabled()) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {

                            readyButton.setForeground(Color.GRAY);
                            readyButton.setFont(new java.awt.Font("OCR A Extended", Font.ITALIC | Font.BOLD, 18));
                            readyButton.setEnabled(false);
                            gameTypeButton.setForeground(Color.GRAY);
                            gameTypeButton.setFont(new java.awt.Font("OCR A Extended", Font.ITALIC | Font.BOLD, 18));
                            gameTypeButton.setEnabled(false);
                            rulesButton.setForeground(Color.GRAY);
                            rulesButton.setFont(new java.awt.Font("OCR A Extended", Font.ITALIC | Font.BOLD, 18));
                            rulesButton.setEnabled(false);
                            explanationLabel.setText("Waiting on the opponent...");

                            client.sendInformationToServer("Ready/");
                        }
                    });
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (readyButton.isEnabled()) {
                            explanationLabel.setText("Declare ready");
                            readyButton.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 18));
                        }
                    }
                });

            }

            @Override
            public void mouseExited(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (readyButton.isEnabled()) {
                            explanationLabel.setText("");
                            readyButton.setFont(new Font("OCR A Extended", Font.BOLD, 18));
                        }
                    }
                });

            }
        });


        gameTypeButton.setText("First to score 3");
        gameTypeButton.setBorderPainted(false);
        gameTypeButton.setBackground(new java.awt.Color(0, 0, 0,0));
        gameTypeButton.setFont(new java.awt.Font("OCR A Extended", Font.BOLD, 18)); // NOI18N
        gameTypeButton.setForeground(Color.WHITE);
        if (id==2){
            gameTypeButton.setFont(new java.awt.Font("OCR A Extended", Font.ITALIC | Font.BOLD, 18));
            gameTypeButton.setForeground(Color.GRAY);
        }
        gameTypeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (gameTypeButton.isEnabled()) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            if (id == 1) {
                                setGameType();
                            }
                        }
                    });
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (gameTypeButton.isEnabled()) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            explanationLabel.setText("Player 1 can click to change game");
                            gameTypeButton.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 18));
                        }
                    });
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (gameTypeButton.isEnabled()) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            explanationLabel.setText("");
                            gameTypeButton.setFont(new java.awt.Font("OCR A Extended", Font.BOLD, 18)); // NOI18N
                        }
                    });
                }
            }
        });

        rulesButton.setBackground(new java.awt.Color(0, 0, 0, 0));
        rulesButton.setFont(new java.awt.Font("OCR A Extended", Font.BOLD, 18)); // NOI18N
        rulesButton.setText("Rules");
        rulesButton.setBorderPainted(false);
        rulesButton.setForeground(Color.WHITE);
        rulesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (rulesButton.isEnabled()) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            rules();
                        }
                    });
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (rulesButton.isEnabled()) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            explanationLabel.setText("Read the rules for TCP soccar");
                            rulesButton.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 18));
                        }
                    });
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (rulesButton.isEnabled()) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            explanationLabel.setText("");
                            rulesButton.setFont(new Font("OCR A Extended", Font.BOLD, 18));
                        }
                    });
                }
            }
        });

        explanationLabel.setFont(new java.awt.Font("OCR A Extended", 2, 14)); // NOI18N
       // explanationLabel.setForeground(new java.awt.Color(204, 0, 51));
        explanationLabel.setForeground(new java.awt.Color(255, 53, 0));
        explanationLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        playerLabel.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        playerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerLabel.setText("Player " + id);
        playerLabel.setForeground(new java.awt.Color(255, 53, 0));

        gameLabel.setFont(new java.awt.Font("OCR A Extended", 3, 36)); // NOI18N
        gameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameLabel.setText("TCP SOCCAR");
        gameLabel.setForeground(new java.awt.Color(255, 53, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(menuLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(explanationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                        .addComponent(playerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gameTypeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(readyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(rulesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(gameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(menuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(readyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(gameTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rulesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(explanationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 51, 153));
        jPanel3.setPreferredSize(new java.awt.Dimension(230, 0));

        jLabel2.setIcon(new javax.swing.ImageIcon("files/höger.png")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 4, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 12, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(0, 0, 0)
                                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE))
                                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();
    }

    public void backToMenu(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                getContentPane().removeAll();
                initComponents();
            }
        });
    }

    private void rules(){

        JPanel rulesPanel = new RulesPanel(this);

        setLayout(new BorderLayout());

        add(rulesPanel, BorderLayout.CENTER);

        jPanel1.setVisible(false);
        jPanel2.setVisible(false);
        jPanel3.setVisible(false);

        revalidate();
    }

    private void setGameType(){
        if(gameType == 0){
            this.gameType = 1;
            client.sendInformationToServer("G/1");
            gameTypeButton.setText("7 rounds");
        } else if (gameType == 1) {
            this.gameType = 2;
            client.sendInformationToServer("G/2");
            gameTypeButton.setText("5 minutes game");
        } else {
            this.gameType = 0;
            client.sendInformationToServer("G/0");
            gameTypeButton.setText("First to score 3");
        }
    }

    public void setGameType(String type){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                switch (type) {
                    case "0" -> {
                        gameType = 0;
                        gameTypeButton.setText("First to score 3");
                    }
                    case "1" -> {
                        gameType = 1;
                        gameTypeButton.setText("7 rounds");
                    }
                    case "2" -> {
                        gameType = 2;
                        gameTypeButton.setText("5 minutes game");
                    }
                }
            }
        });
    }

    public void setUpGameFrame(){
        GameFrame frame = new GameFrame(this.getLocation(), this.getWidth(), this.getHeight(), client, id, gameType);
        frame.setVisible(true);
        client.setGameFrame(frame);
        this.dispose();
    }

    public void incrementPowerUp() {
        powerUps++;
    }
}
