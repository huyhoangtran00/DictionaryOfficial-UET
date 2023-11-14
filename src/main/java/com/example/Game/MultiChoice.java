package com.example.Game;

import com.example.APIGoogle.AudioGoogleAPI;
import com.example.commandLine.DBConnect;
import com.example.commandLine.Main;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class MultiChoice {


    public List<String> genQuestion() {
        List<String> QndA = new ArrayList<>();
        try {
            int IdOfQues= (int)(Math.random() * 10000 + 1);
            String getSubAns = "SELECT wordTarget, wordMeaning from " + DBConnect.DB_NAME
                    + " where id = ?";
            PreparedStatement GetSubAns = DBConnect.connectDB().prepareStatement(getSubAns);
            GetSubAns.setInt(1, IdOfQues);
            ResultSet res = GetSubAns.executeQuery();
            String ans = res.getString(1);
            String ques = res.getString(2);
            QndA.add(ques);
            QndA.add(ans);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 3 ; i++) {
            int IdOfSubAns = (int)(Math.random() * 10000 + 1);
            try {
                String getSubAns = "SELECT wordTarget from " + DBConnect.DB_NAME
                                    + " where id = ?";
                PreparedStatement GetSubAns = DBConnect.connectDB().prepareStatement(getSubAns);
                GetSubAns.setInt(1, IdOfSubAns);
                ResultSet res = GetSubAns.executeQuery();
                QndA.add(res.getString(1));
            } catch (Exception e) {
                System.out.println("Loi lay dap an phu");
            }
        }
        return QndA;
    }

    public static void main(String[] args) {
        int q = 10;
        Scanner sc = new Scanner(System.in);
        while (q-- > 0) {
            List<String> QndA = new MultiChoice().genQuestion();
            String ques = QndA.get(0);
            System.out.println(ques);
            List<Character> key = new ArrayList<>();
            key.add('A');
            key.add('B');
            key.add('C');
            key.add('D');
            int pt = (int) (Math.random() * 4);
            System.out.println(pt);
            String[] answer = new String[4];
            answer[pt] = key.get(pt) + " " + QndA.get(1);
            int j = 2;
            for (int k = 0; k < 4; k++) {
                if (k != pt) {
                    answer[k] = key.get(k) + " " + QndA.get(j);
                    j++;
                }
            }
            for (String x : answer) {
                System.out.println(x);
            }
            char ans = sc.next().charAt(0);
            if (key.indexOf(ans) == pt) {
                System.out.println("Correct");
                Thread au = new Thread(() -> {
                    AudioGoogleAPI audio = AudioGoogleAPI.getInstance();
                    InputStream sound = null;
                    try {
                        sound = audio.getAudio("Correct", "en-UK");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        audio.play(sound);
                    } catch (JavaLayerException e) {
                        throw new RuntimeException(e);
                    }
                });
                au.start();
            }
            else {
                System.out.println("Wrong");
                Thread au = new Thread(() -> {
                    AudioGoogleAPI audio = AudioGoogleAPI.getInstance();
                    InputStream sound = null;
                    try {
                        sound = audio.getAudio("Wrong", "en-UK");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        audio.play(sound);
                    } catch (JavaLayerException e) {
                        throw new RuntimeException(e);
                    }
                });
                au.start();
            }

        }
    }
}


