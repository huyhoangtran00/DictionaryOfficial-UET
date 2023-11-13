package com.example.dictionaryofficial;

import com.example.Game.BlossomGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;

public class BlossomGameController implements Initializable {
    @FXML
    private Button letter1;
    @FXML
    private Button letter2;
    @FXML
    private Button letter3;
    @FXML
    private Button letter4;
    @FXML
    private Button letter5;
    @FXML
    private Button letter6;
    @FXML
    private Button letter7;
    @FXML
    private Button word1;
    @FXML
    private Button word2;
    @FXML
    private Button word3;
    @FXML
    private Button word4;
    @FXML
    private Button word5;
    @FXML
    private Button word6;
    @FXML
    private Button word7;
    @FXML
    private Button word8;
    @FXML
    private Button word9;
    @FXML
    private Label ans1;
    @FXML
    private Label ans2;
    @FXML
    private Label ans3;
    @FXML
    private Label ans4;
    @FXML
    private Label ans5;
    @FXML
    private Label ans6;
    @FXML
    private Label ans7;
    @FXML
    private Label ans8;
    @FXML
    private Label ans9;
    @FXML
    private Label score;
    @FXML
    private Label heart;
    @FXML
    private Label yourAns;
    @FXML
    private TextField ansField;
    @FXML
    private Button submitButton;
    @FXML
    private Button startButton;

    private static ArrayList<Button> letter;
    private static ArrayList<Button> word;
    private static ArrayList<Label> ans;

    public void initGame() {
        for (Button button : letter) {
            button.setVisible(false);
        }
        for (Button button : word) {
            button.setVisible(false);
        }
        for (Label label : ans) {
            label.setVisible(false);
        }

        yourAns.setVisible(false);
        ansField.setVisible(false);
        submitButton.setVisible(false);
        startButton.setVisible(true);
    }

    public void initArray() {
        letter.add(letter1);
        letter.add(letter2);
        letter.add(letter3);
        letter.add(letter4);
        letter.add(letter5);
        letter.add(letter6);
        letter.add(letter7);

        word.add(word1);
        word.add(word2);
        word.add(word3);
        word.add(word4);
        word.add(word5);
        word.add(word6);
        word.add(word7);
        word.add(word8);
        word.add(word9);

        ans.add(ans1);
        ans.add(ans2);
        ans.add(ans3);
        ans.add(ans4);
        ans.add(ans5);
        ans.add(ans6);
        ans.add(ans7);
        ans.add(ans8);
        ans.add(ans9);
    }

    public void startGame(ActionEvent event) {
        for (Button button : letter) {
            button.setVisible(true);
        }
        yourAns.setVisible(true);
        ansField.setVisible(true);
        submitButton.setVisible(true);
        startButton.setVisible(false);

        HashSet<Character> set = BlossomGame.UltiGame();
        int runner = 0;
        for (Character character : set) {
            String tmp = character + "";
            letter.get(runner++).setText(tmp);
        }
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        letter = new ArrayList<>();
        word = new ArrayList<>();
        ans = new ArrayList<>();
        initArray();
        initGame();
    }
}
