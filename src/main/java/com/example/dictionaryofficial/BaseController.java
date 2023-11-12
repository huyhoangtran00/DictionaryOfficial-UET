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
    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane basePane;


    public void intoProgram(ActionEvent event) throws IOException {
        ManageScene.showScene(root, stage, scene, event, "IntoProgram.fxml");
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