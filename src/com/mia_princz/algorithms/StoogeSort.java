package com.mia_princz.algorithms;

import com.mia_princz.graphics.SettingsPanel;
import com.mia_princz.graphics.VisualizerPanel;

import javax.swing.*;

/**
 * A class representing the Stooge Sort algorithm.
 */
public class StoogeSort extends AbstractSorting {
    /**
     * Sorts the elements in the visualizer panel using the Stooge Sort algorithm.
     * This method initiates the sorting process and handles the necessary steps
     * for visualization and synchronization.
     *
     * @param visualizerPanel the visualizer panel containing the elements to be sorted
     */
    public static void sort(VisualizerPanel visualizerPanel) {
        SettingsPanel.getColumnNumberSlider().setEnabled(false);
        SettingsPanel.getDelayTimeSlider().setEnabled(false);

        VisualizerPanel.getTraversingIndexes().add(-1);
        VisualizerPanel.getCurrentIndexes().add(-1);

        array = VisualizerPanel.getColumnHeight();
        sorter = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                int n = array.size();
                sortAlgorithm(visualizerPanel, 0, n - 1);

                VisualizerPanel.getTraversingIndexes().set(0, 0);
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
     * Implements the Stooge Sort algorithm recursively to sort a portion of the array.
     * This method performs the swapping and recursive calls necessary to sort the elements.
     *
     * @param visualizerPanel the visualizer panel for updating the visualization
     * @param low             the starting index of the portion to be sorted
     * @param high            the ending index of the portion to be sorted
     * @throws InterruptedException if the execution is interrupted during visualization
     */
    private static void sortAlgorithm(VisualizerPanel visualizerPanel, int low, int high) throws InterruptedException {
        if (low >= high) return;

        if (array.get(low) > array.get(high)) {
            VisualizerPanel.swap(array, low, high);

            VisualizerPanel.getTraversingIndexes().set(0, low);
            VisualizerPanel.getCurrentIndexes().set(0, high);

            Thread.sleep(delayTime);
            visualizerPanel.repaint();
        }

        if (high - low + 1 > 2) {
            int t = (high - low + 1) / 3;

            sortAlgorithm(visualizerPanel, low, high - t);

            sortAlgorithm(visualizerPanel, low + t, high);

            sortAlgorithm(visualizerPanel, low, high - t);
        }
    }
}
