package javaswingdev;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.*;

public class SimpleTitleBar extends javax.swing.JPanel {

    private ComponentResizer resize;
    private int x;
    private int y;

    private ImageIcon icon;
    private JLabel titleLabel;
    private JLabel iconLabel;

    public ComponentResizer getResize() {
        return this.resize;
    }

    public SimpleTitleBar() {
        initComponents();
    }

    public void init(JFrame frame) {
        resize = new ComponentResizer();
        resize.setSnapSize(new Dimension(10, 10));
        resize.setMinimumSize(new Dimension(300, 200));
        resize.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        resize.registerComponent(frame);
        initMoving(frame);
        simpleButtonBar1.initEvent(frame);
    }

    public void init(JFrame frame, ImageIcon icon, String title) {
        resize = new ComponentResizer();
        resize.setSnapSize(new Dimension(10, 10));
        resize.setMinimumSize(new Dimension(300, 200));
        resize.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        resize.registerComponent(frame);
        initMoving(frame);
        simpleButtonBar1.initEvent(frame);

        this.icon = icon;
        this.titleLabel = new JLabel(title);

        ImageIcon titleIcon = new ImageIcon(icon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));

        this.iconLabel = new JLabel(titleIcon);
        this.iconLabel.setBounds(3, 2, 25, 25);

        this.titleLabel.setBounds(40, 2, 1000, 25);
        this.titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        this.titleLabel.setForeground(new Color(186, 148, 245));

        add(this.iconLabel);
        add(this.titleLabel);
    }

    private void initMoving(JFrame fram) {
        panelMove.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (fram.getExtendedState() != JFrame.MAXIMIZED_BOTH && SwingUtilities.isLeftMouseButton(me)) {
                    x = me.getX() + 3;
                    y = me.getY() + 3;
                }
            }

            @Override
            public void mouseClicked(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me) && me.getClickCount() == 2) {
                    if (fram.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                        fram.setExtendedState(JFrame.NORMAL);
                    } else {
                        fram.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    }
                }
            }

        });
        panelMove.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (fram.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                        fram.setExtendedState(JFrame.NORMAL);
                    }
                    fram.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMove = new javax.swing.JPanel();
        simpleButtonBar1 = new SimpleButtonBar();

        setBackground(new java.awt.Color(50, 50, 50));

        panelMove.setOpaque(false);

        javax.swing.GroupLayout panelMoveLayout = new javax.swing.GroupLayout(panelMove);
        panelMove.setLayout(panelMoveLayout);
        panelMoveLayout.setHorizontalGroup(
            panelMoveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 865, Short.MAX_VALUE)
        );
        panelMoveLayout.setVerticalGroup(
            panelMoveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(panelMove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(simpleButtonBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(panelMove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(simpleButtonBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }

    private JPanel panelMove;
    private SimpleButtonBar simpleButtonBar1;

    public SimpleButtonBar getSimpleButtonBar1() {
        return simpleButtonBar1;
    }
}
