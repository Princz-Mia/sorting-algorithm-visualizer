package com.mia_princz.algorithms;

import com.mia_princz.graphics.SettingsPanel;
import com.mia_princz.graphics.VisualizerPanel;

import javax.swing.*;

/**
 * A class representing the Quick Sort algorithm.
 */
public class QuickSort extends AbstractSorting {
    /**
     * Sorts the elements in the visualizer panel using the Quick Sort algorithm.
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
                sortAlgorithm(visualizerPanel, 0, array.size() - 1);

                VisualizerPanel.getTraversingIndexes().set(0, 0);
                VisualizerPanel.getTraversingIndexes().remove(1);
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
     * Recursive function to perform the Quick Sort algorithm.
     *
     * @param visualizerPanel the visualizer panel containing the elements to be sorted
     * @param low             the starting index of the subarray to be sorted
     * @param high            the ending index of the subarray to be sorted
     * @throws InterruptedException if the sorting process is interrupted
     */
    private static void sortAlgorithm(VisualizerPanel visualizerPanel, int low, int high) throws InterruptedException {
        if (low < high) {
            int pi = partition(visualizerPanel, low, high);

            sortAlgorithm(visualizerPanel, low, pi - 1);
            sortAlgorithm(visualizerPanel, pi + 1, high);
        }
    }

    /**
     * Partitions the array and selects a pivot element for the Quick Sort algorithm.
     *
     * @param visualizerPanel the visualizer panel containing the elements to be sorted
     * @param low             the starting index of the subarray to be partitioned
     * @param high            the ending index of the subarray to be partitioned
     * @return the index of the pivot element
     * @throws InterruptedException if the sorting process is interrupted
     */
    private static int partition(VisualizerPanel visualizerPanel, int low, int high) throws InterruptedException {
        float pivot = array.get(high);

        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (array.get(j) < pivot) {
                i++;
                VisualizerPanel.swap(array, i, j);

                VisualizerPanel.getTraversingIndexes().set(0, j);
                VisualizerPanel.getTraversingIndexes().set(1, i);
                VisualizerPanel.getCurrentIndexes().set(0, low - 1);

                Thread.sleep(25);
                visualizerPanel.repaint();
            }
        }
        VisualizerPanel.swap(array, i + 1, high);

        VisualizerPanel.getTraversingIndexes().set(0, i + 1);
        Thread.sleep(delayTime);
        visualizerPanel.repaint();

        return (i + 1);
    }
}
