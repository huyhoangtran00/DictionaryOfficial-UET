package com.example.commandLine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {
        Connection Connect = DBConnect.connectDB();
        Dictionary dictionary = new Dictionary();
//        while (true) {
//            DictionaryCommandline.dictionaryBasic(dictionary, Connect);
//        }
        HashMap<String, String> danhtu = new HashMap<>();
        danhtu.put("Danh tu", "nghi1\nnghia3\nnghia4");
        DictionaryManagement.insertFromFront("abcde","vui",danhtu,"exp1\nExp2",Connect);
    }
}