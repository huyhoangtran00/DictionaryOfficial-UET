package com.example.dictionaryofficial;

import com.example.APIGoogle.AudioGoogleAPI;
import com.example.APIGoogle.GoogleTransAPI;
import com.example.AutoCorrection.AutoCorrect;
import com.example.cilent.FavouriteWord;
import com.example.commandLine.DBConnect;
import com.example.commandLine.DictionaryCommandline;
import com.example.commandLine.DictionaryManagement;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class IntoProgramController extends BaseController implements Initializable {

    private String searchWord;
    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane basePane;
    @FXML
    private TextField searchField;
    @FXML
    private ListView<String> suggestList;
    @FXML
    private Button searchBar;
    @FXML
    private Button search_button;
    @FXML
    private Button edit_button;
    @FXML
    private Button game_button;
    @FXML
    private Button setting_button;
    @FXML
    private Button speaker_button_1;
    @FXML
    private Button speaker_button_2;
    @FXML
    private Button favour_button;
    @FXML
    private ImageView favour_star;
    @FXML
    private ImageView border_star;
    @FXML
    private Label taskbar;
    @FXML
    private WebView searchResult;
    @FXML
    private ListView<String> historyList;
    @FXML
    private Button EditButton;
    @FXML
    private HTMLEditor editField;
    @FXML
    private Button submitEditButton;
    @FXML
    private Button correctionButton;

    private static ArrayList<String> historyContainer = new ArrayList<>();
    private Vector<String> settingFont ;
    public static TextField static_searchField ;

    private final String style = "<style>\n" +
            "        h1 {\n" +
            "            color: red;\n" +
            "            font-weight: 800;\n" +
            "        }\n" +
            "        h3 i {\n" +
            "            color: green;\n" +
            "        }\n" +
            "        h2 {\n" +
            "            color: rgb(0, 213, 255);\n" +
            "            font-weight: 700;\n" +
            "        }\n" +
            "        ul > li {\n" +
            "            font-weight: 700;\n" +
            "        }\n" +
            "\n" +
            "        ol > li {\n" +
            "            font-weight: 700;\n" +
            "        }\n" +
            "        ol > li > ul > li {\n" +
            "            font-style: italic;\n" +
            "            font-weight: 400;\n" +
            "        }\n" +
            "        \n" +
            "        ul > li > ul > li {\n" +
            "            font-weight: 600;\n" +
            "        }\n" +
            "\n" +
            "        ul > li > ul > li > ul ,i {\n" +
            "            font-style: italic;\n" +
            "            font-weight: 400;\n" +
            "        }\n" +
            "    </style>";
    //-----------------------------------------------------------------------------------------------------//
    public void setFont(String font, double fontSize) {
        searchField.setFont(new Font(font,fontSize));
    }

    public void removeWord() {
        searchWord = searchField.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete a word");
        alert.setHeaderText("Confirmation: Are you sure to delete this word?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            boolean check = DictionaryManagement.removeFromFront(searchWord, DBConnect.connectDB());
            searchField.setText("");
            if (check) {
                searchResult.getEngine().loadContent("Delete Successfully!");
            } else {
                searchResult.getEngine().loadContent("Some errors have occurred!");
            }
        }
    }

    public void logout(ActionEvent event) throws IOException {
        ManageScene.logout(BaseController.getStage(),basePane);
    }


    /* click button to start search */
    public void resultProcessing() {
        correctionButton.setVisible(false);
        EditButton.setVisible(false);
        if (DictionaryCommandline.isExist(searchWord, DBConnect.connectDB())) {
            EditButton.setVisible(true);
            favour_button.setVisible(true);
            if (FavouriteWord.IsFavour(searchWord)) {
                favour_button.setGraphic(favour_star);
                favour_star.setVisible(true);
                border_star.setVisible(false);
            } else {
                favour_button.setGraphic(border_star);
                border_star.setVisible(true);
                favour_star.setVisible(false);
            }
        } else {
            //suggest word
            correctionButton.setVisible(true);
            searchWord = AutoCorrect.closestWord(searchWord);
            correctionButton.setText(searchWord);
        }
    }

    public void clickToSearch(ActionEvent event) throws IOException {
        searchWord = searchField.getText();

        addToHistory(searchWord);
        favour_button.setVisible(false);

        String result = style + DictionaryCommandline.getWord(searchWord, DBConnect.connectDB());
        resultProcessing();

        searchResult.getEngine().loadContent(result);

        suggestList.setVisible(false);
    }

    public void searchBySuggestion(ActionEvent event) throws IOException {
        searchField.setText(searchWord);

        addToHistory(searchWord);
        favour_button.setVisible(false);

        String result = style + DictionaryCommandline.getWord(searchWord, DBConnect.connectDB());
        resultProcessing();

        searchResult.getEngine().loadContent(result);

        suggestList.setVisible(false);
    }


    public void initHistory() throws IOException {
        File file = new File("database/history.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        historyContainer = new ArrayList<>();
        String word = "";
        while ((word = br.readLine()) != null) {
            historyList.getItems().add(word);
            historyContainer.add(word);
        }
        br.close();
    }

    public void saveHistory() throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter("database/history.txt");
            for (String s : historyContainer) {
                System.out.println(s);
                fw.write(s + '\n');
            }
        } catch (Exception e) {
            System.out.println("Cant write file");
            System.out.println(e.getMessage());

        } finally {
            if (fw != null) fw.close();
        }
    }

    public void addToHistory(String word) {
        for (int i = 0; i < historyList.getItems().size(); i++) {
            if (historyList.getItems().get(i).equals(word)) {
                historyList.getItems().remove(i);
                historyContainer.remove(i);
                break;
            }
        }

        while (historyList.getItems().size() >= 50) {
            historyList.getItems().remove(49);
            historyContainer.remove(49);
        }

        historyList.getItems().add(0, word);
        historyContainer.add(0, word);
        System.out.println(historyContainer.size());
    }

    public void resetHistory() {
        historyList.getItems().clear();
        historyContainer.clear();
        try {
            saveHistory();
        } catch (IOException e) {
            System.out.println("Can't write file");
        }
    }

    public void UKAudio(ActionEvent event) throws IOException, JavaLayerException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    AudioGoogleAPI.getInstance().play(AudioGoogleAPI.getInstance().getAudio(searchWord, "en-UK"));
                } catch (JavaLayerException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        try {
            thread.start();
        } catch (Exception e) {
            AudioGoogleAPI.getInstance().play(AudioGoogleAPI.getInstance().getAudio("Please search a word!", "en-UK"));
        }
    }

    public void USAudio(ActionEvent event) throws IOException, JavaLayerException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    AudioGoogleAPI.getInstance().play(AudioGoogleAPI.getInstance().getAudio(searchWord, "en-US"));
                } catch (JavaLayerException | IOException e) {
                    throw new RuntimeException();
                }
            }
        });
        try {
            thread.start();
        } catch (Exception e) {
            AudioGoogleAPI.getInstance().play(AudioGoogleAPI.getInstance().getAudio("Please search a word!", "en-US"));
        }

    }

    public void favourHandler(ActionEvent event) {
        if (FavouriteWord.IsFavour(searchWord)) {
            FavouriteWord.removeFavour(searchWord);
            favour_button.setGraphic(border_star);
            favour_star.setVisible(false);
            border_star.setVisible(true);
        } else {
            FavouriteWord.addFavourite(searchWord);
            favour_button.setGraphic(favour_star);
            border_star.setVisible(false);
            favour_star.setVisible(true);
        }
    }

    public void EditWord(ActionEvent e) {
        submitEditButton.setVisible(true);
        searchField.setEditable(false);
        String result = style + DictionaryCommandline.getWord(searchWord, DBConnect.connectDB());
        result = result.replace("contenteditable=\"false\" ", "");
        System.out.println(result);

        ToolBar toolBar = (ToolBar) editField.lookup(".top-toolbar");
        toolBar.setManaged(false);
        toolBar.setVisible(false);

        ToolBar paragraphToolBar = (ToolBar) editField.lookup(".bottom-toolbar");
        paragraphToolBar.setManaged(false);
        paragraphToolBar.setVisible(false);

        historyList.setDisable(true);
        editField.setHtmlText(result);
        editField.setVisible(true);
        EditButton.setVisible(false);
    }

    public void submitEditWord (ActionEvent e) {
        searchField.setEditable(true);
        String result = editField.getHtmlText();
        System.out.println(result);
        result = result.replace("true", "false");
        System.out.println(result);
        DictionaryManagement.EditFromFront(searchWord, result, DBConnect.connectDB());

        searchResult.getEngine().loadContent(result);

        historyList.setDisable(false);
        editField.setVisible(false);
        submitEditButton.setVisible(false);
        EditButton.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        suggestLabel();
        //init history
        try {
            initHistory();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EditButton.setVisible(false);
        correctionButton.setVisible(false);
        suggestList.setVisible(false);
        favour_button.setVisible(false);
        favour_star.setVisible(false);
        border_star.setVisible(false);
        editField.setVisible(false);
        submitEditButton.setVisible(false);
        IntoProgramController tmp = new IntoProgramController();

        // setting font and font Size
        setting();


        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            suggestList.getItems().clear();
            suggestList.getItems().addAll(DictionaryCommandline.suggestWord(newValue, DBConnect.connectDB()));
            suggestList.setVisible(true);
            if (newValue.isEmpty()) {
                suggestList.setVisible(false);
            }
        });

        // click for autocomplete
        suggestList.setOnMouseClicked(event -> {
            String selectedSuggestion = suggestList.getSelectionModel().getSelectedItem();
            if (selectedSuggestion != null) {
                searchField.setText(selectedSuggestion);
            }

            searchWord = searchField.getText();
            addToHistory(searchWord);
            favour_button.setVisible(false);

            String result = style + DictionaryCommandline.getWord(searchWord, DBConnect.connectDB());
            resultProcessing();

            searchResult.getEngine().loadContent(result);
            suggestList.setVisible(false);
        });

        // Search by history
        historyList.setOnMouseClicked(event -> {
            String selectedSuggestion = historyList.getSelectionModel().getSelectedItem();
            if (selectedSuggestion != null) {
                searchField.setText(selectedSuggestion);
            }

            searchWord = searchField.getText();
            addToHistory(searchWord);
            favour_button.setVisible(false);

            String result = style + DictionaryCommandline.getWord(searchWord, DBConnect.connectDB());
            resultProcessing();

            searchResult.getEngine().loadContent(result);
            suggestList.setVisible(false);
        });

        // enter to search
        searchField.setOnAction(event -> {
            searchWord = searchField.getText();
            addToHistory(searchWord);

            favour_button.setVisible(false);

            String result = style + DictionaryCommandline.getWord(searchWord, DBConnect.connectDB());
            resultProcessing();

            searchResult.getEngine().loadContent(result);
            suggestList.setVisible(false);
        });
    }

    @Override
    public void showAddScene(ActionEvent event) throws IOException {
        saveHistory();
        ManageScene.showScene(root, stage, scene,event,"addAndChange.fxml");
    }

    @Override
    public void showSettingScene(ActionEvent event) throws IOException {
        saveHistory();
        ManageScene.showScene(root, stage, scene,event,"Setting.fxml");
    }

    @Override
    public void showGameScene(ActionEvent event) throws IOException {
        saveHistory();
        ManageScene.showScene(root, stage, scene,event,"Game.fxml");
    }

    @Override
    public void showTranslateScene(ActionEvent event) throws IOException {
        saveHistory();
        ManageScene.showScene(root, stage, scene, event,"Translate.fxml");
    }

    @Override
    public void showHomeScene(ActionEvent event) throws IOException {
        saveHistory();
        ManageScene.showHomeScene(BaseController.getRoot(), BaseController.getStage(), BaseController.getScene(), event);
    }

    public void setting() {
        settingFont = new Vector<>();


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
        ManageScene.setFont(searchField);
//        suggestList.setStyle("-fx-font: "+settingFont.get(1) +"px " +"'"+settingFont.get(0) +"';");
    }


}
