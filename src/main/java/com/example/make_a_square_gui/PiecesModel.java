package com.example.make_a_square_gui;

public class PiecesModel {
    private int id;
    private int[][] matrix;
    private int count;
    private String textField;
    private static int[][][] pieces;

    public PiecesModel() {
        pieces = new int[7][][];
        pieces[0] = new int[][]{{1, 0}, {1, 0}, {1, 1}};
        pieces[1] = new int[][]{{1, 1, 0}, {0, 1, 1}};
        pieces[2] = new int[][]{{1}, {1}, {1}, {1}};
        pieces[3] = new int[][]{{0, 1}, {0, 1}, {1, 1}};
        pieces[4] = new int[][]{{1, 1, 1}, {0, 1, 0}, {0, 1, 0}};
        pieces[5] = new int[][]{{0, 1, 1}, {0, 1, 0}, {1, 1, 0}};
        pieces[6] = new int[][]{{1, 1}, {1, 1}};
    }

    public int[][] retrievePiece(Character character) {
        int index = characterToIndex(character);
        return index >= 0 ? pieces[index] : null;
    }

    private int characterToIndex(Character character) {
        switch (character) {
            case 'L': return 0;
            case 'Z': return 1;
            case 'I': return 2;
            case 'J': return 3;
            case 'T': return 4;
            case 'S': return 5;
            case 'O': return 6;
            default: return -1;
        }
    }
}