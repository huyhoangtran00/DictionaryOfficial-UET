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

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void searchScene(ActionEvent event) throws IOException {
        ManageScene.showScene(root,stage,scene,event,"IntoProgram.fxml");
    }

    public void addScene(ActionEvent event) throws IOException {
        ManageScene.showScene(root, stage, scene, event, "addAndChange.fxml");
    }

    public void showSettingScene(ActionEvent event) throws IOException {
        ManageScene.showScene(root, stage, scene, event, "Setting.fxml");
    }

    public void PlayFlashCard(ActionEvent event) throws IOException {
        ManageScene.showScene(root, stage, scene, event, "FlashCard.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(1);
    }
}
