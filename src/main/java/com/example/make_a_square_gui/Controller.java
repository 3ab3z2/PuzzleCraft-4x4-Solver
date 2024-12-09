package com.example.make_a_square_gui;

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
    private TextField textT,textL,textS,textZ,textJ,textO;
    //======================//
    @FXML
    private Button solveButton;
    //=========================//

    private HashMap<Character, Integer> hashMap = new HashMap<>();
    private Button[][] array2DButton = new Button[4][4];
    private ArrayList<Button> buttonArrayList;
    private int[][] finalBoard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        solveButton.setOnAction(event -> solveProblem());
        initButtons();
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
        hashMap.put('T', parseInteger(textT));
        hashMap.put('L', parseInteger(textL));
        hashMap.put('S', parseInteger(textS));
        hashMap.put('O', parseInteger(textO));
        hashMap.put('Z', parseInteger(textZ));
        hashMap.put('J', parseInteger(textJ));
    }

    private int parseInteger(TextField text) {
        return Integer.parseInt(text.getText());
    }

    private void solveProblem() {
        setHashMap();
        PiecesModel piecesModel = new PiecesModel();
        Map<Integer, int[][]> pieces = new HashMap<>();

        // Map pieces
        hashMap.forEach((key, value) -> {
            int[][] basePiece = piecesModel.retrievePiece(key);
            for (int i = 0; i < value; i++) {
                pieces.put(pieces.size(), basePiece);
            }
        });

        int numberOfThreads = 10;
        int sectionSize = 10000 / numberOfThreads;
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(new Paralleling(pieces, i, sectionSize), String.valueOf(i));
            threads[i].start();
        }

        // Join threads
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Update GUI
        if (Paralleling.foundBoard) {
            updateGUI(Paralleling.finallyBoard);
        } else {
            System.out.println("No solution found!");
        }
    }


    private void updateGUI(int[][] solvedBoard) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String buttonText = solvedBoard[i][j] == 0 ? "" : String.valueOf(solvedBoard[i][j]);
                array2DButton[i][j].setText(buttonText);
                array2DButton[i][j].setStyle(solvedBoard[i][j] == 0 ? "-fx-background-color: white;" : "-fx-background-color: lightgreen;");
            }
        }
    }


}
