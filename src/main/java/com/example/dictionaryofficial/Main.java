package com.example.dictionaryofficial;

import com.example.commandLine.DBConnect;
import com.example.commandLine.Dictionary;
import com.example.commandLine.DictionaryCommandline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;



public class Main extends Application {

    IntoProgramController intoProgramController = new IntoProgramController();
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Base.fxml"));
            Scene scene = new Scene(root);
            scene.setFill(Color.CORNSILK);
            stage.setScene(scene);
            stage.setX(300);
            stage.setY(100);
            stage.setTitle("TripH Dictionary");
            stage.getIcons().add(new Image(new FileInputStream("src/main/resources/com/example/dictionaryofficial/Image/DicLogo_preview_rev_1.png")));
            stage.show();

            stage.setOnCloseRequest(event -> {
                try {
                    event.consume();
                    logout(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout(Stage stage) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logging out");
        alert.setHeaderText("Confirmation: Are you sure to log out?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            intoProgramController.saveHistory();
            System.out.println("Logout successfully!");
            stage.close();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        launch();
    }


}