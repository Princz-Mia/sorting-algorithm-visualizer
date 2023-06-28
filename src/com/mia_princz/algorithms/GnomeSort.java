package com.mia_princz.algorithms;

import com.mia_princz.graphics.SettingsPanel;
import com.mia_princz.graphics.VisualizerPanel;

import javax.swing.*;

/**
 * A class representing the Gnome Sort algorithm.
 */
public class GnomeSort extends AbstractSorting {
    /**
     * Sorts the elements in the visualizer panel using the Gnome Sort algorithm.
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
                int index = 0;

                while (index < n) {
                    VisualizerPanel.getTraversingIndexes().set(0, index);

                    if (index == 0) {
                        index++;

                        VisualizerPanel.getCurrentIndexes().set(0, index);
                        visualizerPanel.repaint();
                    }
                    if (array.get(index) >= array.get(index - 1)) {
                        index++;

                        VisualizerPanel.getCurrentIndexes().set(0, index);
                        visualizerPanel.repaint();
                    } else {
                        VisualizerPanel.swap(array, index, index - 1);
                        index--;

                        Thread.sleep(delayTime);
                        visualizerPanel.repaint();
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