package com.github.mawan5512.pmd.omdb;

import com.github.mawan5512.pmd.omdb.build.OmdbArgument;
import com.github.mawan5512.pmd.omdb.build.OmdbArgumentType;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PaginatedSearchResults {

    private final OmdbMovieSearcher searcher;
    private final OmdbArgument<?>[] args;
    private final Map<Integer, OmdbSearchResults> pages;

    private int currentPageNumber;
    private final int pageCount;
    private final int totalResults;

    PaginatedSearchResults(OmdbMovieSearcher searcher, OmdbArgument<?>... args) throws IOException {
        this.searcher = searcher;
        this.args = args;
        this.pages = new HashMap<>();

        currentPageNumber = 1;
        pages.put(1, readPage(1));
        totalResults = pages.get(1).getTotalResults();
        pageCount = Math.max(1, (totalResults - 1) / 10 + 1);
    }

    public boolean hasPrevPage() {
        return hasPage(currentPageNumber - 1);
    }

    public boolean hasNextPage() {
        return hasPage(currentPageNumber + 1);
    }

    public boolean hasPage(int pageNumber) {
        return pageNumber >= 1 && pageNumber <= getPageCount();
    }

    public OmdbSearchResults getCurrentPage() throws IOException {
        return skipTo(currentPageNumber);
    }

    public OmdbSearchResults prevPage() throws IOException {
        return skipTo(currentPageNumber - 1);
    }

    public OmdbSearchResults nextPage() throws IOException {
        return skipTo(currentPageNumber + 1);
    }

    public OmdbSearchResults skipTo(int pageNumber) throws IOException {
        if (!hasPage(pageNumber))
            throw new IllegalStateException();

        OmdbSearchResults results = pages.get(currentPageNumber = pageNumber);
        if (results == null)
            pages.put(pageNumber, results = readPage(pageNumber));
        return results;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getTotalResultCount() {
        return totalResults;
    }

    private OmdbSearchResults readPage(int page) throws IOException {
        return searcher.search(addPageToArgs(page, args));
    }

    private static OmdbArgument<?>[] addPageToArgs(int page, OmdbArgument<?>... args) {
        OmdbArgument<?>[] newArgs = Arrays.copyOf(args, args.length + 1);
        newArgs[newArgs.length - 1] = OmdbArgumentType.PAGE.withValue(page);
        return newArgs;
    }

}
