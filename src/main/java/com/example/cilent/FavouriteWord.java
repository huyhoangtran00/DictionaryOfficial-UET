package com.example.cilent;

import com.example.commandLine.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FavouriteWord {

    public static Connection connect = DBConnect.connectDB();

    public static final String FAVOUR = "avFavorite";
    public static void showFavourite() {
        try {
            String Favour = "Select word from " + FAVOUR;
            PreparedStatement getFavour = connect.prepareStatement(Favour);
            ResultSet res = getFavour.executeQuery();
            while (res.next()) {
                System.out.println(res.getString(1));
            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi Favour");
        }
    }

    public static boolean IsFavour(String newFavour) {
        try {
            String sql = "Select COUNT(*) from " + FAVOUR + " where word = ?";
            PreparedStatement checkIsFavour = connect.prepareStatement(sql);
            checkIsFavour.setString(1, newFavour);
            ResultSet res = checkIsFavour.executeQuery();
            res.next();
            int exsist = res.getInt(1);
            if (exsist == 0) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi check");
        }
        return false;
    }

    public static void addFavorite(String newFavour) {
        if (IsFavour(newFavour)) {
            return ;
        }
        try {
            String sql = "Insert into " + FAVOUR + "(word, html, description, pronounce) " +
                        "select wordTarget, html, wordMeaning, pronounce from " + DBConnect.DB_NAME +
                        " where wordTarget = ?";
            PreparedStatement addFavour = connect.prepareStatement(sql);
            connect.setAutoCommit(false);
            addFavour.setString(1, newFavour);
            int a = addFavour.executeUpdate();
            connect.commit();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi add");
        }
    }

    public static void removeFavour(String rev) {
        try {
            String sql = " DELETE FROM " + FAVOUR
                    + " WHERE word = ?";
            PreparedStatement revWord = connect.prepareStatement(sql);
            connect.setAutoCommit(false);
            revWord.setString(1,rev);
            revWord.executeUpdate();
            connect.commit();

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi xoa");
        }
    }

    public static void main(String[] args) {
        addFavorite("apple");
        showFavourite();
        removeFavour("apple");
        showFavourite();
    }
}
