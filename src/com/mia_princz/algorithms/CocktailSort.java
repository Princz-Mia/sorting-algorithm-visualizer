package com.mia_princz.algorithms;

import com.mia_princz.graphics.SettingsPanel;
import com.mia_princz.graphics.VisualizerPanel;

import javax.swing.*;

/**
 * A class representing the Cocktail Sort algorithm.
 */
public class CocktailSort extends AbstractSorting {
    /**
     * Sorts the elements in the visualizer panel using the Cocktail Sort algorithm.
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
        VisualizerPanel.getCurrentIndexes().add(-1);

        array = VisualizerPanel.getColumnHeight();
        sorter = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                boolean swapped = true;
                int start = 0;
                int end = array.size() - 1;

                while (swapped) {
                    swapped = false;

                    for (int i = start; i < end; i++) {
                        if (array.get(i) > array.get(i + 1)) {
                            VisualizerPanel.getTraversingIndexes().set(0, i);
                            VisualizerPanel.getCurrentIndexes().set(0, i + 1);

                            VisualizerPanel.swap(array, i, i + 1);
                            swapped = true;

                            Thread.sleep(delayTime);
                            visualizerPanel.repaint();
                        }
                    }

                    if (!swapped) {
                        break;
                    }

                    swapped = false;

                    end--;

                    for (int i = end - 1; i >= start; i--) {
                        if (array.get(i) > array.get(i + 1)) {
                            VisualizerPanel.getTraversingIndexes().set(0, i + 1);
                            VisualizerPanel.getCurrentIndexes().set(1, i);

                            VisualizerPanel.swap(array, i, i + 1);
                            swapped = true;

                            Thread.sleep(delayTime);
                            visualizerPanel.repaint();
                        }
                    }

                    start++;
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