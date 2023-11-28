package com.example.dictionaryofficial;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseController {
    protected static Stage stage;
    protected static Scene scene;
    protected static Parent root;
    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane basePane;


    public void showSearchScene(ActionEvent event) throws IOException {
        ManageScene.showScene(root,stage, scene, event, "IntoProgram.fxml");
    }

    public void showAddScene(ActionEvent event) throws IOException {
        ManageScene.showScene(root,stage,scene,event,"addAndChange.fxml");
    }

    public void showSettingScene(ActionEvent event) throws IOException {
        ManageScene.showScene(root,stage,scene,event,"Setting.fxml");
    }

    public void showGameScene(ActionEvent event) throws IOException {
        ManageScene.showScene(root,stage,scene,event,"Game.fxml");
    }

    public void showTranslateScene(ActionEvent event) throws IOException {
        ManageScene.showScene(root,stage,scene,event,"Translate.fxml");
    }

    public void showHomeScene(ActionEvent event) throws IOException {
        ManageScene.showHomeScene(BaseController.getRoot(), BaseController.getStage(), BaseController.getScene(), event);
    }

    public static Stage getStage() {
        return stage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static Parent getRoot() {
        return root;
    }


}