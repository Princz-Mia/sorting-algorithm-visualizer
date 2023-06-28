package com.mia_princz.algorithms;

import com.mia_princz.graphics.SettingsPanel;
import com.mia_princz.graphics.VisualizerPanel;

import javax.swing.*;

/**
 * A class representing the Heap Sort algorithm.
 */
public class HeapSort extends AbstractSorting {
    /**
     * Sorts the elements in the visualizer panel using the Heap Sort algorithm.
     * This method initiates the sorting process and handles the necessary steps
     * for visualization and synchronization.
     *
     * @param visualizerPanel the VisualizerPanel object that displays the sorting process
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
                int sizeOfHeap = array.size();

                for (int i = sizeOfHeap / 2 - 1; i >= 0; i--) {
                    heapify(visualizerPanel, sizeOfHeap, i);
                }

                for (int i = sizeOfHeap - 1; i > 0; i--) {
                    VisualizerPanel.getCurrentIndexes().set(0, i);
                    VisualizerPanel.swap(array, 0, i);

                    Thread.sleep(delayTime);
                    visualizerPanel.repaint();

                    heapify(visualizerPanel, i, 0);
                }

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
     * Performs the heapify operation on a subtree rooted at the specified index.
     *
     * @param visualizerPanel the visualizer panel for updating the traversal and swapping indexes
     * @param sizeOfHeap      the size of the heap
     * @param i               the root index of the subtree
     * @throws InterruptedException if the execution is interrupted while sleeping
     */
    private static void heapify(VisualizerPanel visualizerPanel, int sizeOfHeap, int i) throws InterruptedException {
        int largestIndex = i;
        int leftIndex = 2 * i + 1;
        int rightIndex = 2 * i + 2;

        if (leftIndex < sizeOfHeap && array.get(leftIndex) > array.get(largestIndex)) {
            largestIndex = leftIndex;
        }

        if (rightIndex < sizeOfHeap && array.get(rightIndex) > array.get(largestIndex)) {
            largestIndex = rightIndex;
        }

        if (largestIndex != i) {
            VisualizerPanel.getTraversingIndexes().set(0, i);
            VisualizerPanel.swap(array, i, largestIndex);

            Thread.sleep(delayTime);
            visualizerPanel.repaint();

            heapify(visualizerPanel, sizeOfHeap, largestIndex);
        }
    }
}
