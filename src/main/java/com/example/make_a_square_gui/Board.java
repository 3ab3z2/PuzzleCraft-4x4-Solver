package com.example.make_a_square_gui;

import java.util.Map;

public class Board {
    private final int[][] grid;
    public final int sizeX;
    public final int sizeY;
    private final Map<Integer, int[][]> pieces;

    public Board(Map<Integer, int[][]> pieces) {
        this.pieces = pieces;
        this.sizeX = 4; // Assuming a 4x4 grid
        this.sizeY = 4;
        this.grid = new int[sizeX][sizeY];
    }

    private boolean canPlacePiece(int[][] board, int[][] piece, int row, int col) {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if (piece[i][j] != 0) {
                    if (row + i >= board.length || col + j >= board[0].length || board[row + i][col + j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void placePiece(int[][] board, int[][] piece, int row, int col) {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if (piece[i][j] != 0) {
                    board[row + i][col + j] = piece[i][j];
                }
            }
        }
    }

    private void removePiece(int[][] board, int[][] piece, int row, int col) {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if (piece[i][j] != 0) {
                    board[row + i][col + j] = 0;
                }
            }
        }
    }

    public boolean isValidBoard(int[][] grid) {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (grid[i][j] != 0 && grid[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setGrid(int[][] copyGrid) {
        for (int i = 0; i < sizeX; i++) {
            System.arraycopy(copyGrid[i], 0, grid[i], 0, sizeY);
        }
    }

    public int[][] decompose(int mask) {
        int[][] copyGrid = new int[sizeX][sizeY];

        for (int i = 0; i < 4; i++) {
            int pieceIndex = (mask >> (i * 4)) & 15; // Extract 4 bits for each piece
            int[][] piece = pieces.get(pieceIndex);

            if (piece == null) continue; // If no piece matches, skip

            for (int rotation = 0; rotation < 4; rotation++) {
                int[][] rotatedPiece = rotatePiece(piece, rotation);

                for (boolean flip : new boolean[]{false, true}) {
                    int[][] currentPiece = flip ? flipPiece(rotatedPiece) : rotatedPiece;

                    for (int row = 0; row < sizeX; row++) {
                        for (int col = 0; col < sizeY; col++) {
                            if (canPlacePiece(copyGrid, currentPiece, row, col)) {
                                placePiece(copyGrid, currentPiece, row, col);
                                if (isValidBoard(copyGrid)) {
                                    return copyGrid;
                                }
                                removePiece(copyGrid, currentPiece, row, col);
                            }
                        }
                    }
                }
            }
        }
        return null; // Return null if no valid placement found
    }

    private int[][] rotatePiece(int[][] piece, int rotation) {
        int rows = piece.length;
        int cols = piece[0].length;
        int[][] rotated = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                switch (rotation) {
                    case 1 -> rotated[j][rows - 1 - i] = piece[i][j]; // 90 degrees
                    case 2 -> rotated[rows - 1 - i][cols - 1 - j] = piece[i][j]; // 180 degrees
                    case 3 -> rotated[cols - 1 - j][i] = piece[i][j]; // 270 degrees
                    default -> rotated[i][j] = piece[i][j]; // 0 degrees
                }
            }
        }
        return rotated;
    }

    private int[][] flipPiece(int[][] piece) {
        int rows = piece.length;
        int cols = piece[0].length;
        int[][] flipped = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                flipped[i][cols - 1 - j] = piece[i][j];
            }
        }
        return flipped;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                ret.append(grid[i][j]);
            }
            ret.append('\n');
        }
        return ret.toString();
    }
}
