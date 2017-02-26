package com.github.mawan5512.pmd;

import com.github.mawan5512.pmd.omdb.OmdbMovieSearcher;

public class PMD {

    public static OmdbMovieSearcher getOmdbMovieSearcher() {
        return searcher;
    }

    private final static OmdbMovieSearcher searcher = new OmdbMovieSearcher(0);

    public static void main(String[] args) {

    }

}
