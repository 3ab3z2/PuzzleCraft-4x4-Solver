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
        setGrid(copyGrid);

        for (int i = 0; i < 4; i++) {
            int pieceIndex = (mask >> (i * 4)) & 15;
            int[][] piece = pieces.get(pieceIndex);

            Piece pieceObject = new Piece(piece);
            for (int rotation = 0; rotation < 4; rotation++) {
                int[][] rotatedPiece = pieceObject.rotate();
                for (boolean flip : new boolean[]{false, true}) {
                    int[][] currentPiece = flip ? new Piece(rotatedPiece).flip() : rotatedPiece;
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
        return null;
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