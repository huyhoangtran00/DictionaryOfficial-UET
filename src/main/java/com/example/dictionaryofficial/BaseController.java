package com.example.dictionaryofficial;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BaseController {
    protected static Stage stage;
    protected static Scene scene;
    protected static Parent root;
    @FXML
    protected Label search_label;
    @FXML
    protected Label edit_label;
    @FXML
    protected Label studying_label;
    @FXML
    protected Label gg_label;
    @FXML
    protected Label setting_label;
    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane basePane;

    @FXML
    protected Button search_button;
    @FXML
    protected Button edit_button;
    @FXML
    protected Button game_button;
    @FXML
    protected Button translate_button;
    @FXML
    protected Button setting_button;


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

    public void suggestLabelVisible(Button button, Label label) {
        button.setOnMouseEntered(event -> {
            label.setVisible(true);
        });

        // Đăng ký sự kiện MouseExited để xác định khi con trỏ chuột rời khỏi button
        button.setOnMouseExited(event -> {
           label.setVisible(false);
        });
    }

    public void suggestLabel() {
        search_label.setVisible(false);
        edit_label.setVisible(false);
        studying_label.setVisible(false);
        gg_label.setVisible(false);
        setting_label.setVisible(false);
        suggestLabelVisible(search_button,search_label);
        suggestLabelVisible(edit_button,edit_label);
        suggestLabelVisible(game_button,studying_label);
        suggestLabelVisible(translate_button,gg_label);
        suggestLabelVisible(setting_button,setting_label);
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