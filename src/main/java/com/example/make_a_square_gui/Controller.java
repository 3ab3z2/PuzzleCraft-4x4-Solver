package com.example.make_a_square_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField textT;
    @FXML
    private TextField textL;
    @FXML
    private TextField textS;
    @FXML
    private TextField textO;
    @FXML
    private TextField textZ;
    @FXML
    private TextField textJ;
    @FXML
    private Button solveButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        solveButton.setOnAction(event -> solveProblem());
    }

    private HashMap<Character, Integer> hashMap = new HashMap<>();

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
