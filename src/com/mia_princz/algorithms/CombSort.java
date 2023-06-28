package com.mia_princz.algorithms;

import com.mia_princz.graphics.SettingsPanel;
import com.mia_princz.graphics.VisualizerPanel;

import javax.swing.*;

/**
 * A class representing the Comb Sort algorithm.
 */
public class CombSort extends AbstractSorting {
    /**
     * Sorts the elements in the visualizer panel using the Comb Sort algorithm.
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

        array = VisualizerPanel.getColumnHeight();
        sorter = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                int n = array.size();
                int gap = n;

                boolean swapped = true;

                while (gap != 1 || swapped) {
                    gap = getNextGap(gap);
                    swapped = false;

                    for (int i = 0; i < n - gap; i++) {
                        VisualizerPanel.getTraversingIndexes().set(0, i);
                        VisualizerPanel.getTraversingIndexes().set(1, i + gap);
                        if (array.get(i) > array.get(i + gap)) {
                            VisualizerPanel.swap(array, i, i + gap);
                            swapped = true;

                            Thread.sleep(delayTime);
                            visualizerPanel.repaint();
                        }
                    }
                }

                VisualizerPanel.getTraversingIndexes().set(0, 0);
                VisualizerPanel.getTraversingIndexes().set(1, -1);

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
     * Calculates the next gap value for the Comb Sort algorithm.
     *
     * @param gap the current gap value.
     * @return the next gap value.
     */
    private static int getNextGap(int gap) {
        gap = (gap * 10) / 13;
        return Math.max(gap, 1);
    }
}