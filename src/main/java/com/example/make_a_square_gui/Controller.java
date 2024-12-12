package com.example.make_a_square_gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //========================================================//
    @FXML
    private Button button0x0, button0x1, button0x2, button0x3,
            button1x0, button1x1, button1x2, button1x3,
            button2x0, button2x1, button2x2, button2x3,
            button3x0, button3x1, button3x2, button3x3;
    //=================================================//
    @FXML
    private TextField textT,textL,textS,textZ,textJ,textO,textI;
    //======================//
    @FXML
    private Button solveButton;
    //=========================//

    private HashMap<Integer, Integer> hashMap = new HashMap<>();
    private Button[][] array2DButton = new Button[4][4];
    private ArrayList<Button> buttonArrayList;
    private int[][] finalBoard;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initButtons();
        solveButton.setOnAction(event -> solveProblem());
    }

    void initButtons() {
        buttonArrayList = new ArrayList<>();

        buttonArrayList.add(button0x0);
        buttonArrayList.add(button0x1);
        buttonArrayList.add(button0x2);
        buttonArrayList.add(button0x3);
        buttonArrayList.add(button1x0);
        buttonArrayList.add(button1x1);
        buttonArrayList.add(button1x2);
        buttonArrayList.add(button1x3);
        buttonArrayList.add(button2x0);
        buttonArrayList.add(button2x1);
        buttonArrayList.add(button2x2);
        buttonArrayList.add(button2x3);
        buttonArrayList.add(button3x0);
        buttonArrayList.add(button3x1);
        buttonArrayList.add(button3x2);
        buttonArrayList.add(button3x3);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                array2DButton[i][j] = buttonArrayList.get(i * 4 + j);
            }
        }
    }

    private void setHashMap() {

        hashMap.put(0, parseInteger(textL));
        hashMap.put(1, parseInteger(textZ));
        hashMap.put(2, parseInteger(textI));
        hashMap.put(3, parseInteger(textJ));
        hashMap.put(4, parseInteger(textT));
        hashMap.put(5, parseInteger(textS));
        hashMap.put(6, parseInteger(textO));
    }

    private int parseInteger(TextField text) {
        return Integer.parseInt(text.getText());
    }

    private void solveProblem() {
        setHashMap();
        Grid grid = new Grid();
        PiecesModel piecesModel = new PiecesModel();
        Piece[] pieces = piecesModel.getAllPieces();
        int valuesSum = 0;

        for (int value : hashMap.values()) {
            if (value != 0) {
                valuesSum += value;
            }
        }

        System.out.println("Total sum of non-zero values: " + valuesSum);

        Piece[] selectedPieces = new Piece[valuesSum];
        int counter = 0;
        int[] valuesArray = new int[valuesSum];
        int index = 0;

        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            int value = entry.getKey();
            int occurrences = entry.getValue();

            for (int i = 0; i < occurrences; i++) {
                valuesArray[index++] = value;
            }
        }
        for (int i = 0; i < valuesSum; i++) {
            selectedPieces[i] = pieces[valuesArray[i]];
            System.out.println(selectedPieces[i]);
        }
        SolverThread solverThread = new SolverThread(grid, selectedPieces, 0);
        solverThread.start();
        try {
            solverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(solverThread.getMessage());
        if (solverThread.isSolutionFound()) {
            System.out.println("Final grid state:");
            int[][] temp = grid.printGridGui();
            updateGUI(temp);
        }
    }


    public void updateGUI(int[][] solvedBoard) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String buttonText = String.valueOf(solvedBoard[i][j]);
                System.out.println(array2DButton[i][j].getText());
                array2DButton[i][j].setText(buttonText);

                switch (solvedBoard[i][j]) {
                    case 1:
                        array2DButton[i][j].setStyle("-fx-background-color: red;");
                        break;
                    case 2:
                        array2DButton[i][j].setStyle("-fx-background-color: green;");
                        break;
                    case 3:
                        array2DButton[i][j].setStyle("-fx-background-color: blue;");
                        break;
                    case 4:
                        array2DButton[i][j].setStyle("-fx-background-color: yellow;");
                        break;
                    case 5:
                        array2DButton[i][j].setStyle("-fx-background-color: purple;");
                        break;
                    default:
                        array2DButton[i][j].setStyle("-fx-background-color: white;");
                }
            }
        }
    }

    public void clearGUI(int[][] solvedBoard){

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    array2DButton[i][j].setText(" ");
                    array2DButton[i][j].setStyle("-fx-background-color: white;");
                }
            }

    }
}
//        // Map pieces
//        hashMap.forEach((key, value) -> {
//            int[][] basePiece = piecesModel.retrievePiece(key);
//            for (int i = 0; i < value; i++) {
//                pieces.put(pieces.size(), basePiece);
//            }
//        });
//
//        int numberOfThreads = 10;
//        int sectionSize = 10000 / numberOfThreads;
//        Thread[] threads = new Thread[numberOfThreads];
//
//        for (int i = 0; i < numberOfThreads; i++) {
//            threads[i] = new Thread(new Paralleling(pieces, i, sectionSize), String.valueOf(i));
//            threads[i].start();
//        }
//
//        // Join threads
//        for (Thread thread : threads) {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // Update GUI
//        if (Paralleling.foundBoard) {
//            updateGUI(Paralleling.finallyBoard);
//        } else {
//            System.out.println("No solution found!");
//        }
//    }
//
//