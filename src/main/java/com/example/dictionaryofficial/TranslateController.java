package com.example.dictionaryofficial;

import com.example.APIGoogle.AudioGoogleAPI;
import com.example.APIGoogle.GoogleTransAPI;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController extends BaseController implements Initializable  {

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
    @FXML
    private Button switch_button;
    @FXML
    private Button src_speaker;
    @FXML
    private Button des_speaker;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setting();
        initTranslate();
        src_text.textProperty().addListener((observable, oldValue, newValue) -> {

                Task<String> task = new Task<String>() {
                    @Override
                    protected String call() throws Exception {
                        String res = "";
                        if(src_text.getText().isEmpty()) {
                            res = "";
                        }
                        else {
                           res = GoogleTransAPI.translate(src_text.getText(),
                                    switch_language(src_option.getSelectionModel().getSelectedItem()),
                                    switch_language(des_option.getSelectionModel().getSelectedItem()));
                            res = outputTextFormatted(res);
                        }
                        return res;
                    }
                };
                // ket thuc va hien thi
                task.setOnSucceeded(event -> {

                    javafx.application.Platform.runLater(() -> {
                        if(src_text.getText().isEmpty()) {
                            javafx.application.Platform.runLater(() -> des_text.setText(""));
                        }
                        else {
                            String result = task.getValue();
                            javafx.application.Platform.runLater(() -> des_text.setText(result));
                        }
                    });
                });
                // Chạy Task trên một luồng mới
                new Thread(task).start();

            });

    }


    public void initTranslate() {
        suggestLabel();
        src_option.getItems().add("English");
        src_option.getItems().add("Vietnamese");
        src_option.getItems().add("Japanese");
        src_option.getItems().add("Chinese");
        src_option.getItems().add("French");
        src_option.getItems().add("Spanish");
        src_option.getItems().add("German");
        src_option.getItems().add("Russian");
        src_option.getItems().add("Arabic");
        src_option.getItems().add("Korean");
        src_option.getItems().add("Portuguese");
        src_option.getItems().add("Phát hiện ngôn ngữ");

        des_option.getItems().add("English");
        des_option.getItems().add("Vietnamese");
        des_option.getItems().add("English");
        des_option.getItems().add("Vietnamese");
        des_option.getItems().add("Japanese");
        des_option.getItems().add("Chinese");
        des_option.getItems().add("French");
        des_option.getItems().add("Spanish");
        des_option.getItems().add("German");
        des_option.getItems().add("Russian");
        des_option.getItems().add("Arabic");
        des_option.getItems().add("Korean");
        des_option.getItems().add("Portuguese");



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
            case "Japanese" :
                return GoogleTransAPI.LANGUAGE.JAPANESE;
            case "Chinese" :
                return GoogleTransAPI.LANGUAGE.CHINESE;
            case "French" :
                return GoogleTransAPI.LANGUAGE.FRENCH;
            case "Spanish":
                return GoogleTransAPI.LANGUAGE.SPANISH;
            case "German" :
                return GoogleTransAPI.LANGUAGE.GERMAN;
            case "Russian":
                return GoogleTransAPI.LANGUAGE.RUSSIAN;
            case "Arabic":
                return GoogleTransAPI.LANGUAGE.ARABIC;
            case "Korean":
                return GoogleTransAPI.LANGUAGE.KOREAN;
            case "Portuguese":
                return GoogleTransAPI.LANGUAGE.PORTUGUESE;
            default:
                break;
        }
        return null;
    }

    public String outputTextFormatted(String text) {
        if(text == "" || text == null) {
            return "";
        }
        String result = "";
        if(text.charAt(0) == '[' && text.charAt(text.length()-1) == ']') {
            text = text.substring(1, text.length()-1);
            if(text.charAt(0) == '[' && text.charAt(text.length()-1) == ']' && text.charAt(text.length()-4) == ','){
                text = text.substring(1, text.length()-4);
            }
        }
        text = text.replace("\\n", "\n");
        return text.trim();
    }


    public void setting () {
        ManageScene.setFont(src_text);
        ManageScene.setFont(des_text);
    }

    public void switch_text_and_language(ActionEvent event) throws  IOException {



     //switch_language
        String temp_lan = src_option.getSelectionModel().getSelectedItem();
        src_option.setValue(des_option.getSelectionModel().getSelectedItem());
        des_option.setValue(temp_lan);

        //switch_text
        String temp_src = src_text.getText();
        src_text.setText(des_text.getText());
        des_text.setText(temp_src);


    }

    public void speaker_src_text(ActionEvent events) throws IOException {
        Thread au = new Thread(()->{
            AudioGoogleAPI audio = AudioGoogleAPI.getInstance();
            InputStream sound = null;
            try {
                sound = audio.getAudio(src_text.getText(),switch_language(src_option.getValue()).toString());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                audio.play(sound);
            } catch (JavaLayerException e) {
                throw new RuntimeException(e);
            }
        });
        au.start();
    }

    public void speaker_des_text(ActionEvent events) throws IOException {
        Thread au = new Thread(()->{
            AudioGoogleAPI audio = AudioGoogleAPI.getInstance();
            InputStream sound = null;
            try {
                sound = audio.getAudio(des_text.getText(), switch_language(des_option.getValue()).toString());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                audio.play(sound);
            } catch (JavaLayerException e) {
                throw new RuntimeException(e);
            }
        });
        au.start();
    }

}
