package com.github.mawan5512.pmd;

import com.github.mawan5512.pmd.omdb.OmdbMovieSearcher;
import com.github.mawan5512.pmd.omdb.RealUrlReader;

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
    }

}
