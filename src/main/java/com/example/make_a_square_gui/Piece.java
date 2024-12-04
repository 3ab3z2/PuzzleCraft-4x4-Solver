package com.example.make_a_square_gui;

public class Piece {
    private int[][] piece;
    private int row;
    private int col;

    public Piece(int[][] piece) {
        this.piece = piece;
        this.row = piece.length;
        this.col = piece[0].length;
    }

    public int[][] rotate() {
        int[][] rotated = new int[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                rotated[j][row - 1 - i] = piece[i][j];
            }
        }
        return rotated;
    }

    public int[][] flip() {
        int[][] flipped = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                flipped[i][col - 1 - j] = piece[i][j];
            }
        }
        return flipped;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ret.append(piece[i][j]);
            }
            ret.append('\n');
        }
        return ret.toString();
    }
}