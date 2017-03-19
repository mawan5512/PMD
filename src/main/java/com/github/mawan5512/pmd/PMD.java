package com.github.mawan5512.pmd;

import com.github.mawan5512.pmd.database.Add;
import com.github.mawan5512.pmd.gui.HomePanel;
import com.github.mawan5512.pmd.gui.LibraryPanel;
import com.github.mawan5512.pmd.omdb.MovieInfo;
import com.github.mawan5512.pmd.omdb.OmdbMovieSearcher;
import com.github.mawan5512.pmd.omdb.build.RealUrlReader;

import javax.swing.*;
import java.io.IOException;

public class PMD {

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
        PMD pmd = new PMD();

        JFrame frame = new JFrame("PMDB");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);

        JTabbedPane tp = new JTabbedPane();
        tp.addTab("Home", new HomePanel());

        tp.addTab("MyLibrary", new LibraryPanel());

        frame.getContentPane().add(tp);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);
    }

}
