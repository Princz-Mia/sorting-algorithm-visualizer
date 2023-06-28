package com.mia_princz.graphics;

import com.javaswingdev.SimpleTitleBar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The VisualizerWindow class represents the main window of the sorting algorithms visualizer.
 * It extends the JFrame class and contains panels for visualization and settings.
 */
public class VisualizerWindow extends JFrame {

    // Constants for panel dimensions and settings
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 900;

    // Images
    private static ImageIcon titleIcon;
    private static BufferedImage frameIcon;

    /**
     * Constructs a new VisualizerWindow object.
     * Initializes the window, icon images, and the title bar.
     * Creates and adds the visualizer panel and settings panel to the window.
     */
    public VisualizerWindow() {
        initIconImage();
        initWindow();
        initTitleBar();

        VisualizerPanel panelVisualizer = new VisualizerPanel();
        SettingsPanel panelSettings = new SettingsPanel(panelVisualizer);

        add(panelVisualizer);
        add(panelSettings);
    }

    /**
     * Initializes the main window of the visualizer.
     * Sets the title, icon image, size, background color, default close operation,
     * layout, resizable property, and location of the window.
     */
    private void initWindow() {
        setTitle("Sorting Algorithms Visualizer");
        setIconImage(frameIcon);
        setSize(WIDTH,HEIGHT + 78);
        setUndecorated(true);
        getContentPane().setBackground( Color.BLACK );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /**
     * Initializes the title bar of the window.
     * Configures the title bar appearance, bounds, and resize options.
     * Disables the resize button and sets the minimum and maximum size to the window dimensions.
     */
    private void initTitleBar() {
        SimpleTitleBar titleBar = new SimpleTitleBar();
        titleBar.init(this, titleIcon, "Sorting Algorithms Visualizer");
        titleBar.setBounds(0,0, 1600, 28);

        // Toggle Resize button
        titleBar.getSimpleButtonBar1().getCmdResize().setEnabled(false);
        titleBar.getSimpleButtonBar1().getCmdResize().setHoverColor(new Color(0,0,0,0));

        // Toggle Resize option
        titleBar.getResize().setMinimumSize(new Dimension(WIDTH, HEIGHT + 78));
        titleBar.getResize().setMaximumSize(new Dimension(WIDTH, HEIGHT + 78));

        add(titleBar);
    }

    /**
     * Loads the icon images for the window.
     * Sets the title icon and frame icon by reading image files.
     * Prints an error message if there's an exception during image loading.
     */
    private void initIconImage() {
        try {
            titleIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage("assets/animated_icon.gif"));
            frameIcon = ImageIO.read(new File("assets/frame_icon.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
