package javaswingdev;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

/**
 * @author RAVEN
 */
public class Message extends JPanel {

    public Message() {
        initComponents();
        setOpaque(false);
        text.setBackground(new Color(0, 0, 0, 0));
        text.setSelectionColor(new Color(160, 90, 255, 200));
        text.setOpaque(false);
    }

    private void initComponents() {
        titleLabel = new JLabel();
        text = new JTextPane();
        cmdOK = new CustomButton();
        //cmdCancel = new CustomButton();

        setBackground(new java.awt.Color(225, 225, 225));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));

        titleLabel.setFont(new java.awt.Font("sansserif", 0, 24));
        titleLabel.setForeground(new java.awt.Color(25, 25, 25));

        text.setFont(new Font("TimesRoman",0, 14));
        text.setForeground(new java.awt.Color(50, 50, 50));
        text.setEditable(false);

        cmdOK.setBackground(new java.awt.Color(48, 170, 63));
        cmdOK.setForeground(new java.awt.Color(255, 255, 255));
        cmdOK.setStyle(CustomButton.ButtonStyle.SECONDARY);
        cmdOK.setText("OK");

        //cmdCancel.setBackground(new java.awt.Color(233, 233, 233));
        //cmdCancel.setText("Cancel");
        /*cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });*/

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(titleLabel)
                                                .addGap(0, 260, Short.MAX_VALUE))
                                        .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                //.addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        //.addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                //.addContainerGap())
        )));
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        g2.dispose();
        super.paintComponent(grphcs);
    }


    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {
        GlassPanePopup.closePopupLast();
    }

    public void eventOK(ActionListener event) {
        cmdOK.addActionListener(event);
    }

    private CustomButton cmdCancel;
    private CustomButton cmdOK;
    public JLabel titleLabel;
    public JTextPane text;

}
