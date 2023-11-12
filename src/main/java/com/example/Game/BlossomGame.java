package com.example.Game;

import com.example.APIGoogle.AudioGoogleAPI;
import com.example.APIGoogle.GoogleTransAPI;
import com.example.commandLine.DBConnect;
import com.example.commandLine.DictionaryCommandline;
import com.example.commandLine.DictionaryManagement;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class BlossomGame {
    public static HashSet<Character> key = new HashSet<>();

    public static HashSet<String> ListAnswer = new HashSet<>();

    public static int score = 0;



    public static void UltiGame() {

        char[] nguyenam = {'a','u', 'e', 'o', 'i'};
        while (key.size() < 3) {
            char tmp = nguyenam[(int)(Math.random()*4)];
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
    }

    public static boolean TrueOrFalse(String answer) {
        if (ListAnswer.contains(answer)) {
            System.out.println("Answer is Exist");
            return false;
        }
        for (int i = 0; i < answer.length(); i++) {
            if(!key.contains(answer.charAt(i))) {
                System.out.println("Not " + (answer.charAt(i)));
                return false;
            }
        }
        if(!DictionaryCommandline.isExist(answer,DBConnect.connectDB())) {
            return false;
        } else {
            ListAnswer.add(answer);
            return true;
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
            if (TrueOrFalse(tmp)) {
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
