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
            for (int page = 1;;) {
                OmdbSearchResults results;

                if (hasYear) {
                    results = pmd.getOmdbMovieSearcher().search(
                            OmdbArgumentType.TITLE_SEARCH.withValue(title),
                            OmdbArgumentType.YEAR.withValue(year),
                            OmdbArgumentType.PAGE.withValue(page));
                } else {
                    results = pmd.getOmdbMovieSearcher().search(
                            OmdbArgumentType.TITLE_SEARCH.withValue(title),
                            OmdbArgumentType.PAGE.withValue(page));
                }

                System.out.println("You are on page " + page);
                printResults(results);

                boolean hasPrevPage = page > 1;
                boolean hasNextPage = results.getTotalResults() > page * 10;

                // keep trying until user gives a valid response
                while (true) {
                    System.out.println("Choose one of the following:");
                    System.out.println("l - Flip to left page");
                    System.out.println("r - Flip to right page");
                    System.out.println("c - Choose an item on this page");
                    System.out.print("[l/r/c]: ");
                    String choice = in.nextLine();
                    System.out.println("------------------------------------------");

                    switch (choice) {
                        case "l":
                            if (hasPrevPage) {
                                page--;
                                continue outer; // breaks out of inner loop
                            } else {
                                System.out.println("No left page.");
                            }
                            break;
                        case "r":
                            if (hasNextPage) {
                                page++;
                                continue outer; // breaks out of inner loop
                            } else {
                                System.out.println("No right page.");
                            }
                            break;
                        default:
                            int chosenTitleNumber = getInteger(in, System.out, "Enter title number: ", "Not a valid number.", 1, results.getResultList().size());
                            String chosenTitleId = results.getResultList().get(chosenTitleNumber - 1).getId();

                            MovieInfo info = pmd.getOmdbMovieSearcher().getInfo(
                                    OmdbArgumentType.ID.withValue(chosenTitleId)).get();

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printResults(OmdbSearchResults results) {
        System.out.println("Result count: " + results.getTotalResults());
        System.out.println("------------------------------------------");
        int count = 1;
        for (OmdbSearchResult result : results.getResultList()) {
            System.out.println(count++ + ": " + result.getTitle() + " (" + result.getYear() + ")");
        }
        System.out.println("------------------------------------------");
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
