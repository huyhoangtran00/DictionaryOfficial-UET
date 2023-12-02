package com.example.Game;

import com.example.APIGoogle.AudioGoogleAPI;
import com.example.APIGoogle.GoogleTransAPI;
import com.example.commandLine.DBConnect;
import com.example.commandLine.DictionaryCommandline;
import com.example.commandLine.DictionaryManagement;
import javafx.concurrent.Task;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BlossomGame {
    private static HashSet<Character> key = new HashSet<>();

    public static HashSet<String> ListAnswer = new HashSet<>();

    public static int score = 0;



    public static HashSet<Character> UltiGame() {
        key = new HashSet<>();
        char[] nguyenam = {'a','u', 'e', 'o', 'i'};
        while (key.size() < 3) {
            char tmp = nguyenam[(int)(Math.random()*4)];
            if (!key.contains(tmp)) {
                key.add(tmp);
            }
        }
        char[] importantChar = {'l', 't', 'h'};
        while (key.size() < 5) {
            char tmp = importantChar[(int)(Math.random()*2)];
            if (!key.contains(tmp)) {
                key.add(tmp);
            }
        }
        while (key.size() < 7) {
            int randomKey = (int) (Math.random() * 26);
            char getKey = (char) (randomKey + 'a');
            if (!key.contains(getKey)) {
                key.add(getKey);
            }
        }
        return key;
    }

    public static int TrueOrFalse(String answer) {
        if (ListAnswer.contains(answer)) {
            System.out.println("Answer is Exist");
            return 1;
        }
        for (int i = 0; i < answer.length(); i++) {
            if(!key.contains(answer.charAt(i))) {
                System.out.println("Not " + (answer.charAt(i)));
                return 1;
            }
        }
        if(!DictionaryCommandline.isExist(answer,DBConnect.connectDB())) {
            return 0;
        } else {
            ListAnswer.add(answer);
            return 2;
        }
    }

    public static void main(String[] args) throws IOException, JavaLayerException {
        UltiGame();
        //System.out.println(key);
        Scanner sc = new Scanner(System.in);
        int q = 7;
        while(q --> 0) {
            System.out.println(key);
            String tmp = sc.next();
            if (TrueOrFalse(tmp) == 2) {
                AudioGoogleAPI audio = AudioGoogleAPI.getInstance();
                InputStream sound = audio.getAudio(tmp, "en-UK");
                audio.play(sound);
                System.out.println("Correct");
                score ++;
            } else {
                System.out.println("Wrong");
            }
        }
        System.out.println(score);
    }
}
