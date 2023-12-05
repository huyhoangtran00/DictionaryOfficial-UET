package com.example.dictionaryofficial;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController extends BaseController implements Initializable {

    @FXML
    private Button flash_card;
    @FXML
    private Button frog;
    @FXML
    private Button multi;
    @FXML
    private ImageView img_1;
    @FXML
    private ImageView img_2;
    @FXML
    private ImageView img_3;



    public void PlayFlashCard(ActionEvent event) throws IOException {
        ManageScene.showScene(BaseController.getRoot(),BaseController.getStage(),BaseController.getScene(), event, "FlashCard.fxml");
    }

    public void PlayBlossomGame(ActionEvent event) throws IOException {
        ManageScene.showScene(BaseController.getRoot(), BaseController.getStage(), BaseController.getScene(), event, "BlossomGame.fxml");
    }
    public void  PlayMultiChoiceGame(ActionEvent event) throws IOException {
        ManageScene.showScene(BaseController.getRoot(), BaseController.getStage(), BaseController.getScene(), event, "MultiChoice.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        suggestLabel();

        flash_card.setOnMouseEntered(mouseEvent -> {
            img_1.setScaleX(1.4);
            img_1.setScaleY(1.4);
        });
        flash_card.setOnMouseExited(mouseEvent -> {
            img_1.setScaleX(1);
            img_1.setScaleY(1);
        });
        frog.setOnMouseEntered(mouseEvent -> {
            img_2.setScaleX(1.4);
            img_2.setScaleY(1.4);
        });
        frog.setOnMouseExited(mouseEvent -> {
            img_2.setScaleX(1);
            img_2.setScaleY(1);
        });
        multi.setOnMouseEntered(mouseEvent -> {
            img_3.setScaleX(1.4);
            img_3.setScaleY(1.4);
        });
        multi.setOnMouseExited(mouseEvent -> {
            img_3.setScaleX(1);
            img_3.setScaleY(1);
        });

        img_1.setOnMouseEntered(mouseEvent -> {
            img_1.setScaleX(1.4);
            img_1.setScaleY(1.4);
        });
        img_1.setOnMouseExited(mouseEvent -> {
            img_1.setScaleX(1);
            img_1.setScaleY(1);
        });
        img_2.setOnMouseEntered(mouseEvent -> {
            img_2.setScaleX(1.4);
            img_2.setScaleY(1.4);
        });
        img_2.setOnMouseExited(mouseEvent -> {
            img_2.setScaleX(1);
            img_2.setScaleY(1);
        });
        img_3.setOnMouseEntered(mouseEvent -> {
            img_3.setScaleX(1.4);
            img_3.setScaleY(1.4);
        });
        img_1.setOnMouseExited(mouseEvent -> {
            img_3.setScaleX(1);
            img_3.setScaleY(1);
        });
    }
}
