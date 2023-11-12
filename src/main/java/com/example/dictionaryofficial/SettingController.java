package com.example.dictionaryofficial;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SettingController  implements Initializable {

    @FXML
    ComboBox<String> fontSize ;
    @FXML
    ComboBox<String> font;
    @FXML
    private Pane pane;

    private IntoProgramController intoProgramController;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setting();

    }


    public void buttonAccept(ActionEvent event) throws IOException {

        try{
            File file = new File ("database/setting.txt");
            FileWriter writer = new FileWriter(file);
            writer.write(font.getSelectionModel().getSelectedItem() + "\n" + fontSize.getSelectionModel().getSelectedItem() );
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }


    }

    public void showGameScene(ActionEvent event) throws IOException {
        ManageScene.showScene(BaseController.getRoot(),BaseController.getStage(),BaseController.getScene(),event,"Game.fxml");
    }

    public void showIntoProgramScene(ActionEvent event) throws IOException {
        ManageScene.showScene(BaseController.getRoot(),BaseController.getStage(),BaseController.getScene(),event,"IntoProgram.fxml");
    }

    public void showAddChangeScene(ActionEvent event) throws IOException {
        ManageScene.showScene(BaseController.getRoot(),BaseController.getStage(),BaseController.getScene(),event,"addAndChange.fxml");
    }


    public void logout (ActionEvent event) throws IOException {
        ManageScene.logout(BaseController.getStage(),pane);
    }

    public void setting()  {
       List<String> listFont = new ArrayList<>();
        try {
            File file = new File("database/setting.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                listFont.add(scanner.nextLine());

            }

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        font.getItems().addAll(Font.getFamilies());

        for(int i = 10 ; i <= 25 ; i++){
            fontSize.getItems().add(i+"");
        }
        font.setValue(listFont.get(0));
        fontSize.setValue(listFont.get(1));

    }


}
