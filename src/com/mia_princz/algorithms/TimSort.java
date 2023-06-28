package com.mia_princz.algorithms;

import com.mia_princz.graphics.SettingsPanel;
import com.mia_princz.graphics.VisualizerPanel;

import javax.swing.*;
import java.util.ArrayList;

/**
 * A class representing the Tim Sort algorithm.
 */
public class TimSort extends AbstractSorting {

    private static final int MIN_MERGE = 32;

    /**
     * Sorts the elements in the visualizer panel using the Tim Sort algorithm.
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
            protected Void doInBackground() throws Exception {
                int n = array.size();
                int minRun = minRunLength(MIN_MERGE);

                for (int i = 0; i < n; i += minRun) {
                    for (int j = i + 1; j <= Math.min((i + MIN_MERGE - 1), (n - 1)); j++) {
                        float temp = array.get(j);
                        int k = j - 1;
                        while (k >= i && array.get(k) > temp) {
                            array.set(k + 1, array.get(k));
                            k--;

                            VisualizerPanel.getCurrentIndexes().set(0, j);
                            Thread.sleep(delayTime);
                            visualizerPanel.repaint();
                        }
                        array.set(k + 1, temp);

                        Thread.sleep(delayTime);
                        visualizerPanel.repaint();
                    }
                }

                for (int size = minRun; size < n; size *= 2) {
                    for (int left = 0; left < n; left += 2 * size) {
                        int mid = left + size - 1;
                        int right = Math.min((left + 2 * size - 1), (n - 1));

                        if (mid < right) {
                            merge(visualizerPanel, left, mid, right);

                            VisualizerPanel.getTraversingIndexes().set(0, right);
                            Thread.sleep(delayTime);
                            visualizerPanel.repaint();
                        }
                    }
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
     * Calculates the minimum run length for the Tim sort algorithm.
     *
     * @param n the total length of the array
     * @return the minimum run length
     */
    private static int minRunLength(int n) {
        assert n >= 0;

        int r = 0;
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }

        return n + r;
    }

    /**
     * Merges two sub-arrays of the array.
     *
     * @param visualizerPanel the VisualizerPanel object that displays the sorting process
     * @param leftIndex       the starting index of the left sub-array
     * @param middleIndex     the ending index of the left sub-array and the starting index of the right sub-array
     * @param rightIndex      the ending index of the right sub-array
     * @throws InterruptedException if the thread is interrupted while sleeping
     */
    private static void merge(VisualizerPanel visualizerPanel, int leftIndex, int middleIndex, int rightIndex)
            throws InterruptedException {
        int n1 = middleIndex - leftIndex + 1;
        int n2 = rightIndex - middleIndex;

        ArrayList<Float> leftArray = new ArrayList<>();
        ArrayList<Float> rightArray = new ArrayList<>();

        for (int i = 0; i < n1; i++) {
            leftArray.add(array.get(leftIndex + i));
        }

        for (int i = 0; i < n2; i++) {
            rightArray.add(array.get(middleIndex + 1 + i));
        }

        int i = 0;
        int j = 0;
        int k = leftIndex;

        while (i < n1 && j < n2) {
            if (leftArray.get(i) <= rightArray.get(j)) {
                array.set(k, leftArray.get(i));
                i++;

                VisualizerPanel.getTraversingIndexes().set(0, i);
            } else {
                array.set(k, rightArray.get(j));
                j++;

                VisualizerPanel.getTraversingIndexes().set(0, j);
            }
            Thread.sleep(delayTime);
            visualizerPanel.repaint();
            k++;
        }

        while (i < n1) {
            array.set(k, leftArray.get(i));
            i++;
            k++;

            VisualizerPanel.getTraversingIndexes().set(0, i);
            Thread.sleep(delayTime);
            visualizerPanel.repaint();
        }

        while (j < n2) {
            array.set(k, rightArray.get(j));
            j++;
            k++;

            VisualizerPanel.getTraversingIndexes().set(0, j);
            Thread.sleep(delayTime);
            visualizerPanel.repaint();
        }
    }
}