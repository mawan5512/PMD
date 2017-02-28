package com.github.mawan5512.pmd;

import com.github.mawan5512.pmd.omdb.OmdbMovieSearcher;

public class PMD {

    private final OmdbMovieSearcher searcher;

    private PMD() {
        searcher = new OmdbMovieSearcher(0);
    }

    public OmdbMovieSearcher getOmdbMovieSearcher() {
        return searcher;
    }

    public static void main(String[] args) {
        PMD pmd = new PMD();
    }

}
