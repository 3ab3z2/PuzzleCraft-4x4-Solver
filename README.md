# Make A Square Game

## Overview
Make A Square Game is a puzzle game inspired by Tetris, where the objective is to fit various pieces into a 4x4 grid without any gaps. The game allows for automatic rotation and flipping of pieces to achieve the perfect square formation.

## Features
- User-friendly GUI built with JavaFX.
- Supports multiple game pieces with different shapes.
- Efficient solving algorithm utilizing multithreading for performance.
- Visual feedback for piece placement and grid status.

## Project Structure
```
Make_A_Square_Game
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── make_a_square_gui
│   │   │               ├── Board.java
│   │   │               ├── HelloController.java
│   │   │               ├── MakeASquare.java
│   │   │               ├── Paralleling.java
│   │   │               ├── Piece.java
│   │   │               ├── PiecesModel.java
│   │   │               └── constants.java
│   │   └── resources
│   │       └── com
│   │           └── example
│   │               └── make_a_square_gui
│   │                   └── hello-view.fxml
├── .vscode
│   ├── launch.json
│   ├── settings.json
│   └── tasks.json
├── .gitignore
├── pom.xml
├── README.md
└── docs
    ├── INSTALLATION.md
    └── USAGE.md
```

## Installation
1. Clone the repository:
   ```
   git clone https://github.com/3ab3z2/PuzzleCraft-4x4-Solver.git
   ```
2. Navigate to the project directory:
   ```
   cd Make_A_Square_Game
   ```
3. Ensure you have Maven installed. If not, download and install it from [Maven's official website](https://maven.apache.org/download.cgi).
4. Build the project using Maven:
   ```
   mvn clean install
   ```

## Usage
1. Run the application:
   ```
   mvn javafx:run
   ```
2. Follow the on-screen instructions to input the number of each piece type and start solving the puzzle.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.