//package com.example.make_a_square_gui;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.TextField;
//
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.ResourceBundle;
//
//public class HelloController implements Initializable {
//    private PiecesModel piecesModel;
//    private int[][] finalBoard;
//
//    @FXML
//    private Button[][] array2DButton;
//    @FXML
//    private TextField textI;
//    @FXML
//    private TextField textJ;
//    @FXML
//    private TextField textL;
//    @FXML
//    private TextField textO;
//    @FXML
//    private TextField textS;
//    @FXML
//    private TextField textT;
//    @FXML
//    private TextField textZ;
//
//    private Map<Character, Integer> hashMap = new HashMap<>();
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        // Initialize the controller
//    }
//
//    private void initButtons() {
//        // Initialize the buttons
//    }
//
//    private void setHashMap() {
//        // Set the hash map with piece counts
//    }
//
//    public void solveProblem() {
//        setHashMap();
//        HashMap<Integer, int[][]> sendPieces = new HashMap<>();
//        int id = 0;
//        for (Map.Entry<Character, Integer> set : hashMap.entrySet()) {
//            int cnt = set.getValue();
//            while (cnt > 0) {
//                sendPieces.put(id++, piecesModel.retrievePiece(set.getKey()));
//                cnt--;
//            }
//        }
//        try {
//            finalBoard = MakeASquare.Square(sendPieces);
//            if (finalBoard == null) {
//                showAlert("No Solution Found", "Make A Square", "No Solution Found");
//            } else {
//                paintButton();
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void showAlert(String title, String header, String content) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION, content, ButtonType.OK);
//        alert.setTitle(title);
//        alert.setHeaderText(header);
//        alert.showAndWait();
//    }
//
//    private void paintButton() {
//        // Paint the buttons to show the solution
//    }
//}
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
