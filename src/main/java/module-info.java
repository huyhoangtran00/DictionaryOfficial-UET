module com.example.dictionaryofficial {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;
    requires jlayer;
    requires java.desktop;


    opens com.example.dictionaryofficial to javafx.fxml;
    exports com.example.dictionaryofficial;
}