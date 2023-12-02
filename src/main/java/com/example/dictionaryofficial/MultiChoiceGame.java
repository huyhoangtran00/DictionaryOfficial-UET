package com.example.dictionaryofficial;

import com.example.APIGoogle.AudioGoogleAPI;
import com.example.Game.MultiChoice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MultiChoiceGame extends GameController implements Initializable {

    private Button[] ans = new Button[4];
    private int pivot;
    List<String> QndA;
   @FXML
    private Button ans_A;
    @FXML
    private Button ans_B;
    @FXML
    private Button ans_C;
    @FXML
    private Button ans_D;
    @FXML
    private Button reset_game;


    @FXML
    private Label ques_game;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    playGame();
    suggestLabel();
    }



    public void playGame() {
        initGame();
        setupButtonEvents();
    }

   public void setupButtonEvents() {
        ans[pivot].setOnAction(event -> handleCorrectAnswer());
         for (int i = 0; i < 4; i++) {
            if (i != pivot) {
                int wrong_key = i; //
                ans[i].setOnAction(event -> handleWrongAnswer(wrong_key));
            }
        }
    }

   public void handleCorrectAnswer() {

       ans[pivot].setStyle("-fx-background-color: green");
       Thread au = new Thread(() -> {
           AudioGoogleAPI audio = AudioGoogleAPI.getInstance();
           InputStream sound = null;
           try {
               sound = audio.getAudio("Correct", "en-UK");

           } catch (IOException e) {
               System.out.println("Please connect the internet !");
           }
           try {
               audio.play(sound);

           } catch (JavaLayerException e) {
               System.out.println("Please connect the internet !");
           }

       });
       au.start();

       reset_game.setVisible(true);


    }

    public void handleWrongAnswer(int answerIndex) {
        Thread au = new Thread(() -> {
            AudioGoogleAPI audio = AudioGoogleAPI.getInstance();
            InputStream sound = null;
            try {

                ans[answerIndex].setStyle("-fx-background-color: #EA5455");
                ans[pivot].setStyle("-fx-background-color: #349f34");
                sound = audio.getAudio("Wrong", "en-UK");

            } catch (IOException e) {
               System.out.println("Please connect the internet !");
            }
            try {
                audio.play(sound);
            } catch (JavaLayerException e) {
                System.out.println("Please connect the internet !");
            }

        });
        au.start();
        reset_game.setVisible(true);
    }

    public void initGame() {

        ans[0] = ans_A;
        ans[1] = ans_B;
        ans[2] = ans_C;
        ans[3] = ans_D;
        reset_game.setVisible(false);
        for(int i = 0 ; i < 4 ; i++) {
           ans[i].setStyle("-fx-background-color: #F3F3F3");
        }
        // Thiết lập lại câu hỏi
        QndA = new MultiChoice().genQuestion();
        String ques = QndA.get(0);
        ques_game.setText(ques);

        // Reset lại pivot và câu trả lời
        pivot = (int) (Math.random() * 4);
        System.out.println(pivot);


        ans[pivot].setText(QndA.get(1));

        int j = 2;
        for (int k = 0; k < 4; k++) {
            if (k != pivot) {
                ans[k].setText(QndA.get(j));
                j++;
            }
        }

        setupButtonEvents();
    }


    public void game_over_reset(ActionEvent event) throws  IOException{
        initGame();
    }

}
