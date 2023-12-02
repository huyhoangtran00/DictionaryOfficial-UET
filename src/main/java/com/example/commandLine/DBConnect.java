package com.example.commandLine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnect {
    public static final String DB_URL = "jdbc:mysql://localhost:3307/dicdb";
    public static final String USER_NAME = "hoanghiep";
    public static final String PASSWORD = "hoanghiep2323";

    public static final String DB_NAME = "av";

    private static Connection connection;

    /**
     * main
     *
     * @author viettuts.vn
     * @param args
     */
    public static void main(String args[]) {
        try {
            // connnect to database 'testdb'
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery("select * from " + DB_NAME);
            // show data
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2)
                        + "  " + rs.getString(3));
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * create connection
     *
     * @author viettuts.vn
     * @param dbURL: database's url
     * @param userName: username is used to login
     * @param password: password is used to login
     * @return connection
     */
    private static Connection getConnection(String dbURL, String userName,
                                           String password) {
        Connection conn = null;
        try {
            String dbPath = "src/main/resources/com/example/Database/dictionary_database.db";
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }

    public static Connection connectDB() {
        if (connection == null) {
            connection = getConnection(DB_URL, USER_NAME, PASSWORD);
        }
        return connection;
    }

}
