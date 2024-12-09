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