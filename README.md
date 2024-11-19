# Make a Square Puzzle Solver

## Description
This project solves a 4x4 puzzle using 4 or 5 pieces with rotation and flipping. The program is implemented in **Java** with a real-time GUI for input/output and uses multithreading to explore possible solutions efficiently.

## Features
- Input:
  - Define pieces using dimensions and grid representation.
  - Specify the number of pieces (4 or 5).
- Output:
  - Display all possible solutions for the puzzle.
  - Indicate if no solution exists.
- Multithreading for efficient problem solving.
- Real-time GUI for visualizing progress.

## Requirements
- Java 17 or higher
- JavaFX for GUI

## How It Works
1. The user specifies the puzzle pieces and their configurations.
2. The program spawns threads to test all possible placements and rotations.
3. Results are displayed in real-time on the GUI.

## Project Structure
```
src/
├── Main.java          // Entry point
├── gui/
│   ├── MainView.java  // GUI layout
│   ├── BoardView.java // Visual representation of the board
├── models/
│   ├── Piece.java     // Represents a piece
│   ├── Board.java     // Manages the board
├── solver/
│   ├── Solver.java    // Core solving logic
│   ├── TaskWorker.java // Individual thread tasks
└── utils/
    ├── FileHandler.java // Reading/writing configurations
    ├── Logger.java      // Logging thread activities
```

## Running the Project
1. Clone the repository.
2. Open the project in your favorite IDE.
3. Ensure JavaFX is configured in your build path.
4. Run `Main.java`.

## Examples
### Input:
```plaintext
4
2 3
111
101
3 2
11
10
10
```
Output:
```
1112
1412
3422
3442
```
Future Enhancements

    Add drag-and-drop functionality for piece placement.
    Extend support for larger grids.


## Authors

- [@3ab3z2](https://www.github.com/3ab3z2)



---
