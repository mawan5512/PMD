package com.github.mawan5512.pmd;

import com.github.mawan5512.pmd.omdb.*;
import com.github.mawan5512.pmd.database.Add;
import com.github.mawan5512.pmd.omdb.MovieInfo;
import com.github.mawan5512.pmd.omdb.OmdbMovieSearcher;
import com.github.mawan5512.pmd.omdb.build.OmdbArgumentType;
import com.github.mawan5512.pmd.omdb.build.RealUrlReader;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

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

        /*
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
         */

        try (Scanner in = new Scanner(System.in)) {
            String title;
            int year = 0;
            boolean hasYear;

            System.out.print("Enter title: ");
            title = in.nextLine();

            System.out.print("Would you like to enter a year? [y/n] ");
            hasYear = in.nextLine().equals("y");

            if (hasYear)
                year = getInteger(in, System.out, "Enter year: ", "Year must be a positive integer.", 1, Integer.MAX_VALUE);

            // keep trying until user selects a title
            outer:
            for (PaginatedSearchResults results = hasYear ? pmd.getOmdbMovieSearcher().search(title, year) : pmd.getOmdbMovieSearcher().search(title);;) {
                System.out.printf("--------------- Page %2d/%2d ---------------%n", results.getCurrentPageNumber(), results.getPageCount());
                printResults(results.getCurrentPage());

                // keep trying until user gives a valid response
                while (true) {
                    System.out.println("------------------------------------------");
                    System.out.println("Choose one of the following:");
                    System.out.println("l - Flip to left page");
                    System.out.println("r - Flip to right page");
                    System.out.println("c - Choose an item on this page");
                    System.out.print("[l/r/c]: ");
                    String choice = in.nextLine();

                    switch (choice) {
                        case "l":
                            if (results.hasPrevPage()) {
                                results.prevPage();
                                continue outer; // breaks out of inner loop
                            } else {
                                System.out.println("No left page.");
                            }
                            break;
                        case "r":
                            if (results.hasNextPage()) {
                                results.nextPage();
                                continue outer; // breaks out of inner loop
                            } else {
                                System.out.println("No right page.");
                            }
                            break;
                        default:
                            int chosenTitleNumber = getInteger(in, System.out, "Enter title number: ", "Not a valid number.", 1, results.getCurrentPage().getResultList().size());
                            String chosenTitleId = results.getCurrentPage().getResultList().get(chosenTitleNumber - 1).getId();

                            MovieInfo info = pmd.getOmdbMovieSearcher().getInfo(chosenTitleId).get();
                            System.out.println("------------------------------------------");
                            System.out.println(info.getActors());
                            System.out.println(info.getDirector());
                            System.out.println(info.getGenre());
                            System.out.println(info.getID());
                            System.out.println(info.getPicUrl());
                            System.out.println(info.getRelease());
                            System.out.println(info.getRuntime());
                            System.out.println(info.getSummary());
                            System.out.println(info.getTitle());
                            System.out.println(info.getYear());
                            return; // breaks out of inner loop
                    }
                }
            }
        }
    }

    private static void printResults(OmdbSearchResults results) {
        int count = 1;
        for (OmdbSearchResult result : results.getResultList()) {
            System.out.printf("%d: %s (%s)%n", count++, result.getTitle(), result.getYear());
        }
    }

    /**
     * Asks the user for an integer, through in and out, until an integer is
     * provided.
     */
    private static int getInteger(Scanner in, PrintStream out, String enterMsg, String errorMsg, int lowBound, int highBound) {
        while (true) {
            out.print(enterMsg);
            try {
                int i = Integer.parseInt(in.nextLine());
                if (i < lowBound || i > highBound)
                    out.println(errorMsg);
                else
                    return i;
            } catch (NumberFormatException e) {
                out.println(errorMsg);
            }
        }
    }

}
