package com.github.mawan5512.pmd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PMD {

    public static void main(String[] args) {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:///home/evan/Desktop/database", "root", "");
        } catch (SQLException e) {
            System.err.println("Could not create connection.");
            e.printStackTrace();
            return;
        }

        try {
            connection.createStatement().executeUpdate("test");
        } catch (SQLException e) {
            System.err.println("Could not execute command.");
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Could not close connection.");
            e.printStackTrace();
        }
    }

}
