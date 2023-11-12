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
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane basePane;


    public void intoProgram(ActionEvent event) throws IOException {
        String src = "IntoProgram.fxml";
        src = ManageScene.changeSrc(ManageScene.typeTheme(),src);
        root = FXMLLoader.load(getClass().getResource(src));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.setFill(Color.CORNSILK);
        stage.setScene(scene);
        stage.setX(300);
        stage.setY(100);
        stage.setTitle("HHH-dictionary");
        stage.show();
    }

    public void setting() {


    }

}