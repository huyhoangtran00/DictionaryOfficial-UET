package com.example.cilent;

import com.example.commandLine.DBConnect;
import com.example.commandLine.Word;
import com.example.dictionaryofficial.IntoProgramController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlashCard {
    public static ArrayList<Word> ListFlashCard = new ArrayList<>();

    public static void GenListFlashCard() {
        try {
            ListFlashCard.clear();
            String FlashCard = "Select word , description from " + FavouriteWord.FAVOUR;
            PreparedStatement getFlashCard = DBConnect.connectDB().prepareStatement(FlashCard);
            ResultSet res = getFlashCard.executeQuery();
            while (res.next()) {
                ListFlashCard.add(new Word(res.getString(1),res.getString(2) != "" ? res.getString(2) : "null" ));
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi Flashcard");
        }
    }

    public static List<Word> getListFlashCard() {
        GenListFlashCard();
        Collections.shuffle(ListFlashCard);
        return ListFlashCard;
    }
}
