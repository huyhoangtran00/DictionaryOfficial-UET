package com.example.commandLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class DictionaryManagement {
    public static void insertFromCommandline(Dictionary dictionary, Connection connect) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insert the number of words: ");
        int numWords = sc.nextInt();
        while (numWords-- > 0) {
            String wordTarget = sc.next();
            String wordExplain = sc.nextLine();
            Word word = new Word(wordTarget, wordExplain);
            dictionary.wordArr.add(word);

            try {
                // Kiểm tra xem từ đã tồn tại hay chưa
                String CheckExist = "SELECT COUNT(*) FROM " + DBConnect.DB_NAME
                        + " WHERE wordTarget = ?";

                PreparedStatement checkExist = connect.prepareStatement(CheckExist);
                checkExist.setString(1,wordTarget);
                ResultSet res = checkExist.executeQuery();
                res.next();
                int exist = res.getInt(1);


                if (exist == 0) {
                    String WordInsert = "INSERT INTO " + DBConnect.DB_NAME
                            + " (wordTarget,wordMeaning) VALUES (?, ?)";

                    PreparedStatement stmt = connect.prepareStatement(WordInsert);
                    connect.setAutoCommit(false);
                    stmt.setString(1,wordTarget);
                    stmt.setString(2,wordExplain);
                    int a = stmt.executeUpdate();
                    connect.commit();
                } else {
                    System.out.println("Từ đã tồn tại trong từ điển, nghĩa của từ sẽ được cập nhật");
                    String GetExplain = "SELECT id, wordTarget, wordMeaning FROM " + DBConnect.DB_NAME
                            + " WHERE wordTarget = ?";
                    PreparedStatement getExplain = connect.prepareStatement(GetExplain);
                    getExplain.setString(1,wordTarget);
                    ResultSet getExplainWord = getExplain.executeQuery();
                    getExplainWord.next();
                    String Explain = getExplainWord.getString(3) + "\n" + wordExplain;


                    String Update = "UPDATE " + DBConnect.DB_NAME + " SET wordMeaning = ? WHERE wordTarget = ?";
                    PreparedStatement update = connect.prepareStatement(Update);
                    connect.setAutoCommit(false);
                    update.setString(1,Explain);
                    update.setString(2,wordTarget);
                    update.executeUpdate();
                    connect.commit();

                    connect.setAutoCommit(false);

                }


            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void removeFromCommandLine(Connection connect) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập từ muốn xóa ");
        String wordDelete = sc.nextLine();
        try {
            String CheckExist = "SELECT COUNT(*) FROM " + DBConnect.DB_NAME
                    + " WHERE wordTarget = ?";

            PreparedStatement checkExist = connect.prepareStatement(CheckExist);
            checkExist.setString(1,wordDelete);
            ResultSet res = checkExist.executeQuery();
            res.next();
            int exist = res.getInt(1);
            if(exist == 0) {
                System.out.println("Không tồn tại từ này");
                return;
            } else {
                String Delete = "DELETE FROM " + DBConnect.DB_NAME
                        + " WHERE wordTarget = ?";
                PreparedStatement delete = connect.prepareStatement(Delete);
                delete.setString(1, wordDelete);
                connect.setAutoCommit(false);
                delete.executeUpdate();
                connect.commit();
            }
        } catch (Exception e) {
            System.out.println("No");
        }
    }

    public static boolean removeFromFront(String wordTarget, Connection connect) {
        try {
            String CheckExist = "SELECT COUNT(*) FROM " + DBConnect.DB_NAME
                    + " WHERE wordTarget = ?";
            String CheckExistInFavour = "SELECT COUNT(*) FROM avFavorite"
                    + " WHERE word = ?";
            PreparedStatement checkExist = connect.prepareStatement(CheckExist);
            PreparedStatement checkExistInFavour = connect.prepareStatement(CheckExistInFavour);
            checkExist.setString(1,wordTarget);
            checkExistInFavour.setString(1, wordTarget);
            ResultSet res = checkExist.executeQuery();
            ResultSet res1 = checkExistInFavour.executeQuery();
            res.next();
            res1.next();
            int exist = res.getInt(1);
            int existInfavour = res1.getInt(1);
            if(exist == 0) {
                System.out.println("Không tồn tại từ này");
                return false;
            } else {
                String Delete = "DELETE FROM " + DBConnect.DB_NAME
                        + " WHERE wordTarget = ?";
                PreparedStatement delete = connect.prepareStatement(Delete);
                delete.setString(1, wordTarget);
                connect.setAutoCommit(false);
                delete.executeUpdate();
                connect.commit();
            }

            if(existInfavour != 0) {
                String Delete = "DELETE FROM avFavorite"
                        + " WHERE word = ?";
                PreparedStatement delete = connect.prepareStatement(Delete);
                delete.setString(1, wordTarget);
                connect.setAutoCommit(false);
                delete.executeUpdate();
                connect.commit();
            }
        } catch (Exception e) {
            System.out.println("No");
            return false;
        }
        return true;
    }

    public static void FixFromCommandLine(Dictionary dictionary, Connection connect) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập từ muốn sửa :");
        String wordFix = sc.next();
        try {
            String CheckExist = "SELECT COUNT(*) FROM " + DBConnect.DB_NAME
                    + " WHERE wordTarget = ?";

            PreparedStatement checkExist = connect.prepareStatement(CheckExist);
            checkExist.setString(1,wordFix);
            ResultSet res = checkExist.executeQuery();
            res.next();
            int exist = res.getInt(1);
            if (exist == 0) {
                System.out.println("Không tồn tại từ này");
                return;
            } else {
                String wordfix = DictionaryCommandline.getWord(wordFix, connect);
                System.out.println("1.Tiếng anh \t 2.Tiếng việt");
                System.out.print("Bạn muốn sửa: ");
                int choice = sc.nextInt();
                sc.nextLine();
                String update;
                String[] WordFix = wordfix.split("/");
                String wordTarget = WordFix[0];
                String wordExplain = WordFix[1];

                switch (choice) {
                    case 1:
                        System.out.print("Bạn muốn sửa thành: ");
                        wordTarget = sc.nextLine();
                        connect.setAutoCommit(false);
                        update = "UPDATE " + DBConnect.DB_NAME + " SET wordTarget = ? WHERE wordMeaning = ?";
                        PreparedStatement Update = connect.prepareStatement(update);
                        Update.setString(1,wordTarget);
                        Update.setString(2,wordExplain);
                        Update.executeUpdate();
                        connect.commit();
                        break;
                    case 2:
                        System.out.print("Bạn muốn sửa thành: ");
                        wordExplain = sc.nextLine();
                        connect.setAutoCommit(false);
                        update = "UPDATE " + DBConnect.DB_NAME + " SET wordMeaning = ? WHERE wordTarget = ?";
                        PreparedStatement Update1 = connect.prepareStatement(update);
                        Update1.setString(1,wordExplain);
                        Update1.setString(2,wordTarget);
                        Update1.executeUpdate();
                        connect.commit();
                        break;
                }

                System.out.println(wordTarget + " = " + wordExplain);
            }

        } catch (Exception e) {
            System.out.println("Loi" + e);
        }
    }

    public static boolean insertFromFront(String wordTarget, String pronounce, HashMap<String, String> TypeMeaning, String example, String meaning, Connection connect) {
        String html = "<h1>" + wordTarget + "</h1>" + "<h3><i>" + pronounce + "</i></h3>";
        for (Map.Entry<String, String> TypeWithMeaning : TypeMeaning.entrySet()) {
            if (Objects.equals(TypeWithMeaning.getValue(), "")) continue;
            html += "<h2>" + TypeWithMeaning.getKey() + "</h2>";
            String[] tmp = TypeWithMeaning.getValue().split("\n");
            for (String si : tmp) {
                html += "<li>" + si + "</li>";
            }
        }
        html += "<h2>Example</h2>";
        String []eachExam = example.split("\n");
        for (String x : eachExam) {
            html += "<li>" + x + "</li>";
        }
        try {
            // Kiểm tra xem từ đã tồn tại hay chưa
            String CheckExist = "SELECT COUNT(*) FROM " + DBConnect.DB_NAME
                    + " WHERE wordTarget = ?";

            PreparedStatement checkExist = connect.prepareStatement(CheckExist);
            checkExist.setString(1,wordTarget);
            ResultSet res = checkExist.executeQuery();
            res.next();
            int exist = res.getInt(1);


            if (exist == 0) {
                String WordInsert = "INSERT INTO " + DBConnect.DB_NAME
                        + " (wordTarget,html,wordMeaning) VALUES (?, ?, ?)";

                PreparedStatement stmt = connect.prepareStatement(WordInsert);
                connect.setAutoCommit(false);
                stmt.setString(1,wordTarget);
                stmt.setString(2,html);
                stmt.setString(3,meaning);
                int a = stmt.executeUpdate();
                connect.commit();
            } else {
                return false;

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    public static void insertFromFile(Dictionary dictionary) throws FileNotFoundException {
        File file = new File("src/main/java/org/example/dictionaries.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            Word word = new Word(sc.next(), sc.nextLine().trim());
            dictionary.wordArr.add(word);
        }
    }

    public static void EditFromFront(String word, String html, Connection connect) {
        try {
            connect.setAutoCommit(false);
            String sql = "UPDATE " + DBConnect.DB_NAME + " SET html = ? WHERE wordTarget = ?";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setString(1,html);
            stmt.setString(2,word);
            stmt.executeUpdate();
            connect.commit();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi sua tu frontend");
        }
    }
}
