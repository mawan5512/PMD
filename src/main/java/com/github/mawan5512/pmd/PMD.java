package com.github.mawan5512.pmd;

import com.github.mawan5512.pmd.omdb.MovieInfo;
import com.github.mawan5512.pmd.omdb.OmdbArgumentType;
import com.github.mawan5512.pmd.omdb.OmdbMovieSearcher;
import com.github.mawan5512.pmd.omdb.RealUrlReader;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;

public class PMD {

    private final OmdbMovieSearcher searcher;

    private PMD() {
        searcher = new OmdbMovieSearcher(new RealUrlReader("UTF-8"), 0);
    }

    public OmdbMovieSearcher getOmdbMovieSearcher() {
        return searcher;
    }

    public static void main(String[] args) {
        PMD pmd = new PMD();
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter title:");
            String title = in.nextLine();
            int year = getPositiveInteger(in, System.out, "Enter year:", "Year must be a positive integer.");

            Optional<MovieInfo> optionalInfo = pmd.getOmdbMovieSearcher().search(
                    OmdbArgumentType.TITLE.withValue(title),
                    OmdbArgumentType.YEAR.withValue(year));

            if (!optionalInfo.isPresent()) {
                System.out.println("Movie not found!");
            } else {
                MovieInfo info = optionalInfo.get();
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Asks the user for an integer, through in and out, until an integer is
     * provided.
     */
    private static int getPositiveInteger(Scanner in, PrintStream out, String enterMsg, String errorMsg) {
        while (true) {
            out.println(enterMsg);
            try {
                int i = Integer.parseInt(in.nextLine());
                if (i <= 0)
                    out.println(errorMsg);
                else
                    return i;
            } catch (NumberFormatException e) {
                out.println(errorMsg);
            }
        }
    }

}
