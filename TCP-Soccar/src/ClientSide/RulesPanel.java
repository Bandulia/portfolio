package ClientSide;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel that shows the game rules.
 * @author julia
 */
public class RulesPanel extends javax.swing.JPanel {
    private javax.swing.JButton backButton;
    private final MenuFrame frame;

    public RulesPanel(MenuFrame frame) {
        this.frame = frame;
        initComponents();
    }

    private void initComponents() {

        backButton = new javax.swing.JButton();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        javax.swing.JTextPane jTextPane1 = new javax.swing.JTextPane();

        setBackground(new java.awt.Color(0, 51, 153));

        backButton.setBackground(new java.awt.Color(0, 0, 0,0));
        backButton.setBorderPainted(false);
        backButton.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        backButton.setText("Back");
        backButton.setForeground(new java.awt.Color(204, 0, 51));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.backToMenu();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setFont(new java.awt.Font("OCR A Extended", Font.BOLD | Font.ITALIC, 14));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setFont(new java.awt.Font("OCR A Extended", 1, 14));
            }
        });

        jLabel1.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TCP SOCCAR RULES");
        jLabel1.setForeground(new java.awt.Color(204, 0, 51));

        jTextPane1.setBackground(new java.awt.Color(0, 51, 153));
        jTextPane1.setBorder(null);
        jTextPane1.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jTextPane1.setForeground(new java.awt.Color(255, 255, 0));
        jTextPane1.setText("- Control your car with the arrow keys \n\n- To get a score get the boll in the oponents goal \n\n- If the light on your side blinks you can enter the field\n  and press space to catch a coin \n\n- Use you coin to activate a power up by pressing the button\n  on the screen \n\n- To start the next round both cars needs to be placed on \n  their field (the light will turn from red to green\n  when the car is places on the field)  \n\n- When buying the \"goal close\" your goal will be closed for  \n  5 seconds \n\n- When bying the \"swith keys\" the oponents keys will become \n  the opposet for 5 seconds  \n\n- When bying the \"turn off\" the opponents car will stop \n  working for 3 seconds  \n\n- You can press pause to pause the game for both of you ");
        jScrollPane2.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42))
        );
    }
}
