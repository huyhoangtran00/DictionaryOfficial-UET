package com.example.dictionaryofficial;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {


    public void searchScene(ActionEvent event) throws IOException {
        ManageScene.showScene(BaseController.getRoot(),BaseController.getStage(),BaseController.getScene(),event,"IntoProgram.fxml");
    }

    public void addScene(ActionEvent event) throws IOException {
        ManageScene.showScene(BaseController.getRoot(),BaseController.getStage(),BaseController.getScene(), event, "addAndChange.fxml");
    }

    public void showSettingScene(ActionEvent event) throws IOException {
        ManageScene.showScene(BaseController.getRoot(),BaseController.getStage(),BaseController.getScene(), event, "Setting.fxml");
    }

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
        System.out.println(1);
    }
}
