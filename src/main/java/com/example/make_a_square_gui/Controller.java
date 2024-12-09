package com.example.make_a_square_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
        System.out.println("Values in the HashMap:");
        hashMap.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
