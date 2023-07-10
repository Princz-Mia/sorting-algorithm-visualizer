# Sorting Algorithm Visualizer

This Java program provides a visualization of various sorting algorithms. It allows users to observe how different sorting algorithms work by visually representing the sorting process step by step.

## Features

- Visual representation: The program uses a graphical interface to display the sorting process, making it easier to understand and follow along.
- Multiple sorting algorithms: The program currently supports the following sorting algorithms:
  - Bitonic Sort
  - Bogo Sort
  - Bubble Sort
  - Cocktail Sort
  - Comb Sort
  - Gnome Sort
  - Heap Sort
  - Insertion Sort
  - Merge Sort
  - Odd-Even Sort / Brick Sort
  - Pancake Sort
  - Pigeonhole Sort
  - Quick Sort
  - Selection Sort
  - Shell Sort
  - Stooge Sort
  - 3-way Merge Sort
  - Tim Sort
- Adjustable settings: Users can adjust the number of elements to sort and the delay time between steps, providing flexibility in visualizing the sorting process.
- Real-time updates: The program updates the visualization in real-time, allowing users to see the changes made during sorting.
- Users can access detailed information about the selected sorting algorithms by choosing an algorithm and clicking on the info button located in the upper right corner.

## Getting Started

To run the Sorting Algorithm Visualizer on your machine, follow these steps:

1. Ensure you have Java Development Kit (JDK) installed.
2. Clone the repository or download the source code.
3. Open a command prompt or terminal window on your computer.
4. Navigate to the src directory of the Java program. For example, if the program folder is located at C:\Projects\SortingAlgoVisualzer\src, you would use the following command in the command prompt:
   ```
   cd C:\Projects\SortingAlgoVisualzer\src
   ```
5. Once you are inside the src directory, compile the program by executing the following command:
   ```
   javac -cp "../lib/*" com/javaswingdev/*.java com/mia_princz/graphics/*.java com/mia_princz/algorithms/*.java
   ```
6. After compiling the program, navigate one level up in the directory structure using the following command:
   ```
   cd ..
   ```
7. Now execute the following command to run the program:
   ```
   java -cp .;src;assets;lib/* com.mia_princz.graphics.Main
   ```

## Usage

1. Launch the program.
2. Adjust the settings in the graphical interface, such as the number of elements and the delay time.
3. Select a sorting algorithm from the available options.
4. Click the "Shuffle Array" button to start the shuffle process.
5. Once the array is shuffled, click the "Sort Array" button to start the sorting process.
6. Observe the visualization as the sorting algorithm progresses.
7. Once the sorting is complete, the program will indicate the end of the process.
8. Adjust the settings or select a different sorting algorithm to visualize another sorting process.

## Graphical Interface

<a href="https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExdDNqcTZpY3ZwNnJ0MThqdmNxeGtkdXFjaW44dHcxdWtscWFsMWxqYSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/mNXiPDnvlvmMtfYuRo/giphy.gif"><img src="https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExdDNqcTZpY3ZwNnJ0MThqdmNxeGtkdXFjaW44dHcxdWtscWFsMWxqYSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/mNXiPDnvlvmMtfYuRo/giphy.gif" align="center" title="Click to download picture" />

## Contributing

Contributions to this project are welcome. If you find any issues or have ideas for improvements, feel free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Acknowledgments

- Information and details about the implemented algorithms were primarily sourced from various platforms such as [Javatpoint](https://www.javatpoint.com/), [GeeksforGeeks](https://www.geeksforgeeks.org/) and [Wikipedia](https://en.wikipedia.org/wiki/Sorting_algorithm).
- The graphical user interface (GUI) is built using Swing and features custom components developed by [Raven Laing](https://github.com/DJ-Raven).
  
## Disclaimer Regarding Images

Please note that the images (icons) used in this project may be subject to copyright and other intellectual property rights held by their respective owners. I do not claim ownership of these assets, and I have used them for demonstration purposes only. It's important to respect the rights of asset creators and obtain proper permissions or licenses before using their work in your own projects. I encourage you to replace these assets with your own or use assets that are properly licensed for your specific use case. If you are the creator or owner of any of the images used in this project and would like them to be removed or credited differently, please contact me, and I will promptly address your concerns. Thank you for understanding and respecting the rights of asset creators.
