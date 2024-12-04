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

    private int[][] getPieces(int numericState) {
        return pieces.get(numericState);
    }

    private boolean isValidSequence(int[][] sequence) {
        for (int i = 0; i < sequence.length; i++) {
            for (int j = 0; j < sequence[0].length; j++) {
                if (sequence[i][j] != 0 && sequence[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] rotate(int moveId, int[][] piece) {
        Piece p = new Piece(piece);
        for (int i = 0; i < moveId; i++) {
            piece = p.rotate();
        }
        return piece;
    }

    private int[] firstEmptyCellInBoard(int[][] cells) {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (cells[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private int firstFullCellInPiece(int[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j] == 1) {
                    return i * cells[0].length + j;
                }
            }
        }
        return -1;
    }

    private int[][] boardState(int numericState) {
        int[][] state = new int[sizeX][sizeY];
        int[][] piece = getPieces(numericState);
        if (piece != null) {
            for (int i = 0; i < piece.length; i++) {
                for (int j = 0; j < piece[0].length; j++) {
                    state[i][j] = piece[i][j];
                }
            }
        }
        return state;
    }

    public int[][] decompose(int numericState) {
        int[][] state = boardState(numericState);
        int[][] decomposed = new int[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                decomposed[i][j] = state[i][j];
            }
        }
        return decomposed;
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