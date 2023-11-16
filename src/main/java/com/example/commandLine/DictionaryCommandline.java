package com.example.commandLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryCommandline {
    public static void showAllWords(Dictionary dictionary, Connection connect) {

        System.out.printf("%-3s | %-15s | %-20s \n", "No", "English", "Vietnamese");
//        int i = 1;
//        for (Word x : dictionary.wordArr) {
//            System.out.printf("%-3s | %-15s | %-20s \n", String.valueOf(i), x.getWordTarget(), x.getWordExplain());
//            i++;
//        }

        try {
            Statement smt = connect.createStatement();
            ResultSet res = smt.executeQuery("Select id, wordTarget, wordMeaning from " + DBConnect.DB_NAME + " limit 100");
            while (res.next()) {
                String meaning = res.getString(3);
                String[] mean = meaning.split("\n");
                for (int i = 0; i < mean.length; i ++) {
                    if (i == 0) {
                        System.out.printf("%-3s | %-15s | %-20s \n", res.getInt(1),
                                res.getString(2),
                                mean[i]);
                    } else {
                        System.out.printf("%-3s   %-15s | %-20s \n", "", "", mean[i]);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void dictionaryBasic(Dictionary dictionary,Connection connect) throws FileNotFoundException {
        //DictionaryManagement.insertFromCommandline(dictionary,connect);
        //DictionaryManagement.insertFromFile(dictionary);
        // DictionaryCommandline.showAllWords(dictionary,connect);
        System.out.println("Welcome to My Application!");
        System.out.println("[0] Exit");
        System.out.println("[1] Add");
        System.out.println("[2] Remove");
        System.out.println("[3] Update");
        System.out.println("[4] Display");
        System.out.println("[5] Lookup");
        System.out.println("[6] Search");
        System.out.println("[7] Game");
        System.out.println("[8] Import from file");
        System.out.println("[9] Export to file");

        System.out.print("Your action: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                DictionaryManagement.insertFromCommandline(dictionary,connect);
                break;
            case 2:
                DictionaryManagement.removeFromCommandLine(connect);
                break;
            case 3:
                DictionaryManagement.FixFromCommandLine(dictionary,connect);
                break;
            case 4:
                break;
            case 5:
                DictionaryCommandline.showAllWords(dictionary,connect);
                break;
            case 6:
                DictionaryCommandline.searchWord(connect);
                break;
        }
    }

    public static List<String> suggestWord(String word, Connection connect) {
        List<String> ListSuggest = new ArrayList<>();
        try {
            //System.out.println("Có vẻ từ bạn tìm không tồn tại, có phải ý bạn là: ");
            Statement smt = connect.createStatement();
            ResultSet res = smt.executeQuery("Select id, wordTarget, wordMeaning from " + DBConnect.DB_NAME
                                            + " where wordTarget like '" + word +"%'" + " limit 100");
//            System.out.printf("%-3s | %-15s | %-20s \n", "No", "English", "Vietnamese");
            while (res.next()) {
                String meaning = res.getString(3);
                String suggestWord = res.getString(2);
                ListSuggest.add(suggestWord);
                String[] mean = meaning.split("\n");
                for (int i = 0; i < mean.length; i ++) {
                    if (i == 0) {
//                        System.out.printf("%-3s | %-15s | %-20s \n", res.getInt(1),
//                                suggestWord,
//                                mean[i]);
                    } else {
//                        System.out.printf("%-3s   %-15s | %-20s \n", "", "", mean[i]);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ListSuggest;
    }

    public static String getWord (String word, Connection connect) {
        try {
            Statement smt = connect.createStatement();
            ResultSet res = smt.executeQuery("Select id, wordTarget, html from " + DBConnect.DB_NAME
                                                + " where wordTarget = '" + word + "'");
//            System.out.printf("%-3s | %-15s | %-20s \n", "No", "English", "Vietnamese");
            if(res.next()) {
                String meaning = res.getString(3);
                String[] mean = meaning.split("\n");
                meaning = "";
                for (int i = 0; i < mean.length; i ++) {
                    if (i == 0) {
//                        System.out.printf("%-3s | %-15s | %-20s \n", res.getInt(1),
//                                res.getString(2),
//                                mean[i]);
                        meaning = meaning + mean[i] + '\n';
                    } else {
                        //System.out.printf("%-3s   %-15s | %-20s \n", "", "", mean[i]);
                        meaning = meaning + mean[i] + '\n';
                    }
                }
//                suggestWord(word, connect);
                return meaning;
            } else {
                suggestWord(word, connect);
            }
        } catch (Exception e) {
            System.out.println("Loi truy xuat Db");
            System.out.println(e);
        }
        return "Oops! Your searched word is not available.\nYou can add new words to help us improve our dictionary.";
    }

    public static boolean isExist(String word, Connection connection) {
        return !(getWord(word, connection).equals("Oops! Your searched word is not available.\nYou can add new words to help us improve our dictionary."));
    }

    public static String searchWordFromFront(String word, Connection connect) {
        try {
            Statement smt = connect.createStatement();
            ResultSet res = smt.executeQuery("Select id, wordTarget, html from " + DBConnect.DB_NAME
                    + " where wordTarget = '" + word + "'");
            System.out.printf("%-3s | %-15s | %-20s \n", "No", "English", "Vietnamese");
            if(res.next()) {
                String meaning = res.getString(3);
//                String[] mean = meaning.split("\n");
//                meaning = "";
//                for (int i = 0; i < mean.length; i ++) {
//                    if (i == 0) {
//                        System.out.printf("%-3s | %-15s | %-20s \n", res.getInt(1),
//                                res.getString(2),
//                                mean[i]);
//                        meaning = meaning + mean[i] + '\n';
//                    } else {
//                        System.out.printf("%-3s   %-15s | %-20s \n", "", "", mean[i]);
//                        meaning = meaning + mean[i] + '\n';
//                    }
//                }
//                suggestWord(word, connect);
                return meaning;
            } else {
                suggestWord(word, connect);
            }
        } catch (Exception e) {
            System.out.println("Loi truy xuat Db");
            System.out.println(e);
        }
        return "Oops! Your searched word is not available." +
                "\nYou can add new words to help us improve our dictionary.";
    }

    public static void searchWord(Connection connect) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập từ cần tìm ");
        String wordTarget = sc.next();
        getWord(wordTarget,connect);
    }
}
