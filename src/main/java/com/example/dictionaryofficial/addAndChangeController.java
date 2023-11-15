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

public class addAndChangeController implements Initializable {
    @FXML
    private ComboBox<String> mode;
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
    //Switch Scene
    public void searchScene(ActionEvent event) throws IOException {

       ManageScene.showScene(BaseController.getRoot(),BaseController.getStage(),BaseController.getScene(),event,"IntoProgram.fxml");
    }

    public void gameScene(ActionEvent event) throws IOException {
        ManageScene.showScene(BaseController.getRoot(),BaseController.getStage(),BaseController.getScene(),event,"Game.fxml");

    }

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

    public void showSettingScene(ActionEvent event) throws IOException {
        ManageScene.showScene(BaseController.getRoot(),BaseController.getStage(),BaseController.getScene(), event, "Setting.fxml");
    }
    public void showTranslateScene(ActionEvent event) throws IOException {
        ManageScene.showScene(BaseController.getRoot(),BaseController.getStage(),BaseController.getScene(), event, "Translate.fxml");
    }

    public void checkExistingWord(ActionEvent event) throws IOException {
        notification.setVisible(true);
        if (mode.getValue().equals("Add")) {
            String addedWord = wordInput.getText();
            boolean isExist = DictionaryCommandline.isExist(addedWord, DBConnect.connectDB());
            if (!isExist) {
                notification.setText("Your word has not existed in Dictionary. You can add now!");
                notification.setTextFill(Color.GREEN);
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
        } else if (mode.getValue().equals("Edit")) {
            String addedWord = wordInput.getText();
            boolean isExist = DictionaryCommandline.isExist(addedWord, DBConnect.connectDB());
            if (isExist) {
                notification.setText("Your word has existed in Dictionary. You can edit now!");
                notification.setTextFill(Color.GREEN);
                pronunciationInput.setEditable(true);
                nounInput.setEditable(true);
                verbInput.setEditable(true);
                adjInput.setEditable(true);
                advInput.setEditable(true);
                exampleInput.setEditable(true);
            } else {
                notification.setText("Your word has not existed. " +
                        "You can add the word to the Dictionary first!");
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
    }

    public void checkExistingWord() {
        System.out.println(111);
        notification.setVisible(true);
        if (mode.getValue().equals("Add Words")) {
            String addedWord = wordInput.getText();
            boolean isExist = DictionaryCommandline.isExist(addedWord, DBConnect.connectDB());
            if (!isExist) {
                System.out.println("here");
                notification.setText("Your word has not existed in Dictionary. You can add now!");
                notification.setTextFill(Color.GREEN);
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
        } else if (mode.getValue().equals("Edit Words")) {
            String addedWord = wordInput.getText();
            boolean isExist = DictionaryCommandline.isExist(addedWord, DBConnect.connectDB());
            if (isExist) {
                notification.setText("Your word has existed in Dictionary. You can edit now!");
                notification.setTextFill(Color.GREEN);
                pronunciationInput.setEditable(true);
                nounInput.setEditable(true);
                verbInput.setEditable(true);
                adjInput.setEditable(true);
                advInput.setEditable(true);
                exampleInput.setEditable(true);
            } else {
                notification.setText("Your word has not existed. " +
                        "You can add the word to the Dictionary first!");
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
    }


    public void addWord(ActionEvent event) {
        String word = wordInput.getText();
        String pronunciation = pronunciationInput.getText();
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

        mode.setItems(FXCollections.observableArrayList(
                "Add Words",
                "Edit Words"
        ));
        mode.setValue("Add Words");
        settingFont();

        mode.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("Selected value: " + newValue);
                submitButton.setVisible(false);
                notification.setVisible(false);
                pronunciationInput.setEditable(false);
                nounInput.setEditable(false);
                verbInput.setEditable(false);
                adjInput.setEditable(false);
                advInput.setEditable(false);
                exampleInput.setEditable(false);
            }
        });

        wordInput.setOnAction(event -> {
            String searchWord = wordInput.getText();
            checkExistingWord();
        });
    }

}
