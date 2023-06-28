package com.mia_princz.algorithms;

import com.mia_princz.graphics.SettingsPanel;
import com.mia_princz.graphics.VisualizerPanel;

import javax.swing.*;
import java.util.ArrayList;

/**
 * A class representing the Three-Way Merge Sort algorithm.
 */
public class ThreeWayMergeSort extends AbstractSorting {
    /**
     * Sorts the elements in the visualizer panel using the Three-Way Merge Sort algorithm.
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
            protected Void doInBackground() throws Exception {
                int n = array.size();
                ArrayList<Float> fArray = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    fArray.add(i, array.get(i));
                }

                sortAlgorithm(visualizerPanel, fArray, 0, n, array);

                for (int i = 0; i < n; i++) {
                    array.set(i, fArray.get(i));

                    Thread.sleep(delayTime);
                    visualizerPanel.repaint();
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
     * Implements the Three-Way Merge Sort algorithm recursively to sort a portion of the array.
     * This method performs the recursive calls and merging of sub-arrays necessary for sorting.
     *
     * @param visualizerPanel the visualizer panel for updating the visualization
     * @param gArray          the original array containing the elements to be sorted
     * @param low             the starting index of the portion to be sorted
     * @param high            the ending index (exclusive) of the portion to be sorted
     * @param destArray       the destination array to store the sorted elements
     * @throws InterruptedException if the execution is interrupted during visualization
     */
    private static void sortAlgorithm(VisualizerPanel visualizerPanel, ArrayList<Float> gArray, int low, int high, ArrayList<Float> destArray) throws InterruptedException {
        if (high - low < 2) return;

        int mid1 = low + ((high - low) / 3);
        int mid2 = low + 2 * ((high - low) / 3) + 1;

        sortAlgorithm(visualizerPanel, destArray, low, mid1, gArray);

        Thread.sleep(delayTime);
        visualizerPanel.repaint();

        sortAlgorithm(visualizerPanel, destArray, mid1, mid2, gArray);
        Thread.sleep(delayTime);
        visualizerPanel.repaint();

        sortAlgorithm(visualizerPanel, destArray, mid2, high, gArray);
        Thread.sleep(delayTime);
        visualizerPanel.repaint();

        merge(visualizerPanel, destArray, low, mid1, mid2, high, gArray);
        Thread.sleep(delayTime);
        visualizerPanel.repaint();
    }

    /**
     * Merges three sub-arrays within the destination array into a single sorted sub-array.
     * This method performs the merging process and updates the visualization accordingly.
     *
     * @param visualizerPanel the visualizer panel for updating the visualization
     * @param gArray          the original array containing the elements to be sorted
     * @param low             the starting index of the first sub-array
     * @param mid1            the ending index (exclusive) of the first sub-array
     * @param mid2            the ending index (exclusive) of the second sub-array
     * @param high            the ending index (exclusive) of the third sub-array
     * @param destArray       the destination array to store the sorted elements
     * @throws InterruptedException if the execution is interrupted during visualization
     */
    private static void merge(VisualizerPanel visualizerPanel, ArrayList<Float> gArray, int low, int mid1, int mid2, int high, ArrayList<Float> destArray) throws InterruptedException {
        int i = low;
        int j = mid1;
        int k = mid2;
        int l = low;

        while ((i < mid1) && (j < mid2) && (k < high)) {
            if ((gArray.get(i) - gArray.get(j)) < 0) {
                if ((gArray.get(i) - gArray.get(k)) < 0) {
                    destArray.set(l, gArray.get(i));
                    l++;
                    i++;

                    VisualizerPanel.getTraversingIndexes().set(0, i);
                } else {
                    destArray.set(l, gArray.get(k));
                    l++;
                    k++;

                    VisualizerPanel.getTraversingIndexes().set(0, k);
                }
            } else {
                if ((gArray.get(j) - gArray.get(k)) < 0) {
                    destArray.set(l, gArray.get(j));
                    l++;
                    j++;

                    VisualizerPanel.getTraversingIndexes().set(0, j);
                } else {
                    destArray.set(l, gArray.get(k));
                    l++;
                    k++;

                    VisualizerPanel.getTraversingIndexes().set(0, k);
                }
            }
            VisualizerPanel.getCurrentIndexes().set(0, low);
            Thread.sleep(delayTime);
            visualizerPanel.repaint();
        }

        while ((i < mid1) && (j < mid2)) {
            if ((gArray.get(i) - gArray.get(j)) < 0) {
                destArray.set(l, gArray.get(i));
                l++;
                i++;

                VisualizerPanel.getTraversingIndexes().set(0, i);
            } else {
                destArray.set(l, gArray.get(j));
                l++;
                j++;

                VisualizerPanel.getTraversingIndexes().set(0, j);
            }
            VisualizerPanel.getCurrentIndexes().set(0, low);
            Thread.sleep(delayTime);
            visualizerPanel.repaint();
        }

        while ((j < mid2) && (k < high)) {
            if ((gArray.get(j) - gArray.get(k)) < 0) {
                destArray.set(l, gArray.get(j));
                l++;
                j++;

                VisualizerPanel.getTraversingIndexes().set(0, j);
            } else {
                destArray.set(l, gArray.get(k));
                l++;
                k++;

                VisualizerPanel.getTraversingIndexes().set(0, k);
            }
            VisualizerPanel.getCurrentIndexes().set(0, low);
            Thread.sleep(delayTime);
            visualizerPanel.repaint();
        }

        while ((i < mid1) && (k < high)) {
            if ((gArray.get(i) - gArray.get(k)) < 0) {
                destArray.set(l, gArray.get(i));
                l++;
                i++;

                VisualizerPanel.getTraversingIndexes().set(0, i);
            } else {
                destArray.set(l, gArray.get(k));
                l++;
                k++;

                VisualizerPanel.getTraversingIndexes().set(0, k);
            }
            VisualizerPanel.getCurrentIndexes().set(0, low);
            Thread.sleep(delayTime);
            visualizerPanel.repaint();
        }

        while (i < mid1) {
            destArray.set(l, gArray.get(i));
            l++;
            i++;

            VisualizerPanel.getTraversingIndexes().set(0, i);
            VisualizerPanel.getCurrentIndexes().set(0, low);
            Thread.sleep(delayTime);
            visualizerPanel.repaint();
        }

        while (j < mid2) {
            destArray.set(l, gArray.get(j));
            l++;
            j++;

            VisualizerPanel.getTraversingIndexes().set(0, j);
            VisualizerPanel.getCurrentIndexes().set(0, low);
            Thread.sleep(delayTime);
            visualizerPanel.repaint();
        }

        while (k < high) {
            destArray.set(l, gArray.get(k));
            l++;
            k++;

            VisualizerPanel.getTraversingIndexes().set(0, k);
            VisualizerPanel.getCurrentIndexes().set(0, low);
            Thread.sleep(delayTime);
            visualizerPanel.repaint();
        }
    }
}
