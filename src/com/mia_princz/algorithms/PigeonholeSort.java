package com.mia_princz.algorithms;

import com.mia_princz.graphics.SettingsPanel;
import com.mia_princz.graphics.VisualizerPanel;

import javax.swing.*;
import java.util.Arrays;

/**
 * A class representing the Pigeonhole Sort algorithm.
 */
public class PigeonholeSort extends AbstractSorting {
    /**
     * Sorts the elements in the visualizer panel using the Pigeonhole Sort algorithm.
     * This method initiates the sorting process and handles the necessary steps
     * for visualization and synchronization.
     *
     * @param visualizerPanel the VisualizerPanel object that displays the sorting process
     */
    public static void sort(VisualizerPanel visualizerPanel) {
        SettingsPanel.getColumnNumberSlider().setEnabled(false);
        SettingsPanel.getDelayTimeSlider().setEnabled(false);

        VisualizerPanel.getTraversingIndexes().add(-1);

        array = VisualizerPanel.getColumnHeight();
        sorter = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                int n = array.size();
                float min = array.get(0);
                float max = array.get(0);

                // Find the minimum and maximum values in the array
                for (int k = 0; k < n; k++) {
                    if (array.get(k) > max) {
                        max = array.get(k);
                    }
                    if (array.get(k) < min) {
                        min = array.get(k);
                    }
                }

                // Calculate the range of values and create pigeonhole array
                float range = max - min + 1;
                range = (float) Math.floor(range);
                int[] pigeonHole = new int[(int) range];
                Arrays.fill(pigeonHole, 0);

                // Place elements into pigeonholes
                for (int i = 0; i < n; i++) {
                    pigeonHole[(int) (array.get(i) - min)]++;
                }

                int index = 0;

                // Reconstruct the array by placing elements back from pigeonholes
                for (int j = 0; j < range; j++) {
                    while (pigeonHole[j] > 0) {
                        pigeonHole[j]--;
                        array.set(index, j + min);
                        index++;

                        VisualizerPanel.getTraversingIndexes().set(0, index);

                        Thread.sleep(delayTime);
                        visualizerPanel.repaint();
                    }
                }

                VisualizerPanel.getTraversingIndexes().set(0, 0);

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
