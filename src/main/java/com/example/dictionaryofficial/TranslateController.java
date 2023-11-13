package com.example.dictionaryofficial;

import com.example.APIGoogle.GoogleTransAPI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button confirm_button;
    @FXML
    private TextArea src_text;
    @FXML
    private TextArea des_text;
    @FXML
    private ComboBox<String> src_option;
    @FXML
    private ComboBox<String> des_option;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setting();
        initTranslate();
    }

    public void showIntoProgramScene(ActionEvent event) throws IOException {
        ManageScene.showScene(root,stage,scene,event,"IntoProgram.fxml");
    }

    public void showAddChangeScene(ActionEvent event) throws IOException {
        ManageScene.showScene(root,stage,scene,event,"addAndChange.fxml");
    }

    public void showSettingScene(ActionEvent event) throws IOException {
        ManageScene.showScene(root,stage,scene,event,"Setting.fxml");
    }

    public void initTranslate() {
        src_option.getItems().add("English");
        src_option.getItems().add("Vietnamese");
        src_option.getItems().add("Phát hiện ngôn ngữ");

        des_option.getItems().add("English");
        des_option.getItems().add("Vietnamese");

        src_option.setValue("English");
        des_option.setValue("Vietnamese");

    }


    public void translate_language(ActionEvent event) throws IOException {
        String res = GoogleTransAPI.translate(src_text.getText(),
                switch_language(src_option.getSelectionModel().getSelectedItem()),
                switch_language(des_option.getSelectionModel().getSelectedItem()));
        res = outputTextFormatted(res);
        des_text.setText(res);

    }

    public GoogleTransAPI.LANGUAGE switch_language(String lan) {
        switch (lan) {
            case "English" :
                return GoogleTransAPI.LANGUAGE.ENGLISH;

            case "Vietnamese":
                return GoogleTransAPI.LANGUAGE.VIETNAMESE;

            case "Phát hiện ngôn ngữ":
                return GoogleTransAPI.LANGUAGE.AUTO;
            default:
                break;
        }
        return null;
    }

    public String outputTextFormatted(String text) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c) || c == ',' || c == ' ') {
                result.append(c);
            }
        }
        String[] ans = result.toString().split(",");
        return ans[0];
    }


    public void setting () {
        ManageScene.setFont(src_text);
        ManageScene.setFont(des_text);
    }
}
