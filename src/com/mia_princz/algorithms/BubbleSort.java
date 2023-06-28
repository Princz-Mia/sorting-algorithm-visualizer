package com.mia_princz.algorithms;

import com.mia_princz.graphics.SettingsPanel;
import com.mia_princz.graphics.VisualizerPanel;

import javax.swing.*;

/**
 * A class representing the Bubble Sort algorithm.
 */
public class BubbleSort extends AbstractSorting {
    /**
     * Sorts the elements in the visualizer panel using the Bubble Sort algorithm.
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
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        VisualizerPanel.getTraversingIndexes().set(0, j);
                        VisualizerPanel.getCurrentIndexes().set(0, n - i);

                        if (array.get(j) > array.get(j + 1)) {
                            VisualizerPanel.swap(array, j + 1, j);

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