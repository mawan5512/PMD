package com.github.mawan5512.pmd;

import com.github.mawan5512.pmd.database.Add;
import com.github.mawan5512.pmd.gui.HomePanel;
import com.github.mawan5512.pmd.gui.LibraryPanel;
import com.github.mawan5512.pmd.omdb.MovieInfo;
import com.github.mawan5512.pmd.omdb.OmdbMovieSearcher;
import com.github.mawan5512.pmd.omdb.build.RealUrlReader;
import com.sun.istack.internal.logging.Logger;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PMD {

    private final static String DB_DIR = System.getProperty("user.home") + "/PMDb";

    private final OmdbMovieSearcher searcher;

    private PMD() {
        searcher = new OmdbMovieSearcher(new RealUrlReader("UTF-8"), 0);
    }

    public void addToLibrary(MovieInfo info) {
        Add.Add(info.getTitle(), info.getYear(), info.getRuntime(), info.getGenre(), info.getDirector(), info.getActors(), info.getSummary());
    }

    public OmdbMovieSearcher getOmdbMovieSearcher() {
        return searcher;
    }

    public static void main(String[] args) throws IOException {
        startDatabaseServer();
        addDatabaseShutdownHook();
        PMD pmd = new PMD();

        JFrame frame = new JFrame("PMDB");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);

        JTabbedPane tp = new JTabbedPane();
        tp.addTab("Home", new HomePanel());

        tp.addTab("MyLibrary", new LibraryPanel(pmd));

        frame.getContentPane().add(tp);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);
    }

    public static Connection getDatabaseConnection() {
        try {
            return DriverManager.getConnection("jdbc:derby:" + DB_DIR);
        } catch (SQLException e) {
            throw new AssertionError(e);
        }
    }

    private static void startDatabaseServer() {
        try {
            DriverManager.getConnection("jdbc:derby:" + DB_DIR + ";create=true");
            System.out.println("Database started.");
        } catch (SQLException e) {
            throw new AssertionError(e);
        }
    }

    private static void addDatabaseShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                boolean gotSQLExc = false;
                try {
                    DriverManager.getConnection("jdbc:derby:;shutdown=true");
                } catch (SQLException e) {
                    if (e.getSQLState().equals("XJ015")) {
                        gotSQLExc = true;
                    }
                }
                if (!gotSQLExc) {
                    Logger.getLogger(PMD.class).severe("Database did not shut down normally.");
                } else {
                    Logger.getLogger(PMD.class).info("Database shut down normally.");
                }
            }
        });
    }

}
