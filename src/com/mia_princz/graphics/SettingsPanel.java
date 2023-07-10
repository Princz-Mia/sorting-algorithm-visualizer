package com.mia_princz.graphics;

import com.mia_princz.algorithms.*;

import com.javaswingdev.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The SettingsPanel class represents a panel that contains various settings and controls for the visualizer program.
 * It extends the PanelGlowingGradient class and implements the ActionListener interface to handle button events.
 */
public class SettingsPanel extends PanelGlowingGradient implements ActionListener {

    // Constants for panel dimensions and settings
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 125;
    private static final int MIN_ARRAY_SIZE = 64;
    private static final int MAX_ARRAY_SIZE = 1024;
    private static final int MIN_DELAY = 1;
    private static final int MAX_DELAY = 32;

    // Buttons
    private static CustomButton SHUFFLE_BUTTON;
    private static CustomButton SORTER_BUTTON;
    private static CustomButton STOP_BUTTON;
    private static CustomButton INFO_BUTTON;

    // Sliders
    private static SliderGradient COLUMN_NUMBER_SLIDER;
    private static SliderGradient DELAY_TIME_SLIDER;

    // ComboBox
    private static CustomComboBox SORTING_ALGORITHM_SELECTOR;

    // Labels
    private static JLabel ARRAY_SIZE_LABEL;
    private static JLabel DELAY_TIME_LABEL;

    // Ref to the visualizerPanel
    private final VisualizerPanel VISUALIZER_PANEL;

    // List of available sorting algorithms
    private final String[] AVAILABLE_SORTING_ALGORITHMS = {
            "Bitonic Sort",
            "Bogo Sort",
            "Bubble Sort",
            "Cocktail Sort",
            "Comb Sort",
            "Gnome Sort",
            "Heap Sort",
            "Insertion Sort",
            "Merge Sort",
            "Odd-Even Sort / Brick Sort",
            "Pancake Sort",
            "Pigeonhole Sort",
            "Quick Sort",
            "Selection Sort",
            "Shell Sort",
            "Stooge Sort",
            "3-way Merge Sort",
            "Tim Sort",
    };

    /**
     * Creates a new SettingsPanel with the specified VisualizerPanel.
     *
     * @param VISUALIZER_PANEL The VisualizerPanel to associate with this SettingsPanel.
     */
    public SettingsPanel(VisualizerPanel VISUALIZER_PANEL) {
        setBounds(0, -10 + 39, WIDTH, HEIGHT);

        this.VISUALIZER_PANEL = VISUALIZER_PANEL;

        initButtons();
        initLabels();
        initSliders();
        initComboBoxes();
        initSpringLayout();
    }

    /**
     * Initializes the buttons and sets their properties.
     */
    private void initButtons() {
        SHUFFLE_BUTTON = new CustomButton();
        SHUFFLE_BUTTON.setText("Shuffle Array");
        SHUFFLE_BUTTON.setActionCommand("shuffle");
        SHUFFLE_BUTTON.addActionListener(this);
        SHUFFLE_BUTTON.setPreferredSize(new Dimension(100,50));

        SORTER_BUTTON = new CustomButton();
        SORTER_BUTTON.setText("Sort Array");
        SORTER_BUTTON.setActionCommand("sort");
        SORTER_BUTTON.addActionListener(this);
        SORTER_BUTTON.setPreferredSize(new Dimension(100,50));

        STOP_BUTTON = new CustomButton();
        STOP_BUTTON.setText("Stop Sorting");
        STOP_BUTTON.setActionCommand("stop");
        STOP_BUTTON.addActionListener(this);
        STOP_BUTTON.setPreferredSize(new Dimension(100,50));


        ImageIcon icon = new ImageIcon("assets/info_icon.png");
        INFO_BUTTON = new CustomButton();
        INFO_BUTTON.setIcon(icon);
        INFO_BUTTON.setStyle(CustomButton.ButtonStyle.INFO_ICON);
        INFO_BUTTON.setRound(10);
        INFO_BUTTON.setActionCommand("info");
        INFO_BUTTON.addActionListener(this);
        INFO_BUTTON.setPreferredSize(new Dimension(60,50));

        add(SHUFFLE_BUTTON);
        add(SORTER_BUTTON);
        add(STOP_BUTTON);
        add(INFO_BUTTON);
    }

    /**
     * Initializes the labels and sets their properties.
     */
    private void initLabels() {
        ARRAY_SIZE_LABEL = new JLabel();
        ARRAY_SIZE_LABEL.setForeground(new Color(200, 200, 200, 255));
        ARRAY_SIZE_LABEL.setText("Array size: " + VisualizerPanel.getBaseColumnNumber());
        ARRAY_SIZE_LABEL.setPreferredSize(new Dimension(125, 30));

        DELAY_TIME_LABEL = new JLabel();
        DELAY_TIME_LABEL.setText("Delay time: " + MAX_DELAY / 2);
        DELAY_TIME_LABEL.setForeground(new Color(200, 200, 200, 255));
        DELAY_TIME_LABEL.setPreferredSize(new Dimension(100, 30));

        add(ARRAY_SIZE_LABEL);
        add(DELAY_TIME_LABEL);
    }

    /**
     * Initializes the sliders and sets their properties.
     */
    private void initSliders() {
        COLUMN_NUMBER_SLIDER = new SliderGradient();
        COLUMN_NUMBER_SLIDER.setPaintLabels(true);
        COLUMN_NUMBER_SLIDER.setPaintTicks(true);
        COLUMN_NUMBER_SLIDER.setMinimum(MIN_ARRAY_SIZE);
        COLUMN_NUMBER_SLIDER.setMaximum(MAX_ARRAY_SIZE);
        COLUMN_NUMBER_SLIDER.setMinorTickSpacing(48);
        COLUMN_NUMBER_SLIDER.setMajorTickSpacing(480);
        COLUMN_NUMBER_SLIDER.setForeground(new Color(200, 200, 200));
        COLUMN_NUMBER_SLIDER.setPreferredSize(new Dimension(200,50));
        COLUMN_NUMBER_SLIDER.setOpaque(false);
        COLUMN_NUMBER_SLIDER.setValue(VisualizerPanel.getBaseColumnNumber());
        COLUMN_NUMBER_SLIDER.addChangeListener(e -> {
            VisualizerPanel.setBaseColumnNumber(COLUMN_NUMBER_SLIDER.getValue());
            VisualizerPanel.getColumnHeight().clear();

            ARRAY_SIZE_LABEL.setText("Array size: " + VisualizerPanel.getBaseColumnNumber());

            VISUALIZER_PANEL.initColumnHeight();
        });

        DELAY_TIME_SLIDER = new SliderGradient();
        DELAY_TIME_SLIDER.setPaintLabels(true);
        DELAY_TIME_SLIDER.setPaintTicks(true);
        DELAY_TIME_SLIDER.setMinimum(MIN_DELAY);
        DELAY_TIME_SLIDER.setMaximum(MAX_DELAY);
        DELAY_TIME_SLIDER.setMinorTickSpacing(1);
        DELAY_TIME_SLIDER.setMajorTickSpacing(15);
        DELAY_TIME_SLIDER.setForeground(new Color(200, 200, 200));
        DELAY_TIME_SLIDER.setPreferredSize(new Dimension(200,50));
        DELAY_TIME_SLIDER.setOpaque(false);
        DELAY_TIME_SLIDER.setValue(MAX_DELAY / 2);
        BitonicSort.setDelayTime(DELAY_TIME_SLIDER.getValue());
        DELAY_TIME_SLIDER.addChangeListener(e -> {
            BitonicSort.setDelayTime(DELAY_TIME_SLIDER.getValue());
            DELAY_TIME_LABEL.setText("Delay time: " + BitonicSort.getDelayTime());
        });

        add(COLUMN_NUMBER_SLIDER);
        add(DELAY_TIME_SLIDER);
    }

    /**
     * Initializes the sorting algorithm selector ComboBox and sets its properties.
     */
    private void initComboBoxes() {
        SORTING_ALGORITHM_SELECTOR = new CustomComboBox();
        SORTING_ALGORITHM_SELECTOR.setOpaque(false);
        SORTING_ALGORITHM_SELECTOR.setPreferredSize(new Dimension(200,40));
        SORTING_ALGORITHM_SELECTOR.setModel(new DefaultComboBoxModel(AVAILABLE_SORTING_ALGORITHMS));
        SORTING_ALGORITHM_SELECTOR.setSelectedIndex(-1);
        SORTING_ALGORITHM_SELECTOR.setLabeText("Sorting Algorithms");

        add(SORTING_ALGORITHM_SELECTOR);
    }

    /**
     * Initializes the SpringLayout for positioning the components.
     */
    private void initSpringLayout() {
        // Layout manager
        SpringLayout SPRING_LAYOUT = new SpringLayout();
        SPRING_LAYOUT.putConstraint(SpringLayout.NORTH, SHUFFLE_BUTTON, 15, SpringLayout.NORTH, this);
        SPRING_LAYOUT.putConstraint(SpringLayout.WEST, SHUFFLE_BUTTON, 60, SpringLayout.WEST, this);

        SPRING_LAYOUT.putConstraint(SpringLayout.NORTH, COLUMN_NUMBER_SLIDER, 15, SpringLayout.NORTH, this);
        SPRING_LAYOUT.putConstraint(SpringLayout.WEST, COLUMN_NUMBER_SLIDER, 50, SpringLayout.EAST, SHUFFLE_BUTTON);

        SPRING_LAYOUT.putConstraint(SpringLayout.NORTH, ARRAY_SIZE_LABEL, 25, SpringLayout.NORTH, this);
        SPRING_LAYOUT.putConstraint(SpringLayout.WEST, ARRAY_SIZE_LABEL, 20, SpringLayout.EAST, COLUMN_NUMBER_SLIDER);

        SPRING_LAYOUT.putConstraint(SpringLayout.NORTH, DELAY_TIME_SLIDER, 15, SpringLayout.NORTH, this);
        SPRING_LAYOUT.putConstraint(SpringLayout.WEST, DELAY_TIME_SLIDER, 20, SpringLayout.EAST, ARRAY_SIZE_LABEL);

        SPRING_LAYOUT.putConstraint(SpringLayout.NORTH, DELAY_TIME_LABEL, 25, SpringLayout.NORTH, this);
        SPRING_LAYOUT.putConstraint(SpringLayout.WEST, DELAY_TIME_LABEL, 20, SpringLayout.EAST, DELAY_TIME_SLIDER);

        SPRING_LAYOUT.putConstraint(SpringLayout.NORTH, SORTING_ALGORITHM_SELECTOR, 20, SpringLayout.NORTH, this);
        SPRING_LAYOUT.putConstraint(SpringLayout.WEST, SORTING_ALGORITHM_SELECTOR, 20, SpringLayout.EAST, DELAY_TIME_LABEL);

        SPRING_LAYOUT.putConstraint(SpringLayout.NORTH, SORTER_BUTTON, 15, SpringLayout.NORTH, this);
        SPRING_LAYOUT.putConstraint(SpringLayout.WEST, SORTER_BUTTON, 50, SpringLayout.EAST, SORTING_ALGORITHM_SELECTOR);

        SPRING_LAYOUT.putConstraint(SpringLayout.NORTH, STOP_BUTTON, 15, SpringLayout.NORTH, this);
        SPRING_LAYOUT.putConstraint(SpringLayout.WEST, STOP_BUTTON, 50, SpringLayout.EAST, SORTER_BUTTON);

        SPRING_LAYOUT.putConstraint(SpringLayout.NORTH, INFO_BUTTON, 15, SpringLayout.NORTH, this);
        SPRING_LAYOUT.putConstraint(SpringLayout.WEST, INFO_BUTTON, 50, SpringLayout.EAST, STOP_BUTTON);

        setLayout(SPRING_LAYOUT);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        String selectedSorting = (String) SORTING_ALGORITHM_SELECTOR.getSelectedItem();

        if (ae.getActionCommand().equals("shuffle")) {
            if (VisualizerPanel.getIsShufflerDone() && VisualizerPanel.isSortingDone())
                VISUALIZER_PANEL.initShuffler();
        } else if (ae.getActionCommand().equals("sort")) {
            if (VisualizerPanel.getIsShufflerDone() && VisualizerPanel.isSortingDone()) {
                if (selectedSorting == null) {
                    initMissingAlgorithmErrorMessage();
                    return;
                }

                if (selectedSorting.equals("Bitonic Sort")) {
                    boolean isPowerOfTwo = ((int) Math.ceil((Math.log(VisualizerPanel.getBaseColumnNumber()) / (Math.log(2)))) == (int) (Math.floor((Math.log(VisualizerPanel.getBaseColumnNumber()) / Math.log(2)))));
                    if (!isPowerOfTwo) {
                        initBitonicSortErrorMessage();
                        return;
                    }
                }

                VisualizerPanel.setSortingDone(false);

                switch (selectedSorting) {
                    case "Bitonic Sort"                 -> BitonicSort.sort(VISUALIZER_PANEL);
                    case "Bogo Sort"                    -> BogoSort.sort(VISUALIZER_PANEL);
                    case "Bubble Sort"                  -> BubbleSort.sort(VISUALIZER_PANEL);
                    case "Cocktail Sort"                -> CocktailSort.sort(VISUALIZER_PANEL);
                    case "Comb Sort"                    -> CombSort.sort(VISUALIZER_PANEL);
                    case "Gnome Sort"                   -> GnomeSort.sort(VISUALIZER_PANEL);
                    case "Heap Sort"                    -> HeapSort.sort(VISUALIZER_PANEL);
                    case "Insertion Sort"               -> InsertionSort.sort(VISUALIZER_PANEL);
                    case "Merge Sort"                   -> MergeSort.sort(VISUALIZER_PANEL);
                    case "Odd-Even Sort / Brick Sort"   -> OddEvenSort.sort(VISUALIZER_PANEL);
                    case "Pancake Sort"                 -> PancakeSort.sort(VISUALIZER_PANEL);
                    case "Pigeonhole Sort"              -> PigeonholeSort.sort(VISUALIZER_PANEL);
                    case "Quick Sort"                   -> QuickSort.sort(VISUALIZER_PANEL);
                    case "Selection Sort"               -> SelectionSort.sort(VISUALIZER_PANEL);
                    case "Shell Sort"                   -> ShellSort.sort(VISUALIZER_PANEL);
                    case "Stooge Sort"                  -> StoogeSort.sort(VISUALIZER_PANEL);
                    case "3-way Merge Sort"             -> ThreeWayMergeSort.sort(VISUALIZER_PANEL);
                    case "Tim Sort"                     -> TimSort.sort(VISUALIZER_PANEL);
                    default                             -> VisualizerPanel.setSortingDone(true);
                }
            }
        } else if (ae.getActionCommand().equals("stop")) {
            if (BitonicSort.getSorter() != null) {
                BitonicSort.getSorter().cancel(true);
            }
        } else if (ae.getActionCommand().equals("info")) {
            if (selectedSorting == null) return;
            initMoreInfoMessage(selectedSorting);
        }
    }

    private void initMissingAlgorithmErrorMessage() {
        Message obj = new Message();
        obj.titleLabel.setText("Missing Sorting Algorithm Selection");

        obj.text.setText("""
                Please select a sorting algorithm from the dropdown menu before sorting the array. You can choose an algorithm by clicking on the dropdown and selecting one. Once you've made your selection, you can proceed by pressing the 'Sort Array' button.
                \nPlease press the OK button to proceed . . .""");

        obj.eventOK(ae -> GlassPanePopup.closePopupLast());
        GlassPanePopup.showPopup(obj);
    }

    private void initBitonicSortErrorMessage() {
        Message obj = new Message();
        obj.titleLabel.setText("Invalid Array Size for Bitonic Sort");

        obj.text.setText("""
                To ensure proper functioning of the Bitonic Sort algorithm, the size of the array must be a power of two. You can modify the size by using the slider located in the settings panel.
                \nPlease press the OK button to proceed . . .""");

        obj.eventOK(ae -> GlassPanePopup.closePopupLast());
        GlassPanePopup.showPopup(obj);
    }

    /**
     * Initializes the MoreInfo Window with description of the selected sorting algorithm.
     */
    private void initMoreInfoMessage(String selectedSorting) {
        Message obj = new Message();
        obj.titleLabel.setText("About: " + selectedSorting);

        switch (selectedSorting) {
            case "Bitonic Sort":
                obj.text.setText("""
                        Bitonic sort is a comparison-based sorting algorithm that can be run in parallel. It focuses on converting a random sequence of numbers into a bitonic sequence, one that monotonically increases, then decreases. Rotations of a bitonic sequence are also bitonic. More specifically, bitonic sort can be modelled as a type of sorting network. The initial unsorted sequence enters through input pipes, where a series of comparators switch two entries to be in either increasing or decreasing order.

                        The algorithm, created by Ken Batcher in 1968, consists of two parts. First, the unsorted sequence is built into a bitonic sequence; then, the series is split multiple times into smaller sequences until the input is in sorted order.

                        • Worst-case performance:\t\uD835\uDCAA(\uD835\uDC59\uD835\uDC5C\uD835\uDC54\u00B2(\uD835\uDC5B))
                        • Best-case performance:\t\uD835\uDCAA(\uD835\uDC59\uD835\uDC5C\uD835\uDC54\u00B2(\uD835\uDC5B))
                        • Average performance:\t\uD835\uDCAA(\uD835\uDC59\uD835\uDC5C\uD835\uDC54\u00B2(\uD835\uDC5B))

                        • Worst-case space complexity:\t\uD835\uDCAA(\uD835\uDC5B \uD835\uDC59\uD835\uDC5C\uD835\uDC54\u00B2(\uD835\uDC5B))

                        • Stable:\t\t\tNo""");
                break;

            case "Bogo Sort":
                obj.text.setText("""
                        In computer science, bogosort (also known as permutation sort, stupid sort, slowsort or bozosort) is a sorting algorithm based on the generate and test paradigm. The function successively generates permutations of its input until it finds one that is sorted. It is not considered useful for sorting, but may be used for educational purposes, to contrast it with more efficient algorithms.

                        Two versions of this algorithm exist: a deterministic version that enumerates all permutations until it hits a sorted one, and a randomized version that randomly permutes its input. An analogy for the working of the latter version is to sort a deck of cards by throwing the deck into the air, picking the cards up at random, and repeating the process until the deck is sorted. Its name is a portmanteau of the words bogus and sort.

                        • Worst-case performance:\t\uD835\uDCAA(\uD835\uDC56\uD835\uDC5B\uD835\uDC53\uD835\uDC56\uD835\uDC5B\uD835\uDC56\uD835\uDC61\uD835\uDC52)
                        • Best-case performance:\t\uD835\uDCAA(\uD835\uDC5B)
                        • Average performance:\t\uD835\uDCAA(\uD835\uDC5B! * \uD835\uDC5B)

                        • Worst-case space complexity:\t\uD835\uDCAA(1)

                        • Stable:\t\t\tNo""");
                break;
            case "Bubble Sort":
                obj.text.setText("""
                        Bubble sort, sometimes referred to as sinking sort, is a simple sorting algorithm that repeatedly steps through the input list element by element, comparing the current element with the one after it, swapping their values if needed. These passes through the list are repeated until no swaps had to be performed during a pass, meaning that the list has become fully sorted. The algorithm, which is a comparison sort, is named for the way the larger elements "bubble" up to the top of the list.

                        This simple algorithm performs poorly in real world use and is used primarily as an educational tool. More efficient algorithms such as quicksort, timsort, or merge sort are used by the sorting libraries built into popular programming languages such as Python and Java.

                        • Worst-case performance:\t\uD835\uDCAA(\uD835\uDC5B\u00B2)
                        • Best-case performance:\t\uD835\uDCAA(\uD835\uDC5B)
                        • Average performance:\t\uD835\uDCAA(\uD835\uDC5B\u00B2)

                        • Worst-case space complexity:\t\uD835\uDCAA(1)

                        • Stable:\t\t\tYes""");
                break;
            case "Cocktail Sort":
                obj.text.setText("""
                        Cocktail shaker sort, also known as bidirectional bubble sort, cocktail sort, shaker sort (which can also refer to a variant of selection sort), ripple sort, shuffle sort, or shuttle sort, is an extension of bubble sort. The algorithm extends bubble sort by operating in two directions. While it improves on bubble sort by more quickly moving items to the beginning of the list, it provides only marginal performance improvements.

                        Like most variants of bubble sort, cocktail shaker sort is used primarily as an educational tool. More performant algorithms such as quicksort, merge sort, or timsort are used by the sorting libraries built into popular programming languages such as Python and Java.

                        • Worst-case performance:\t\uD835\uDCAA(\uD835\uDC5B\u00B2)
                        • Best-case performance:\t\uD835\uDCAA(\uD835\uDC5B)
                        • Average performance:\t\uD835\uDCAA(\uD835\uDC5B\u00B2)

                        • Worst-case space complexity:\t\uD835\uDCAA(1)

                        • Stable:\t\t\tYes""");
                break;
            case "Comb Sort":
                obj.text.setText("""
                        Comb sort is a relatively simple sorting algorithm originally designed by Włodzimierz Dobosiewicz and Artur Borowy in 1980, later rediscovered (and given the name "Combsort") by Stephen Lacey and Richard Box in 1991. Comb sort improves on bubble sort in the same way that Shellsort improves on insertion sort.

                        The National Institute of Standards and Technology's (NIST) "diminishing increment sort" definition mentions the term 'comb sort' as visualizing iterative passes of the data, "where the teeth of a comb touch;" the former term is linked to Don Knuth.

                        • Worst-case performance:\t\uD835\uDCAA(\uD835\uDC5B\u00B2)
                        • Best-case performance:\t\uD835\uDEF3(\uD835\uDC5B \uD835\uDC59\uD835\uDC5C\uD835\uDC54 \uD835\uDC5B)
                        • Average performance:\t\uD835\uDEFA(\uD835\uDC5B\u00B2 / 2\u1D56)

                        • Worst-case space complexity:\t\uD835\uDCAA(1)

                        • Stable:\t\t\tYes""");
                break;
            case "Gnome Sort":
                obj.text.setText("""
                        Gnome sort (nicknamed stupid sort) is a variation of the insertion sort sorting algorithm that does not use nested loops. Gnome sort was originally proposed by Iranian computer scientist Hamid Sarbazi-Azad (professor of Computer Science and Engineering at Sharif University of Technology) in 2000. The sort was first called stupid sort (not to be confused with bogosort), and then later described by Dick Grune and named gnome sort.

                        Gnome sort performs at least as many comparisons as insertion sort and has the same asymptotic run time characteristics. Gnome sort works by building a sorted list one element at a time, getting each item to the proper place in a series of swaps. The average running time is O(n2) but tends towards O(n) if the list is initially almost sorted.

                        Dick Grune described the sorting method with the following story:

                        \s\s\s\sGnome Sort is based on the technique used by the standard Dutch
                        \s\s\s\sGarden Gnome (Du.: tuinkabouter). Here is how a garden gnome
                        \s\s\s\ssorts a line of flower pots. Basically, he looks at the flower pot next to
                        \s\s\s\shim and the previous one; if they are in the right order he steps one
                        \s\s\s\spot forward, otherwise, he swaps them and steps one pot backward.
                        \s\s\s\sBoundary conditions: if there is no previous pot, he steps forwards; if
                        \s\s\s\sthere is no pot next to him, he is done.
                        \t\t— "Gnome Sort - The Simplest Sort Algorithm"

                        • Worst-case performance:\t\uD835\uDCAA(\uD835\uDC5B\u00B2)
                        • Best-case performance:\t\uD835\uDCAA(\uD835\uDC5B)
                        • Average performance:\t\uD835\uDCAA(\uD835\uDC5B\u00B2)

                        • Worst-case space complexity:\t\uD835\uDCAA(1)

                        • Stable:\t\t\tYes""");
                break;
            case "Heap Sort":
                obj.text.setText("""
                        In computer science, heapsort is a comparison-based sorting algorithm. Heapsort can be thought of as an improved selection sort: like selection sort,heapsort divides its input into a sorted and an unsorted region, and it iteratively shrinks the unsorted region by extracting the largest element from it and inserting it into the sorted region. Unlike selection sort, heapsort does not waste time with a linear-time scan of the unsorted region; rather, heap sort maintains the unsorted region in a heap data structure to more quickly find the largest element in each step.

                        Although somewhat slower in practice on most machines than a well-implemented quicksort, it has the advantage of a more favorable worst-case O(n log n) runtime (and as such is used by Introsort as a fallback should it detect that quicksort is becoming degenerate). Heapsort is an in-place algorithm, but it is not a stable sort.

                        Heapsort was invented by J. W. J. Williams in 1964. This was also the birth of the heap, presented already by Williams as a useful data structure in its own right. In the same year, Robert W. Floyd published an improved version that could sort an array in-place, continuing his earlier research into the treesort algorithm.

                        • Worst-case performance:\t\uD835\uDCAA(\uD835\uDC5B \uD835\uDC59\uD835\uDC5C\uD835\uDC54 \uD835\uDC5B)
                        • Best-case performance:\t\uD835\uDCAA(\uD835\uDC5B \uD835\uDC59\uD835\uDC5C\uD835\uDC54 \uD835\uDC5B)
                        • Average performance:\t\uD835\uDCAA(\uD835\uDC5B \uD835\uDC59\uD835\uDC5C\uD835\uDC54 \uD835\uDC5B)

                        • Worst-case space complexity:\t\uD835\uDCAA(1)

                        • Stable:\t\t\tNo""");
                break;
            case "Insertion Sort":
                obj.text.setText("""
                        Insertion sort is a simple sorting algorithm that builds the final sorted array (or list) one item at a time by comparisons. It is much less efficient on large lists than more advanced algorithms such as quicksort, heapsort, or merge sort. However, insertion sort provides several advantages:

                        • Simple implementation: Jon Bentley shows a three-line C/C++ version
                        \s\s\sthat is five lines when optimized.
                        • Efficient for (quite) small data sets, much like other quadratic (i.e., O(n2))
                        \s\s\ssorting algorithms
                        • More efficient in practice than most other simple quadratic algorithms
                        \s\s\ssuch as selection sort or bubble sort
                        • Adaptive, i.e., efficient for data sets that are already substantially sorted:
                        \s\s\sthe time complexity is O(kn) when each element in the input is no more
                        \s\s\sthan k places away from its sorted position
                        • Stable; i.e., does not change the relative order of elements with equal
                        \s\s\skeys
                        • In-place; i.e., only requires a constant amount O(1) of additional memory
                        \s\s\sspace
                        • Online; i.e., can sort a list as it receives it
                        \s\s\sWhen people manually sort cards in a bridge hand, most use a method
                        \s\s\sthat is similar to insertion sort.

                        • Worst-case performance:\t\uD835\uDCAA(\uD835\uDC5B \uD835\uDC59\uD835\uDC5C\uD835\uDC54 \uD835\uDC5B)
                        • Best-case performance:\t\uD835\uDCAA(\uD835\uDC5B \uD835\uDC59\uD835\uDC5C\uD835\uDC54 \uD835\uDC5B)
                        • Average performance:\t\uD835\uDCAA(\uD835\uDC5B \uD835\uDC59\uD835\uDC5C\uD835\uDC54 \uD835\uDC5B)

                        • Worst-case space complexity:\t\uD835\uDCAA(1)

                        • Stable:\t\t\tYes""");
                break;
            case "Merge Sort":
                obj.text.setText("""
                        In computer science, merge sort (also commonly spelled as mergesort) is an efficient, general-purpose, and comparison-based sorting algorithm. Most implementations produce a stable sort, which means that the order of equal elements is the same in the input and output. Merge sort is a divide-and-conquer algorithm that was invented by John von Neumann in 1945. A detailed description and analysis of bottom-up merge sort appeared in a report by Goldstine and von Neumann as early as 1948.

                        • Worst-case performance:\t\uD835\uDCAA(\uD835\uDC5B \uD835\uDC59\uD835\uDC5C\uD835\uDC54 \uD835\uDC5B)
                        • Best-case performance:\t\uD835\uDCAA(\uD835\uDC5B \uD835\uDC59\uD835\uDC5C\uD835\uDC54 \uD835\uDC5B)
                        • Average performance:\t\uD835\uDCAA(\uD835\uDC5B \uD835\uDC59\uD835\uDC5C\uD835\uDC54 \uD835\uDC5B)

                        • Worst-case space complexity:\t\uD835\uDCAA(\uD835\uDC5B)

                        • Stable:\t\t\tYes""");
                break;
            case "Odd-Even Sort / Brick Sort":
                obj.text.setText("""
                        In computing, an odd–even sort or odd–even transposition sort (also known as brick sort or parity sort) is a relatively simple sorting algorithm, developed originally for use on parallel processors with local interconnections. It is a comparison sort related to bubble sort, with which it shares many characteristics. It functions by comparing all odd/even indexed pairs of adjacent elements in the list and, if a pair is in the wrong order (the first is larger than the second) the elements are switched. The next step repeats this for even/odd indexed pairs (of adjacent elements). Then it alternates between odd/even and even/odd steps until the list is sorted.

                        • Worst-case performance:\t\uD835\uDCAA(\uD835\uDC5B\u00B2)
                        • Best-case performance:\t\uD835\uDEF3(\uD835\uDC5B)
                        • Average performance:\t\uD835\uDCAA(\uD835\uDC5B\u00B2)

                        • Worst-case space complexity:\t\uD835\uDCAA(1)

                        • Stable:\t\t\tYes""");
                break;
            case "Pancake Sort":
                obj.text.setText("""
                        Pancake sorting is the mathematical problem of sorting a disordered stack of pancakes in order of size when a spatula can be inserted at any point in the stack and used to flip all pancakes above it. A pancake number is the minimum number of flips required for a given number of pancakes. In this form, the problem was first discussed by American geometer Jacob E. Goodman. A variant of the problem is concerned with burnt pancakes, where each pancake has a burnt side and all pancakes must, in addition, end up with the burnt side on bottom.

                        All sorting methods require pairs of elements to be compared. For the traditional sorting problem, the usual problem studied is to minimize the number of comparisons required to sort a list. The number of actual operations, such as swapping two elements, is then irrelevant. For pancake sorting problems, in contrast, the aim is to minimize the number of operations, where the only allowed operations are reversals of the elements of some prefix of the sequence. Now, the number of comparisons is irrelevant.

                        • Worst-case performance:\t\uD835\uDCAA(\uD835\uDC5B\u00B2)
                        • Best-case performance:\t\uD835\uDCAA(\uD835\uDC5B\u00B2)
                        • Average performance:\t\uD835\uDCAA(\uD835\uDC5B\u00B2)

                        • Worst-case space complexity:\t\uD835\uDCAA(1)

                        • Stable:\t\t\tNo""");
                break;
            case "Pigeonhole Sort":
                obj.text.setText("""
                        Pigeonhole sorting is a sorting algorithm that is suitable for sorting lists of elements where the number n of elements and the length N of the range of possible key values are approximately the same. It requires O(n + N) time. It is similar to counting sort, but differs in that it "moves items twice: once to the bucket array and again to the final destination counting sort builds an auxiliary array then uses the array to compute each item's final destination and move the item there."

                        The pigeonhole algorithm works as follows:

                        \s\s1.\s\sGiven an array of values to be sorted, set up an auxiliary array of initially
                        \s\s\s\s\s\s\sempty "pigeonholes", one pigeonhole for each key in the range of the\s
                        \s\s\s\s\s\s\skeys in the original array.
                        \s\s2.\s\sGoing over the original array, put each value into the pigeonhole
                        \s\s\s\s\s\s\scorresponding to its key, such that each pigeonhole eventually contains a
                        \s\s\s\s\s\s\slist of all values with that key.
                        \s\s3.\s\sIterate over the pigeonhole array in increasing order of keys, and for each
                        \s\s\s\s\s\s\spigeonhole, put its elements into the original array in increasing order.

                        • Worst-case performance:\t\uD835\uDCAA(\uD835\uDC5B + \uD835\uDDAD)
                        • Best-case performance:\t\uD835\uDCAA(\uD835\uDC5B + \uD835\uDDAD)
                        • Average performance:\t\uD835\uDCAA(\uD835\uDC5B + \uD835\uDDAD)

                        • Worst-case space complexity:\t\uD835\uDCAA(\uD835\uDC5B + \uD835\uDDAD)

                        • Stable:\t\t\tYes""");
                break;
            case "Quick Sort":
                obj.text.setText("""
                        Quicksort is an efficient, general-purpose sorting algorithm. Quicksort was developed by British computer scientist Tony Hoare in 1959 and published in 1961. It is still a commonly used algorithm for sorting. Overall, it is slightly faster than merge sort and heapsort for randomized data, particularly on larger distributions.

                        Quicksort is a divide-and-conquer algorithm. It works by selecting a 'pivot' element from the array and partitioning the other elements into two sub-arrays, according to whether they are less than or greater than the pivot. For this reason, it is sometimes called partition-exchange sort. The sub-arrays are then sorted recursively. This can be done in-place, requiring small additional amounts of memory to perform the sorting.

                        Quicksort is a comparison sort, meaning that it can sort items of any type for which a "less-than" relation (formally, a total order) is defined. Most implementations of quicksort are not stable, meaning that the relative order of equal sort items is not preserved.

                        • Worst-case performance:\t\uD835\uDCAA(\uD835\uDC5B\u00B2)
                        • Best-case performance:\t\uD835\uDCAA(\uD835\uDC5B \uD835\uDC59\uD835\uDC5C\uD835\uDC54 \uD835\uDC5B)
                        • Average performance:\t\uD835\uDCAA(\uD835\uDC5B \uD835\uDC59\uD835\uDC5C\uD835\uDC54 \uD835\uDC5B)

                        • Worst-case space complexity:\t\uD835\uDCAA(\uD835\uDC5B \uD835\uDC59\uD835\uDC5C\uD835\uDC54 \uD835\uDC5B)

                        • Stable:\t\t\tNo""");
                break;
            case "Selection Sort":
                obj.text.setText("""
                        In computer science, selection sort is an in-place comparison sorting algorithm. It has an O(n2) time complexity, which makes it inefficient on large lists, and generally performs worse than the similar insertion sort. Selection sort is noted for its simplicity and has performance advantages over more complicated algorithms in certain situations, particularly where auxiliary memory is limited.

                        The algorithm divides the input list into two parts: a sorted sublist of items which is built up from left to right at the front (left) of the list and a sublist of the remaining unsorted items that occupy the rest of the list. Initially, the sorted sublist is empty and the unsorted sublist is the entire input list. The algorithm proceeds by finding the smallest (or largest, depending on sorting order) element in the unsorted sublist, exchanging (swapping) it with the leftmost unsorted element (putting it in sorted order), and moving the sublist boundaries one element to the right.

                        The time efficiency of selection sort is quadratic, so there are a number of sorting techniques which have better time complexity than selection sort.

                        • Worst-case performance:\t\uD835\uDCAA(\uD835\uDC5B\u00B2)
                        • Best-case performance:\t\uD835\uDCAA(\uD835\uDC5B\u00B2)
                        • Average performance:\t\uD835\uDCAA(\uD835\uDC5B\u00B2)

                        • Worst-case space complexity:\t\uD835\uDCAA(1)

                        • Stable:\t\t\tYes""");
                break;
            case "Shell Sort":
                obj.text.setText("""
                        Shellsort, also known as Shell sort or Shell's method, is an in-place comparison sort. It can be seen as either a generalization of sorting by exchange (bubble sort) or sorting by insertion (insertion sort). The method starts by sorting pairs of elements far apart from each other, then progressively reducing the gap between elements to be compared. By starting with far apart elements, it can move some out-of-place elements into position faster than a simple nearest neighbor exchange. Donald Shell published the first version of this sort in 1959. The running time of Shellsort is heavily dependent on the gap sequence it uses. For many practical variants, determining their time complexity remains an open problem.

                        • Worst-case performance:\t\uD835\uDCAA(\uD835\uDC5B\u00B2)
                        • Best-case performance:\t\uD835\uDCAA(\uD835\uDC5B \uD835\uDC59\uD835\uDC5C\uD835\uDC54 \uD835\uDC5B)
                        • Average performance:\t\uD835\uDCAA(\uD835\uDC5B \uD835\uDC59\uD835\uDC5C\uD835\uDC54(\uD835\uDC5B)\u00B2)

                        • Worst-case space complexity:\t\uD835\uDCAA(1)

                        • Stable:\t\t\tNo""");
                break;
            case "Stooge Sort":
                obj.text.setText("""
                        Stooge sort is a recursive sorting algorithm. It is notable for its exceptionally bad time complexity of O(nlog 3 / log 1.5 ) = O(n2.7095...). The running time of the algorithm is thus slower compared to reasonable sorting algorithms, and is slower than bubble sort, a canonical example of a fairly inefficient sort. It is however more efficient than Slowsort. The name comes from The Three Stooges.

                        The algorithm is defined as follows:

                        \s\s\u2022\s\sIf the value at the start is larger than the value at the end, swap
                        \s\s\s\s\s\sthem.
                        \s\s\u2022\s\sIf there are 3 or more elements in the list, then:
                        \s\s\s\s\s\s\u2022\s\sStooge sort the initial 2/3 of the list
                        \s\s\s\s\s\s\u2022\s\sStooge sort the final 2/3 of the list
                        \s\s\s\s\s\s\u2022\s\sStooge sort the initial 2/3 of the list again

                        It is important to get the integer sort size used in the recursive calls by rounding the 2/3 upwards, e.g. rounding 2/3 of 5 should give 4 rather than 3, as otherwise the sort can fail on certain data.

                        • Worst-case performance:\t\uD835\uDCAA(\uD835\uDDAD\u00B2\u00B7\u2077\u2070\u2079)
                        • Best-case performance:\t\uD835\uDCAA(\uD835\uDDAD\u00B2\u00B7\u2077\u2070\u2079)
                        • Average performance:\t\uD835\uDCAA(\uD835\uDDAD\u00B2\u00B7\u2077\u2070\u2079)

                        • Worst-case space complexity:\t\uD835\uDCAA(\uD835\uDDAD)

                        • Stable:\t\t\tNo""");
                break;
            case "3-way Merge Sort":
                obj.text.setText("""
                        Three-way Merge Sort, also known as Ternary Merge Sort, is a comparison-based sorting algorithm that divides the input into three sublists, recursively sorts each sublist, and then merges them back together. It focuses on converting a random sequence of numbers into three sorted sublists, which are then merged to obtain the final sorted output.

                        The algorithm, created by an unknown author, consists of the following steps:
                        \s\s1. Divide the input sequence into three equal or nearly equal parts.
                        \s\s2. Recursively sort each sublist until they are sufficiently small to be directly
                        \s\s\s\s\s\smerged.
                        \s\s3. Merge the three sorted sublists into a single sorted list by comparing the\s
                        \s\s\s\s\s\selements at corresponding positions and placing them in the correct order.

                        • Worst-case performance: \t\uD835\uDC42(\uD835\uDC5B log \uD835\uDC5B)
                        • Best-case performance: \t\uD835\uDC42(\uD835\uDC5B log \uD835\uDC5B)
                        • Average performance: \t\uD835\uDC42(\uD835\uDC5B log \uD835\uDC5B)

                        • Worst-case space complexity: \t\uD835\uDC42(\uD835\uDC5B)

                        • Stable:\t\t\tYes""");
                break;
            case "Tim Sort":
                obj.text.setText("""
                        Timsort is a hybrid, stable sorting algorithm, derived from merge sort and insertion sort, designed to perform well on many kinds of real-world data. It was implemented by Tim Peters in 2002 for use in the Python programming language. The algorithm finds subsequences of the data that are already ordered (runs) and uses them to sort the remainder more efficiently. This is done by merging runs until certain criteria are fulfilled. Timsort has been Python's standard sorting algorithm since version 2.3. It is also used to sort arrays of non-primitive type in Java SE 7, on the Android platform, in GNU Octave, on V8, Swift, and Rust.

                        It uses techniques from Peter McIlroy's 1993 paper "Optimistic Sorting and Information Theoretic Complexity".

                        • Worst-case performance:\t\uD835\uDCAA(\uD835\uDC5B \uD835\uDC59\uD835\uDC5C\uD835\uDC54 \uD835\uDC5B)
                        • Best-case performance:\t\uD835\uDCAA(\uD835\uDC5B)
                        • Average performance:\t\uD835\uDCAA(\uD835\uDC5B \uD835\uDC59\uD835\uDC5C\uD835\uDC54 \uD835\uDC5B)

                        • Worst-case space complexity:\t\uD835\uDCAA(\uD835\uDC5B)

                        • Stable:\t\t\tYes""");
                break;
            default:
                return;
        }

        obj.eventOK(ae -> GlassPanePopup.closePopupLast());
        GlassPanePopup.showPopup(obj);
    }

    /**
     * Returns the column number slider.
     *
     * @return The column number slider.
     */
    public static SliderGradient getColumnNumberSlider() {
        return COLUMN_NUMBER_SLIDER;
    }

    /**
     * Returns the delay time slider.
     *
     * @return The delay time slider.
     */
    public static SliderGradient getDelayTimeSlider() {
        return DELAY_TIME_SLIDER;
    }
}
