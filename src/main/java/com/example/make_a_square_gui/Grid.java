package com.example.make_a_square_gui;
public class Grid {
    
    private int[][] grid;

    public Grid() {
        grid = new int[constants.gridRows][constants.gridCols];
    }
    Controller controller = new Controller();

    public boolean canPlacePiece(Piece piece, int x, int y) {
        int[][] pieceShape = piece.getPiece();
        int pieceRows = pieceShape.length;
        int pieceCols = pieceShape[0].length;

        // Check if the piece fits within the grid boundaries
        if (x + pieceRows > 4 || y + pieceCols > 4) {
            return false;
        }

        for (int i = 0; i < pieceRows; i++) {
            for (int j = 0; j < pieceCols; j++) {
                if (pieceShape[i][j] == 1 && grid[x + i][y + j] != 0) {
                    return false; // Collision detected
                }
            }
        }
        return true;
    }

    public void placePiece(Piece piece, int x, int y, int pieceNumber) {
        int[][] pieceShape = piece.getPiece(); 
        int pieceRows = pieceShape.length;
        int pieceCols = pieceShape[0].length;

        for (int i = 0; i < pieceRows; i++) {
            for (int j = 0; j < pieceCols; j++) {
                if (pieceShape[i][j] == 1) {
                    grid[x + i][y + j] = pieceNumber; 
                }
            }
        }
    }

    public int[][] getGridState() {
        int[][] copy = new int[constants.gridRows][constants.gridCols];
        for (int i = 0; i < constants.gridRows; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, constants.gridCols);
        }
        return copy;
    }

    public void printGrid() {
        int[][] temp = new int[4][4];
        for (int i = 0; i < constants.gridRows; i++) {
            for (int j = 0; j < constants.gridCols; j++) {
                System.out.print(grid[i][j] + " ");
                temp[i][j] = grid[i][j];
            }
            System.out.println();
        }
        printGridGui();
    }

    public int[][] printGridGui() {
        int[][] temp = new int[4][4];
        for (int i = 0; i < constants.gridRows; i++) {
            for (int j = 0; j < constants.gridCols; j++) {
                temp[i][j] = grid[i][j];
            }
        }
        return temp;
    }

    public boolean isFull() {
        for (int i = 0; i < constants.gridRows; i++) {
            for (int j = 0; j < constants.gridCols; j++) {
                if (grid[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void removePiece(Piece piece, int x, int y) {
        int[][] pieceShape = piece.getPiece();
        int pieceRows = pieceShape.length;
        int pieceCols = pieceShape[0].length;

        for (int i = 0; i < pieceRows; i++) {
            for (int j = 0; j < pieceCols; j++) {
                if (pieceShape[i][j] == 1) {
                    grid[x + i][y + j] = 0;
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : grid) {
            for (int cell : row) {
                sb.append(cell).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
