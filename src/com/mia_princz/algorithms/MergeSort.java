package com.mia_princz.algorithms;

import com.mia_princz.graphics.SettingsPanel;
import com.mia_princz.graphics.VisualizerPanel;

import javax.swing.*;

/**
 * A class representing the Merge Sort algorithm.
 */
public class MergeSort extends AbstractSorting {
    /**
     * Sorts the elements in the visualizer panel using the Merge Sort algorithm.
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
                sortAlgorithm(visualizerPanel, 0, array.size() - 1);

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
     * Recursive method that implements the merge sort algorithm.
     *
     * @param visualizerPanel the visualizer panel containing the elements to be sorted
     * @param leftIndex       the left index of the sub-array to be sorted
     * @param rightIndex      the right index of the sub-array to be sorted
     * @throws InterruptedException if the thread is interrupted during sleep
     */
    private static void sortAlgorithm(VisualizerPanel visualizerPanel, int leftIndex, int rightIndex) throws InterruptedException {
        if (leftIndex < rightIndex) {
            VisualizerPanel.getCurrentIndexes().set(0, rightIndex);

            int middleIndex = leftIndex + ((rightIndex - leftIndex) / 2);

            sortAlgorithm(visualizerPanel, leftIndex, middleIndex);

            Thread.sleep(delayTime);
            visualizerPanel.repaint();
            Thread.sleep(delayTime);

            sortAlgorithm(visualizerPanel, middleIndex + 1, rightIndex);

            Thread.sleep(delayTime);
            visualizerPanel.repaint();
            Thread.sleep(delayTime);

            merge(visualizerPanel, leftIndex, middleIndex, rightIndex);

            Thread.sleep(delayTime);
            visualizerPanel.repaint();
            Thread.sleep(delayTime);
        }
    }

    /**
     * Merges two sub-arrays in the main array.
     *
     * @param visualizerPanel the visualizer panel containing the elements to be sorted
     * @param leftIndex       the left index of the first sub-array
     * @param middleIndex     the right index of the first sub-array and left index of the second sub-array
     * @param rightIndex      the right index of the second sub-array
     * @throws InterruptedException if the thread is interrupted during sleep
     */
    private static void merge(VisualizerPanel visualizerPanel, int leftIndex, int middleIndex, int rightIndex) throws InterruptedException {
        int n1 = middleIndex - leftIndex + 1;
        int n2 = rightIndex - middleIndex;

        float[] leftArray = new float[n1];
        float[] rightArray = new float[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array.get(leftIndex + i);
        }

        for (int i = 0; i < n2; i++) {
            rightArray[i] = array.get(middleIndex + 1 + i);
        }

        int i = 0;
        int j = 0;
        int k = leftIndex;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array.set(k, leftArray[i]);
                i++;

                VisualizerPanel.getTraversingIndexes().set(0, k);
                Thread.sleep(delayTime);
                visualizerPanel.repaint();
            } else {
                array.set(k, rightArray[j]);
                j++;

                VisualizerPanel.getTraversingIndexes().set(0, k);
                Thread.sleep(delayTime);
                visualizerPanel.repaint();
            }
            k++;
        }

        while (i < n1) {
            array.set(k, leftArray[i]);
            i++;
            k++;

            VisualizerPanel.getTraversingIndexes().set(0, k);
            Thread.sleep(delayTime);
            visualizerPanel.repaint();
        }

        while (j < n2) {
            array.set(k, rightArray[j]);
            j++;
            k++;

            VisualizerPanel.getTraversingIndexes().set(0, k);
            Thread.sleep(delayTime);
            visualizerPanel.repaint();
        }
    }
}