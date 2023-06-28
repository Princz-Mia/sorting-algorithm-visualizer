package com.mia_princz.algorithms;

import com.mia_princz.graphics.VisualizerPanel;

import javax.swing.*;
import java.util.ArrayList;

/**
 * An abstract class representing a sorting algorithm.
 */
public abstract class AbstractSorting {

    protected static ArrayList<Float> array;

    protected static int delayTime = 1;

    protected static SwingWorker<Void, Void> sorter;

    /**
     * Returns the delay time in milliseconds used during sorting.
     *
     * @return The delay time in milliseconds.
     */
    public static int getDelayTime() {
        return delayTime;
    }

    /**
     * Sets the delay time in milliseconds used during sorting.
     *
     * @param value The delay time in milliseconds.
     */
    public static void setDelayTime(int value) {
        delayTime = value;
    }

    /**
     * Returns the sorter SwingWorker used for sorting.
     *
     * @return The sorter SwingWorker.
     */
    public static SwingWorker<Void, Void> getSorter() {
        return sorter;
    }

    /**
     * Finishes the sorting process by updating the visualizer panel.
     *
     * @param visualizerPanel The visualizer panel to update.
     * @throws InterruptedException if the thread is interrupted during sleep.
     */
    public static void finish(VisualizerPanel visualizerPanel) throws InterruptedException {
        int n = array.size();
        int j = -1;
        for (int i = 0; i < n; i++) {
            VisualizerPanel.getTraversingIndexes().set(0, i);
            VisualizerPanel.getCurrentIndexes().add(j++);

            Thread.sleep(delayTime);
            visualizerPanel.repaint();
        }

        VisualizerPanel.getCurrentIndexes().add(j);

        Thread.sleep(delayTime);
        visualizerPanel.repaint();
    }
}

