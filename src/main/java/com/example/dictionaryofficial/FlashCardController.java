package com.example.dictionaryofficial;

import com.example.cilent.FlashCard;
import com.example.commandLine.Word;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FlashCardController extends GameController implements Initializable {
    @FXML
    private StackPane FlashCardPane = new StackPane();
    @FXML
    private Label cardLabel = new Label();
    private boolean isFront = true;
    private String selected = "";
    private int cur = 1;

    private Word curWord;

    @FXML
    private Button prevButton = new Button();
    @FXML
    private Button nextButton = new Button();
    @FXML
    private Label curLabel = new Label();

    public List<Word> ListFlashCard;

    public void initialize() {
    }
    public void rotatePane() {

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.3), FlashCardPane);
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(180);
        rotateTransition.play();
        RotateTransition rotate = new RotateTransition(Duration.seconds(0.3), cardLabel);
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.setFromAngle(0);
        rotate.setToAngle(180);
        rotate.play();
        rotate.setOnFinished(e -> {
            if (isFront) {
                cardLabel.setStyle("-fx-font-size: 25px;-fx-font-weight: regular;");
                selected = cardLabel.getText();
                cardLabel.setText(ListFlashCard.get(cur - 1).getWordExplain());
            } else {
                cardLabel.setStyle("-fx-font-size: 25px;-fx-font-weight: bold;");
                cardLabel.setText(selected);
            }
            cardLabel.setVisible(true);
            isFront = !isFront;
        });
    }
    private void fadeTrans() {
        FadeTransition fade = new FadeTransition(Duration.seconds(0.5), FlashCardPane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }
    private void left_trans() {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400), FlashCardPane);
        translateTransition.setFromX(-FlashCardPane.getWidth());
        translateTransition.setToX(0);
        translateTransition.setAutoReverse(true);
        fadeTrans();
        if (!isFront) {
            cardLabel.setStyle("-fx-font-size: 25px;-fx-font-weight: regular;");
            selected = cardLabel.getText();
            cardLabel.setText(ListFlashCard.get(cur - 1).getWordExplain());
        } else {
            cardLabel.setStyle("-fx-font-size: 25px;-fx-font-weight: bold;");
            cardLabel.setText(selected);
        }
        translateTransition.play();
    }
    private void right_trans() {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400), FlashCardPane);
        translateTransition.setFromX(FlashCardPane.getWidth());
        translateTransition.setToX(0);
        translateTransition.setAutoReverse(true);
        fadeTrans();
        if (!isFront) {
            cardLabel.setStyle("-fx-font-size: 25px;-fx-font-weight: regular;");
            selected = cardLabel.getText();
            cardLabel.setText(ListFlashCard.get(cur - 1).getWordExplain());
        } else {
            cardLabel.setStyle("-fx-font-size: 25px;-fx-font-weight: bold;");
            cardLabel.setText(selected);
        }
        translateTransition.play();
    }

    public void showButton(int pt) {
        if (pt == 1) {
            prevButton.setVisible(false);
        } else {
            prevButton.setVisible(true);
        }

        if (pt == ListFlashCard.size()) {
            nextButton.setVisible(false);
        } else {
            nextButton.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        suggestLabel();
        ListFlashCard = FlashCard.getListFlashCard();
        cardLabel.setStyle("-fx-font-size: 25px;");
        cardLabel.setText(ListFlashCard.get(0).getWordTarget());
        curLabel.setText(Integer.toString(cur) + " / " + Integer.toString(ListFlashCard.size()));
        FlashCardPane.setOnMouseClicked(event -> {
            cardLabel.setVisible(false);
            rotatePane();
        });
        if (cur == 1) {
            prevButton.setVisible(false);
        } else {
            prevButton.setVisible(true);
        }

        nextButton.setOnMouseClicked(event -> {
            if (cur < ListFlashCard.size()) {
                isFront = true;
                right_trans();
                cur++;
                showButton(cur);
                selected = ListFlashCard.get(cur - 1).getWordTarget();
                cardLabel.setText(selected);
                curLabel.setText(Integer.toString(cur) + " / " + Integer.toString(ListFlashCard.size()));
            }
        });
        prevButton.setOnMouseClicked(event -> {
            if (cur > 1) {
                isFront = true;
                left_trans();
                cur--;
                showButton(cur);
                selected = ListFlashCard.get(cur - 1).getWordTarget();
                cardLabel.setText(selected);
                curLabel.setText(Integer.toString(cur) + " / " + Integer.toString(ListFlashCard.size()));
            }
        });
    }

}
