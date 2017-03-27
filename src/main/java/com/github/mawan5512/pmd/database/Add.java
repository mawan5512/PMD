package com.github.mawan5512.pmd.database;

import com.github.mawan5512.pmd.PMD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Add {

    public static void Add(String Title, String Year, int Runtime, String Genre, String Director, String LeadActors, String ShortSummary)
    {
        Connection con;
        Statement st;

        try
        {
            con = PMD.getDatabaseConnection();
            st = con.createStatement();
            st.executeUpdate("INSERT INTO Movies (Title, Year, Runtime, Genre, Director, LeadActors, ShortSummary) "
                    + "VALUES ('" + Title + "', '" + Year + "', '" + Runtime + "', '" + Genre + "', '" + Director + "', '" + LeadActors + "', '" + ShortSummary + "');");
            System.out.println("PMD Updated");
        }
        catch (Exception e)
        {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
    }

}
