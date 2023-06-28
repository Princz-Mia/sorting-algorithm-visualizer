package com.mia_princz.algorithms;

import com.mia_princz.graphics.SettingsPanel;
import com.mia_princz.graphics.VisualizerPanel;

import javax.swing.*;

/**
 * A class representing the Shell Sort algorithm.
 */
public class ShellSort extends AbstractSorting {
    /**
     * Sorts the elements in the visualizer panel using the Shell Sort algorithm.
     * This method initiates the sorting process and handles the necessary steps
     * for visualization and synchronization.
     *
     * @param visualizerPanel the VisualizerPanel object that displays the sorting process
     */
    public static void sort(VisualizerPanel visualizerPanel) {
        SettingsPanel.getColumnNumberSlider().setEnabled(false);
        SettingsPanel.getDelayTimeSlider().setEnabled(false);


        array = VisualizerPanel.getColumnHeight();
        sorter = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                int n = array.size();

                for (int gap = n / 2; gap > 0; gap /= 2) {
                    for (int i = gap; i < n; i++) {
                        float temp = array.get(i);
                        int j;
                        for (j = i; j >= gap && array.get(j - gap) > temp; j -= gap) {
                            array.set(j, array.get(j - gap));
                            VisualizerPanel.getTraversingIndexes().add(j);

                            Thread.sleep(delayTime);
                            visualizerPanel.repaint();
                        }
                        array.set(j, temp);
                        VisualizerPanel.getTraversingIndexes().add(j);

                        Thread.sleep(delayTime);
                        visualizerPanel.repaint();

                        VisualizerPanel.getTraversingIndexes().clear();
                    }
                }

                VisualizerPanel.getTraversingIndexes().clear();
                VisualizerPanel.getTraversingIndexes().add(0);
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
