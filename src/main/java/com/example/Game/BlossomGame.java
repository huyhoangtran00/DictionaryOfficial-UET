package com.example.Game;

import com.example.commandLine.DBConnect;
import com.example.commandLine.DictionaryCommandline;
import com.example.commandLine.DictionaryManagement;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Scanner;

public class BlossomGame {
    public static HashSet<Character> key = new HashSet<>();

    public static int score = 0;



    public static void UltiGame() {
        while (key.size() < 7) {
            int randomKey = (int) (Math.random() * 26);
            char getKey = (char) (randomKey + 'a');
            if (!key.contains(getKey)) {
                key.add(getKey);
            }
        }
    }

    public static boolean TrueOrFalse(String answer) {
        for (int i = 0; i < answer.length(); i++) {
            if(!key.contains(answer.charAt(i))) {
                System.out.println("Not " + (answer.charAt(i)));
                return false;
            }
        }
        if(!DictionaryCommandline.isExist(answer,DBConnect.connectDB())) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        UltiGame();
        //System.out.println(key);
        Scanner sc = new Scanner(System.in);
        int q = 7;
        while(q --> 0) {
            System.out.println(key);
            String tmp = sc.next();
            if (TrueOrFalse(tmp)) {
                System.out.println("Correct");
                score ++;
            } else {
                System.out.println("Wrong");
            }
        }
        System.out.println(score);
    }
}
