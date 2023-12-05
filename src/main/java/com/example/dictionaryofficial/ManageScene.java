package com.example.dictionaryofficial;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import static com.example.dictionaryofficial.ManageScene.THEME.*;

public class ManageScene {
    /**
     * The fuction help to show the scene
     * @param root: Parent
     * @param stage: Stage
     * @param scene: Scene
     * @param event: ActionEvent
     * @param sourceScene: String ( name of file fxml which file you want to show scene)

     */

   public enum THEME {
       BASIC("basic"),
        PURPLE("purple");


        private final String basic;

        THEME(String basic) {
            this.basic = "basic";
        }

        public static List<String> getListTheme() {
            List<String> list_theme = new ArrayList<>();
            list_theme.add("basic");
            list_theme.add("purple");
            return list_theme;
        }


    }
    public static void showScene(Parent root , Stage stage, Scene scene, ActionEvent event , String sourceScene) throws IOException {

        sourceScene = ManageScene.changeSrc(ManageScene.typeTheme(),sourceScene);
        root = FXMLLoader.load(ManageScene.class.getResource(sourceScene));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TripH-dictionary");
        stage.show();
    }

    public static void showHomeScene(Parent root , Stage stage, Scene scene, ActionEvent event) throws IOException {
        root = FXMLLoader.load(ManageScene.class.getResource("Base.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("HHH-dictionary");
        stage.show();
    }

    /**
     * The fuction help to show status logout
     * @param stage: Stage
     * @param basePane : Pane
     */
    public static void logout(Stage stage, Pane basePane ) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logging out");
        alert.setHeaderText("Confirmation: Are you sure to log out?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) basePane.getScene().getWindow();
            System.out.println("Logout successfully!");
            stage.close();
        }
    }
    public static void setFont(TextField textField){
        Vector<String> settingFont = new Vector<>();
        try {
            File file = new File("database/setting.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                settingFont.add(scanner.nextLine());

            }

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        textField.setFont(new Font(settingFont.get(0),Double.parseDouble(settingFont.get(1)) ));
    }

    public static void setFont(TextArea textArea){

       Vector<String> settingFont = new Vector<>();
        try {
            File file = new File("database/setting.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                settingFont.add(scanner.nextLine());

            }

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        textArea.setFont(new Font(settingFont.get(0),Double.parseDouble(settingFont.get(1)) ));
    }

    public static THEME typeTheme() {

        Vector<String> setting = new Vector<>();
        try {
            File file = new File("database/setting.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                setting.add(scanner.nextLine());

            }

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return switch (setting.get(2)) {
            case "basic" -> BASIC;
            case "purple" -> PURPLE;
            default -> null;
        };


    }
    public static String changeSrc(THEME theme,String src) {
        switch (theme) {
            case BASIC :

               return "theme_basic/" + src;
            case PURPLE:
                return src;
            default:
                return src;

        }


    }

}
