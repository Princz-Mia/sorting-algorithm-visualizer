package com.mia_princz.algorithms;

import com.mia_princz.graphics.SettingsPanel;
import com.mia_princz.graphics.VisualizerPanel;

import javax.swing.*;

/**
 * A class representing the Odd-Even Sort algorithm.
 */
public class OddEvenSort extends AbstractSorting {
    /**
     * Sorts the elements in the visualizer panel using the Odd-Even  Sort algorithm.
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
                int n = array.size();
                boolean isSorted = false;

                while (!isSorted) {
                    isSorted = true;

                    for (int i = 1; i <= n - 2; i += 2) {
                        VisualizerPanel.getTraversingIndexes().set(0, i);
                        if (array.get(i) > array.get(i + 1)) {
                            VisualizerPanel.swap(array, i, i + 1);
                            isSorted = false;

                            Thread.sleep(delayTime);
                            visualizerPanel.repaint();
                        }
                    }

                    for (int i = 0; i <= n - 2; i += 2) {
                        VisualizerPanel.getTraversingIndexes().set(0, i);
                        if (array.get(i) > array.get(i + 1)) {
                            VisualizerPanel.swap(array, i, i + 1);
                            isSorted = false;

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
}