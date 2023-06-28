package com.mia_princz.graphics;

import com.javaswingdev.GlassPanePopup;

/**
 * The main class that starts the visualization application.
 */
public class Main {

    /**
     * The main method that initializes and displays the main window of the visualization application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        // Create the main window
        VisualizerWindow mainWindow = new VisualizerWindow();

        // Install the glass pane popup
        GlassPanePopup.install(mainWindow);

        // Set the main window visible
        mainWindow.setVisible(true);
    }
}