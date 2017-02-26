package com.github.mawan5512.pmd;

import com.github.mawan5512.pmd.omdb.OmdbMovieSearcher;

public class PMD {

    private final static PMD singleton = new PMD();
    private final OmdbMovieSearcher searcher;

    public OmdbMovieSearcher getOmdbMovieSearcher() {
        return searcher;
    }

    private PMD() {
        searcher = new OmdbMovieSearcher(0);
    }

    public static PMD getInstance() {
        return singleton;
    }

    public static void main(String[] args) {

    }

}
