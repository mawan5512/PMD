package com.github.mawan5512.pmd.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Add {

    public static void Add(String Title, int Year, int Runtime, String Genre, String Director, String LeadActors, String ShortSummary)
    {
        Connection con;
        Statement st;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmd", "root", "PMD");
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
