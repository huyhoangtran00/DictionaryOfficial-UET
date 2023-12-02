package com.example.dictionaryofficial;

import com.example.APIGoogle.AudioGoogleAPI;
import com.example.Game.BlossomGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;

public class BlossomGameController extends GameController implements Initializable {
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
    private Button deleteButton;
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
    @FXML
    private AnchorPane notification;
    @FXML
    private Label scoreNoti;
    @FXML
    private Button restart;
    @FXML
    private ImageView star1;
    @FXML
    private ImageView star2;
    @FXML
    private ImageView star3;
    @FXML
    private AnchorPane instruction;

    private static ArrayList<Button> letter;
    private static ArrayList<Button> word;
    private static ArrayList<Label> ans;
    private static ArrayList<InputStream> soundList;
    private static Thread thread1;
    private static Thread thread2;
    private static Thread thread3;

    private final int MAX_HEART = 3;
    private int scoreInt;
    private int heartInt;

    private static final AudioGoogleAPI audio = AudioGoogleAPI.getInstance();
    private InputStream sound;
    private String userAns = "";

    public void initGame() {
        letter = new ArrayList<>();
        word = new ArrayList<>();
        ans = new ArrayList<>();
        soundList = new ArrayList<>();
        instruction.setVisible(true);


        initArray();

        for (Button button : letter) {
            button.setVisible(false);
        }
        for (Button button : word) {
            button.setVisible(false);
        }
        for (Label label : ans) {
            label.setVisible(false);
        }

        ansField.setText("");
        yourAns.setVisible(false);
        ansField.setVisible(false);
        submitButton.setVisible(false);
        deleteButton.setVisible(false);
        notification.setVisible(false);
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
        instruction.setVisible(false);
        scoreInt = 0;
        heartInt = MAX_HEART;
        heart.setText("x" + heartInt);
        score.setText(String.valueOf(scoreInt));
        BlossomGame.ListAnswer = new HashSet<>();
        for (Button button : letter) {
            button.setVisible(true);
        }
        yourAns.setVisible(true);
        ansField.setVisible(true);
        submitButton.setVisible(true);
        deleteButton.setVisible(true);
        startButton.setVisible(false);

        HashSet<Character> set = BlossomGame.UltiGame();
        int runner = 0;
        for (Character character : set) {
            String tmp = character + "";
            letter.get(runner++).setText(tmp);
        }
    }

    public void takeLet1(ActionEvent event) {
        userAns = userAns + letter1.getText();
        ansField.setText(userAns);
    }
    public void takeLet2(ActionEvent event) {
        userAns = userAns + letter2.getText();
        ansField.setText(userAns);
    }
    public void takeLet3(ActionEvent event) {
        userAns = userAns + letter3.getText();
        ansField.setText(userAns);
    }
    public void takeLet4(ActionEvent event) {
        userAns = userAns + letter4.getText();
        ansField.setText(userAns);
    }
    public void takeLet5(ActionEvent event) {
        userAns = userAns + letter5.getText();
        ansField.setText(userAns);
    }
    public void takeLet6(ActionEvent event) {
        userAns = userAns + letter6.getText();
        ansField.setText(userAns);
    }
    public void takeLet7(ActionEvent event) {
        userAns = userAns + letter7.getText();
        ansField.setText(userAns);
    }

    public void takeWord1(ActionEvent event) throws IOException, JavaLayerException {
        String tmp2 = ans1.getText();
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sound = audio.getAudio(tmp2, "en-US");
                    audio.play(sound);
                } catch (IOException | JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
    }
    public void takeWord2(ActionEvent event) throws IOException, JavaLayerException {
        String tmp2 = ans2.getText();
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sound = audio.getAudio(tmp2, "en-US");
                    audio.play(sound);
                } catch (IOException | JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
    }
    public void takeWord3(ActionEvent event) throws IOException, JavaLayerException {
        String tmp2 = ans3.getText();
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sound = audio.getAudio(tmp2, "en-US");
                    audio.play(sound);
                } catch (IOException | JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
    }
    public void takeWord4(ActionEvent event) throws IOException, JavaLayerException {
        String tmp2 = ans4.getText();
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sound = audio.getAudio(tmp2, "en-US");
                    audio.play(sound);
                } catch (IOException | JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
    }
    public void takeWord5(ActionEvent event) throws IOException, JavaLayerException {
        String tmp2 = ans5.getText();
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sound = audio.getAudio(tmp2, "en-US");
                    audio.play(sound);
                } catch (IOException | JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
    }
    public void takeWord6(ActionEvent event) throws IOException, JavaLayerException {
        String tmp2 = ans6.getText();
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sound = audio.getAudio(tmp2, "en-US");
                    audio.play(sound);
                } catch (IOException | JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
    }
    public void takeWord7(ActionEvent event) throws IOException, JavaLayerException {
        String tmp2 = ans7.getText();
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sound = audio.getAudio(tmp2, "en-US");
                    audio.play(sound);
                } catch (IOException | JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
    }
    public void takeWord8(ActionEvent event) throws IOException, JavaLayerException {
        String tmp2 = ans8.getText();
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sound = audio.getAudio(tmp2, "en-US");
                    audio.play(sound);
                } catch (IOException | JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
    }
    public void takeWord9(ActionEvent event) throws IOException, JavaLayerException {
        String tmp2 = ans9.getText();
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sound = audio.getAudio(tmp2, "en-US");
                    audio.play(sound);
                } catch (IOException | JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
    }

    public void deleteAChar(ActionEvent event) {
        if (!userAns.isEmpty()) {
            userAns = userAns.substring(0, userAns.length() - 1);
            ansField.setText(userAns);
        }
    }

    public void showNoti() {
        notification.setVisible(true);
        scoreNoti.setText(scoreInt + "/9");
        star1.setVisible(true);
        star2.setVisible(true);
        star3.setVisible(true);
        if (scoreInt < 8) {
            star3.setVisible(false);
        }
        if (scoreInt < 5) {
            star2.setVisible(false);
        }
        if (scoreInt < 2) {
            star1.setVisible(false);
        }

    }

    public void submit(ActionEvent event) throws IOException, JavaLayerException {
        String tmp = ansField.getText();
        int result = BlossomGame.TrueOrFalse(tmp);
        if (tmp.length() >= 3 && result == 2) {

            if (scoreInt == 8) {
                scoreInt++;
                showNoti();
            } else {
                thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            sound = audio.getAudio(tmp, "en-US");
                            audio.play(sound);
                        } catch (IOException | JavaLayerException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
            thread1.start();
            word.get(scoreInt).setVisible(true);
            ans.get(scoreInt).setText(ansField.getText());
            ans.get(scoreInt).setVisible(true);
            scoreInt++;
            score.setText(String.valueOf(scoreInt));
            ansField.setText("");
        } else if (result == 1) {
            if (heartInt > 0) heartInt--;
            heart.setText("x" + heartInt);
            if (heartInt == 0) {
                showNoti();
            } else {
                thread2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            sound = audio.getAudio("You have submitted this answer, try another one.", "en-US");
                            audio.play(sound);
                        } catch (IOException | JavaLayerException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                thread2.start();
            }

        } else if (result == 0 || tmp.length() < 3){
            if (heartInt > 0) heartInt--;
            heart.setText("x" + heartInt);
            if (heartInt == 0) {
                showNoti();
            } else {
                thread3 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            sound = audio.getAudio("Incorrect! Try it again", "en-US");
                            audio.play(sound);
                        } catch (IOException | JavaLayerException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                thread3.start();
            }
        }
        ansField.setText("");
        userAns = "";
    }

    public void restartGame(ActionEvent event) {
        initGame();
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initGame();
        suggestLabel();
    }



}
