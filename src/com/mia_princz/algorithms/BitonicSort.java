package com.mia_princz.algorithms;

import com.mia_princz.graphics.SettingsPanel;
import com.mia_princz.graphics.VisualizerPanel;

import javax.swing.*;

/**
 * A class representing the Bitonic Sort algorithm.
 */
public class BitonicSort extends AbstractSorting {
    /**
     * Sorts the elements in the visualizer panel using the Bitonic Sort algorithm.
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
        VisualizerPanel.getCurrentIndexes().add(-1);

        array = VisualizerPanel.getColumnHeight();
        sorter = new SwingWorker<>() {
            /**
             * Runs the sorting process in the background.
             *
             * @return The result of the background operation.
             * @throws InterruptedException if the thread is interrupted during the sorting process.
             */
            @Override
            protected Void doInBackground() throws InterruptedException {
                int n = array.size();
                sortAlgorithm(visualizerPanel, 0, n, 1);

                Thread.sleep(delayTime);
                visualizerPanel.repaint();

                VisualizerPanel.getTraversingIndexes().set(0, 0);
                VisualizerPanel.getTraversingIndexes().remove(1);
                VisualizerPanel.getCurrentIndexes().clear();

                finish(visualizerPanel);

                return null;
            }

            /**
             * Performs operations after the sorting process is complete.
             */
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
     * Swaps elements in the array based on the given direction.
     *
     * @param visualizerPanel The visualizer panel for displaying the sorting process.
     * @param i               The index of the first element to swap.
     * @param j               The index of the second element to swap.
     * @param dir             The direction of the swap (0 or 1).
     * @throws InterruptedException if the thread is interrupted during the swap.
     */
    private static void directionSwap(VisualizerPanel visualizerPanel, int i, int j, int dir) throws InterruptedException {
        if ((array.get(i) > array.get(j) && dir == 1) || (array.get(i) < array.get(j) && dir == 0)) {
            VisualizerPanel.getTraversingIndexes().set(0, i);
            VisualizerPanel.getTraversingIndexes().set(1, j);
            VisualizerPanel.swap(array, i, j);

            Thread.sleep(delayTime);
            visualizerPanel.repaint();
        }
    }

    /**
     * Performs the Bitonic Sort algorithm recursively.
     *
     * @param visualizerPanel The visualizer panel for displaying the sorting process.
     * @param low             The starting index of the range to sort.
     * @param n               The size of the range to sort.
     * @param dir             The direction of the sort (0 or 1).
     * @throws InterruptedException if the thread is interrupted during the sorting process.
     */
    private static void sortAlgorithm(VisualizerPanel visualizerPanel, int low, int n, int dir) throws InterruptedException {
        if (n > 1) {
            int m = n / 2;

            sortAlgorithm(visualizerPanel, low, m, 1);

            sortAlgorithm(visualizerPanel, low + m, m, 0);

            merge(visualizerPanel, low, n, dir);
        }
    }

    /**
     * Merges two sub-arrays in the specified direction.
     *
     * @param visualizerPanel The visualizer panel for displaying the sorting process.
     * @param low             The starting index of the range to merge.
     * @param n               The size of the range to merge.
     * @param dir             The direction of the merge (0 or 1).
     * @throws InterruptedException if the thread is interrupted during the merging process.
     */
    private static void merge(VisualizerPanel visualizerPanel, int low, int n, int dir) throws InterruptedException {
        if (n > 1) {
            int m = n / 2;

            int j = 0;
            for (int i = low; i < low + m; i++) {
                j++;

                directionSwap(visualizerPanel, i, i + m, dir);

                VisualizerPanel.getTraversingIndexes().add(j, i);

                Thread.sleep(delayTime);
                visualizerPanel.repaint();
            }
            VisualizerPanel.getTraversingIndexes().clear();
            VisualizerPanel.getTraversingIndexes().add(0, -1);
            VisualizerPanel.getTraversingIndexes().add(1, -1);

            merge(visualizerPanel, low, m, dir);

            merge(visualizerPanel, low + m, m, dir);
        }
    }
}