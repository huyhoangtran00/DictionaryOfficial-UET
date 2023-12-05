package com.example.commandLine;

import java.util.ArrayList;
import java.util.List;

//import java.util.*;
public class Dictionary {
    protected List<Word> wordArr = new ArrayList<>();
    private static List<String> allWordsSet;

    public static List<String> getAllWordsSet() {
        if (allWordsSet == null) {
            allWordsSet = DictionaryCommandline.getAllWords(DBConnect.connectDB());
        }
        return allWordsSet;
    }

}
