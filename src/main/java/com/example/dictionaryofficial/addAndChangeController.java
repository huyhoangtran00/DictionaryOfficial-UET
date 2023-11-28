package com.example.dictionaryofficial;

import com.example.commandLine.DBConnect;
import com.example.commandLine.DictionaryCommandline;
import com.example.commandLine.DictionaryManagement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Vector;

public class addAndChangeController extends BaseController implements Initializable {
    @FXML
    private TextField wordInput;
    @FXML
    private TextField pronunciationInput;
    @FXML
    private TextArea nounInput;
    @FXML
    private TextArea verbInput;
    @FXML
    private TextArea adjInput;
    @FXML
    private TextArea advInput;
    @FXML
    private TextArea exampleInput;
    @FXML
    private Label notification;
    @FXML
    private Button search_button;
    @FXML
    private Button edit_button;
    @FXML
    private Button game_button;
    @FXML
    private Button setting_button;
    @FXML
    private Button check;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button submitButton;

    private Vector<String> settingFont;

//    Connection connection = DBConnect.connectDB();

    //-----------------------------------------------------------------------------------------------------------//



    public void settingFont() {
        settingFont = new Vector<>();
        try {
            File file = new File("database/setting.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                settingFont.add(scanner.nextLine());

            }

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ManageScene.setFont(wordInput);
        ManageScene.setFont(pronunciationInput);
        ManageScene.setFont(nounInput);
        ManageScene.setFont(verbInput);
        ManageScene.setFont(adjInput);
        ManageScene.setFont(advInput);
        ManageScene.setFont(exampleInput);
    }


    public void checkExistingWord(ActionEvent event) throws IOException {
        notification.setVisible(true);
        String addedWord = wordInput.getText();
        boolean isExist = DictionaryCommandline.isExist(addedWord, DBConnect.connectDB());
        if (!isExist) {
            notification.setText("Your word has not existed in Dictionary. You can add now!");
            notification.setTextFill(Color.GREEN);
            wordInput.setEditable(false);
            submitButton.setVisible(true);
            pronunciationInput.setEditable(true);
            nounInput.setEditable(true);
            verbInput.setEditable(true);
            adjInput.setEditable(true);
            advInput.setEditable(true);
            exampleInput.setEditable(true);
        } else {
            notification.setText("Your word has existed in Dictionary. " +
                    "You can edit the word instead of adding again!");
            notification.setTextFill(Color.RED);
            submitButton.setVisible(false);
            pronunciationInput.setEditable(false);
            nounInput.setEditable(false);
            verbInput.setEditable(false);
            adjInput.setEditable(false);
            advInput.setEditable(false);
            exampleInput.setEditable(false);
        }

    }

    public void checkExistingWord() {
        notification.setVisible(true);
        String addedWord = wordInput.getText();
        boolean isExist = DictionaryCommandline.isExist(addedWord, DBConnect.connectDB());
        if (!isExist) {
            System.out.println("here");
            notification.setText("Your word has not existed in Dictionary. You can add now!");
            notification.setTextFill(Color.GREEN);
            wordInput.setEditable(false);
            submitButton.setVisible(true);
            pronunciationInput.setEditable(true);
            nounInput.setEditable(true);
            verbInput.setEditable(true);
            adjInput.setEditable(true);
            advInput.setEditable(true);
            exampleInput.setEditable(true);
        } else {
            notification.setText("Your word has existed in Dictionary. " +
                    "You can edit the word instead of adding again!");
            notification.setTextFill(Color.RED);
            submitButton.setVisible(false);
            pronunciationInput.setEditable(false);
            nounInput.setEditable(false);
            verbInput.setEditable(false);
            adjInput.setEditable(false);
            advInput.setEditable(false);
            exampleInput.setEditable(false);
        }
    }


    public void addWord(ActionEvent event) {
        String word = wordInput.getText();
        String pronunciation = "/" + pronunciationInput.getText() + "/";
        HashMap<String, String> typeMeaning = new HashMap<>();
        typeMeaning.put("Danh từ", nounInput.getText());
        typeMeaning.put("Động từ", verbInput.getText());
        typeMeaning.put("Tính từ", adjInput.getText());
        typeMeaning.put("Trạng từ", advInput.getText());
        String example = exampleInput.getText();
        boolean isOk = false;
        isOk = DictionaryManagement.insertFromFront(word, pronunciation, typeMeaning, example, DBConnect.connectDB());
        if (isOk) {
            notification.setText("Added Successfully!");
            submitButton.setVisible(false);
        } else {
            notification.setText("Added Failed! Please add again");
            submitButton.setVisible(false);
        }
        wordInput.setEditable(true);
        pronunciationInput.setText("");
        nounInput.setText("");
        verbInput.setText("");
        adjInput.setText("");
        advInput.setText("");
        exampleInput.setText("");

        submitButton.setVisible(false);
        pronunciationInput.setEditable(false);
        nounInput.setEditable(false);
        verbInput.setEditable(false);
        adjInput.setEditable(false);
        advInput.setEditable(false);
        exampleInput.setEditable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        submitButton.setVisible(false);
        notification.setVisible(false);
        pronunciationInput.setEditable(false);
        nounInput.setEditable(false);
        verbInput.setEditable(false);
        adjInput.setEditable(false);
        advInput.setEditable(false);
        exampleInput.setEditable(false);


        settingFont();

        wordInput.setOnAction(event -> {
            String searchWord = wordInput.getText();
            checkExistingWord();
        });
    }
}
