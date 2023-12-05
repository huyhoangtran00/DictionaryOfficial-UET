package com.example.dictionaryofficial;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SettingController extends BaseController implements Initializable {

    @FXML
    ComboBox<String> fontSize ;
    @FXML
    ComboBox<String> font;

    @FXML
    ComboBox<String> theme;
    @FXML
    private Pane pane;
    @FXML
    private Label member1;
    @FXML
    private Label member2;
    @FXML
    private Label member3;

    List<String> list_theme = new ArrayList<>();

    private IntoProgramController intoProgramController;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        suggestLabel();
        setting();

        member1.setOnMouseClicked((event) -> {
            try {
                Desktop.getDesktop().browse(URI.create("https://github.com/huyhoangtran00"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        member2.setOnMouseClicked((event) -> {
            try {
                Desktop.getDesktop().browse(URI.create("https://github.com/Lhhiep-maxcode"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        member3.setOnMouseClicked((event) -> {
            try {
                Desktop.getDesktop().browse(URI.create("https://github.com/BlackRose484"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public void buttonAccept(ActionEvent event) throws IOException {

        try{
            File file = new File ("database/setting.txt");
            FileWriter writer = new FileWriter(file);
            writer.write(font.getSelectionModel().getSelectedItem() +
                    "\n" +
                    fontSize.getSelectionModel().getSelectedItem() +
                     "\n" +
                    theme.getSelectionModel().getSelectedItem());
            writer.close();
            ManageScene.showScene(BaseController.getRoot(),BaseController.getStage(),BaseController.getScene(),event,"Setting.fxml");
        }
        catch (IOException e){
            e.printStackTrace();
        }


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
        list_theme.add("basic");
        list_theme.add("purple");


        theme.getItems().addAll(ManageScene.THEME.getListTheme());
        font.setValue(listFont.get(0));
        fontSize.setValue(listFont.get(1));
        theme.setValue(listFont.get(2));


    }


}
