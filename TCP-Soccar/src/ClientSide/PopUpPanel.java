package ClientSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The panel used in various situations using the same layout.
 * @author julia
 */
public class PopUpPanel extends javax.swing.JPanel {
    private javax.swing.JLabel bigLabel;
    private javax.swing.JLabel explanationLabel;
    private javax.swing.JLabel textLabel;
    private final GameFrame gameFrame;

    public PopUpPanel(GameFrame frame) {
        this.gameFrame = frame;
        initComponents();
    }

    /**
     * Creating all the basics without text labels or specific colors.
     */
    private void initComponents() {

        textLabel = new javax.swing.JLabel();
        bigLabel = new javax.swing.JLabel();
        explanationLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 0, 204));

        textLabel.setFont(new java.awt.Font("OCR A Extended", 1, 36)); // NOI18N
        textLabel.setForeground(Color.WHITE);
        textLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        bigLabel.setFont(new java.awt.Font("OCR A Extended", 1, 180)); // NOI18N
        bigLabel.setForeground(new java.awt.Color(255, 53, 0));
        bigLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        explanationLabel.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        explanationLabel.setForeground(Color.WHITE);
        explanationLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bigLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(explanationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(textLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(explanationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bigLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
        );
        addKeyListener(new MyKeyListener());
        setFocusable(true);
        requestFocus();
    }

    public void setLabelText(String headLine, String explanation,String bigText){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                textLabel.setText(headLine);
                explanationLabel.setText(explanation);
                bigLabel.setText(bigText);
            }
        });
    }

    public void setPanelColor(Color color){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setBackground(color);
            }
        });
    }

    public void setBlackFont(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                explanationLabel.setForeground(Color.BLACK);
                bigLabel.setForeground(Color.BLACK);
                textLabel.setForeground(Color.BLACK);
            }
        });
    }


    public class MyKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER){
                gameFrame.resumeGameFrame();
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {}
    }
}

