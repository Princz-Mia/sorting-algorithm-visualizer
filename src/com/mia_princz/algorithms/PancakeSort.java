package com.mia_princz.algorithms;

import com.mia_princz.graphics.SettingsPanel;
import com.mia_princz.graphics.VisualizerPanel;

import javax.swing.*;

/**
 * A class representing the Pancake Sort algorithm.
 */
public class PancakeSort extends AbstractSorting {
    /**
     * Sorts the elements in the visualizer panel using the Pancake Sort algorithm.
     * This method initiates the sorting process and handles the necessary steps
     * for visualization and synchronization.
     *
     * @param visualizerPanel the VisualizerPanel object that displays the sorting process
     */
    public static void sort(VisualizerPanel visualizerPanel) {
        SettingsPanel.getColumnNumberSlider().setEnabled(false);
        SettingsPanel.getDelayTimeSlider().setEnabled(false);

        VisualizerPanel.getTraversingIndexes().add(-1);
        VisualizerPanel.getTraversingIndexes().add(-1);
        VisualizerPanel.getCurrentIndexes().add(-1);

        array = VisualizerPanel.getColumnHeight();
        sorter = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                int n = array.size();
                for (int currentSize = n; currentSize > 1; currentSize--) {
                    VisualizerPanel.getCurrentIndexes().set(0, currentSize);

                    int maxIndex = findMax(currentSize);
                    if (maxIndex != currentSize - 1) {
                        flip(visualizerPanel, maxIndex);
                        flip(visualizerPanel, currentSize - 1);
                    }
                }

                VisualizerPanel.getTraversingIndexes().set(0, 0);
                VisualizerPanel.getTraversingIndexes().set(1, -1);
                VisualizerPanel.getCurrentIndexes().clear();
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
     * Reverses the elements in the array from index 0 to the specified index (inclusive).
     * This method is used in the Pancake Sort algorithm.
     *
     * @param visualizerPanel the visualizer panel used for animation
     * @param i the index up to which the array elements should be reversed
     * @throws InterruptedException if the thread sleep is interrupted
     */
    private static void flip(VisualizerPanel visualizerPanel, int i) throws InterruptedException {
        int start = 0;
        while (start < i) {
            VisualizerPanel.swap(array, start, i);
            start++;
            i--;

            VisualizerPanel.getTraversingIndexes().set(0, start);
            VisualizerPanel.getTraversingIndexes().set(1, i);
            Thread.sleep(delayTime);
            visualizerPanel.repaint();
        }
    }

    /**
     * Finds the index of the maximum element in the array up to the specified index.
     * This method is used in the Pancake Sort algorithm.
     *
     * @param n the index up to which to search for the maximum element
     * @return the index of the maximum element
     */
    private static int findMax(int n) {
        int maxIndex, i;
        for (maxIndex = 0, i = 0; i < n; i++) {
            if (array.get(i) > array.get(maxIndex)) {
                maxIndex = i;
            }
        }

        return maxIndex;
    }
}
