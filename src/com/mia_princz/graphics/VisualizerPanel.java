package com.mia_princz.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * The VisualizerPanel class represents the panel for visualizing sorting algorithms.
 * It extends the JPanel class and contains methods for initializing the panel, shuffling elements,
 * swapping elements, and painting the visual representation of the sorting process.
 */
public class VisualizerPanel extends JPanel {

    // Constants for panel dimensions and settings
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 800;
    private static int baseColumnNumber = 512;
    private static float columnWidth = (float) WIDTH / baseColumnNumber;
    private static final ArrayList<Float> columnHeight = new ArrayList<>();
    private static boolean shuffleDone = true;
    private static boolean sortingDone = true;
    private static ArrayList<Integer> currentIndexes;
    private static ArrayList<Integer> traversingIndexes;

    /**
     * Constructs a new VisualizerPanel object.
     * Initializes the panel's bounds, background color, and column heights.
     */
    public VisualizerPanel() {
        currentIndexes = new ArrayList<>();
        traversingIndexes = new ArrayList<>();

        setBounds(0,100 + 78, WIDTH, HEIGHT);
        setBackground(Color.BLACK);

        initColumnHeight();
    }

    /**
     * Initializes the heights of the columns based on the panel's dimensions and column number.
     */
    public void initColumnHeight() {
        float interval = (float) HEIGHT / baseColumnNumber;
        int j = 1;
        for (int i = 0; i < baseColumnNumber; i++) {
            columnHeight.add(i, j * interval);
            j++;
        }
        columnWidth = (float) WIDTH / baseColumnNumber;
        repaint();
    }

    /**
     * Initializes the shuffler SwingWorker to perform the shuffling animation.
     * Disables relevant UI components during shuffling.
     */
    public void initShuffler() {
        shuffleDone = false;
        SettingsPanel.getColumnNumberSlider().setEnabled(false);
        SettingsPanel.getDelayTimeSlider().setEnabled(false);

        SwingWorker<Void, Void> shuffler = new SwingWorker<>() {
            final Random rnd = new Random();

            @Override
            protected Void doInBackground() throws InterruptedException {
                int middle = baseColumnNumber / 2;
                for (int i = 0, j = middle; i < middle; i++, j++) {
                    int randomIndex = rnd.nextInt(baseColumnNumber);
                    swap(columnHeight, i, randomIndex);

                    randomIndex = rnd.nextInt(baseColumnNumber);
                    swap(columnHeight, j, randomIndex);

                    Thread.sleep(8);
                    repaint();
                }
                return null;
            }

            @Override
            public void done() {
                super.done();
                shuffleDone = true;
                SettingsPanel.getColumnNumberSlider().setEnabled(true);
                SettingsPanel.getDelayTimeSlider().setEnabled(true);
            }
        };

        shuffler.execute();
    }

    /**
     * Swaps two elements in the given array.
     *
     * @param array the array of elements
     * @param i     the index of the first element
     * @param j     the index of the second element
     */
    public static void swap(ArrayList<Float> array, int i, int j) {
        float temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    /**
     * Returns the shuffle completion status.
     *
     * @return true if shuffling is done, false otherwise
     */
    public static boolean getIsShufflerDone() {
        return shuffleDone;
    }

    /**
     * Returns the sorting completion status.
     *
     * @return true if sorting is done, false otherwise
     */
    public static boolean isSortingDone() {
        return sortingDone;
    }

    /**
     * Sets the sorting completion status.
     *
     * @param sortingDone the sorting completion status
     */
    public static void setSortingDone(boolean sortingDone) {
        VisualizerPanel.sortingDone = sortingDone;
    }

    /**
     * Returns the heights of the columns.
     *
     * @return the list of column heights
     */
    public static ArrayList<Float> getColumnHeight() {
        return columnHeight;
    }

    /**
     * Returns the number of columns.
     *
     * @return the column number
     */
    public static int getBaseColumnNumber() {
        return baseColumnNumber;
    }

    /**
     * Sets the number of columns.
     *
     * @param baseColumnNumber the number of columns
     */
    public static void setBaseColumnNumber(int baseColumnNumber) {
        VisualizerPanel.baseColumnNumber = baseColumnNumber;
    }

    /**
     * Returns the list of currently traversed indexes during sorting.
     *
     * @return the list of traversed indexes
     */
    public static ArrayList<Integer> getCurrentIndexes() {
        return currentIndexes;
    }

    /**
     * Returns the list of indexes currently being traversed during sorting.
     *
     * @return the list of traversing indexes
     */
    public static ArrayList<Integer> getTraversingIndexes() {
        return traversingIndexes;
    }

    /**
     * Overrides the paintComponent method to paint the visual representation of the sorting process.
     *
     * @param graphics the Graphics object to paint on
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.WHITE);

        Rectangle2D column;
        float columnSize = columnHeight.size();

        int i = 0;
        while (i < baseColumnNumber) {
            //column = new Rectangle2D.Float(i * columnWidth, HEIGHT - columnHeight.get(i), columnWidth - SettingsPanel.getColumnNumberSlider().getValue() / baseColumnNumber, columnHeight.get(i));
            column = new Rectangle2D.Float(i * columnWidth, HEIGHT - columnHeight.get(i), columnWidth - columnSize / baseColumnNumber, columnHeight.get(i));
            graphics2D.fill(column);
            i++;
        }

        for (Integer currentIndex : currentIndexes) {
            if (currentIndex != null && currentIndex > -1 && currentIndex < baseColumnNumber) {
                graphics2D.setColor(Color.GREEN);
                //column = new Rectangle2D.Float(currentIndex * columnWidth, HEIGHT - columnHeight.get(currentIndex), columnWidth - SettingsPanel.getColumnNumberSlider().getValue() / baseColumnNumber, columnHeight.get(currentIndex));
                column = new Rectangle2D.Float(currentIndex * columnWidth, HEIGHT - columnHeight.get(currentIndex), columnWidth - columnSize / baseColumnNumber, columnHeight.get(currentIndex));
                graphics2D.fill(column);
            }
        }

        for (Integer traversingIndex : traversingIndexes) {
            if (traversingIndex != null && traversingIndex > -1 && traversingIndex < baseColumnNumber) {
                graphics2D.setColor(Color.RED);
                //column = new Rectangle2D.Float(traversingIndex * columnWidth, HEIGHT - columnHeight.get(traversingIndex), columnWidth - SettingsPanel.getColumnNumberSlider().getValue() / baseColumnNumber, columnHeight.get(traversingIndex));
                column = new Rectangle2D.Float(traversingIndex * columnWidth, HEIGHT - columnHeight.get(traversingIndex), columnWidth - columnSize / baseColumnNumber, columnHeight.get(traversingIndex));
                graphics2D.fill(column);
            }
        }

        graphics.dispose();
    }
}
