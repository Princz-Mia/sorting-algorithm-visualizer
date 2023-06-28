package com.mia_princz.algorithms;

import com.mia_princz.graphics.SettingsPanel;
import com.mia_princz.graphics.VisualizerPanel;

import javax.swing.*;
import java.util.Random;

/**
 * A class representing the Bogo Sort algorithm.
 */
public class BogoSort extends AbstractSorting {
    /**
     * Sorts the elements in the visualizer panel using the Bogo Sort algorithm.
     * This method initiates the sorting process and handles the necessary steps
     * for visualization and synchronization.
     *
     * @param visualizerPanel the VisualizerPanel object that displays the sorting process
     */
    public static void sort(VisualizerPanel visualizerPanel) {
        SettingsPanel.getColumnNumberSlider().setEnabled(false);
        SettingsPanel.getDelayTimeSlider().setEnabled(false);

        VisualizerPanel.getTraversingIndexes().add(-1);

        array = VisualizerPanel.getColumnHeight();
        sorter = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                int n = array.size();
                while (!isSorted(n)) {
                    shuffle(visualizerPanel, n);
                }

                VisualizerPanel.getTraversingIndexes().set(0, 0);

                finish(visualizerPanel);

                return null;
            }

            @Override
            public void done() {
                VisualizerPanel.getTraversingIndexes().clear();
                VisualizerPanel.getCurrentIndexes().clear();
                visualizerPanel.repaint();

                SettingsPanel.getColumnNumberSlider().setEnabled(true);
                SettingsPanel.getDelayTimeSlider().setEnabled(true);
                VisualizerPanel.setSortingDone(true);
            }
        };

        sorter.execute();
    }

    /**
     * Shuffles the elements in the visualizer panel using the Bogo Sort algorithm.
     *
     * @param visualizerPanel the visualizer panel containing the elements to be shuffled.
     * @param n               the size of the array to be shuffled.
     * @throws InterruptedException if the thread is interrupted during the shuffling process.
     */
    private static void shuffle(VisualizerPanel visualizerPanel, int n) throws InterruptedException {
        for (int i = 1; i < n; i++) {
            int randomIndex = new Random().nextInt(0, n);

            VisualizerPanel.getTraversingIndexes().set(0, i);

            VisualizerPanel.swap(array, i, randomIndex);

            Thread.sleep(delayTime);
            visualizerPanel.repaint();
        }
    }

    /**
     * Checks if the elements in the array are sorted in non-decreasing order.
     *
     * @param n the size of the array to be checked.
     * @return {@code true} if the array is sorted, {@code false} otherwise.
     */
    private static boolean isSorted(int n) {
        for (int i = 1; i < n; i++) {
            if (array.get(i) < array.get(i - 1)) {
                return false;
            }
        }

        return true;
    }
}