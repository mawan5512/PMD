package com.github.mawan5512.pmd.omdb;

import java.util.List;

/**
 * The result of searching OMDb with the "s" parameter, giving a list of
 * search results.
 */
public class OmdbSearchResults {

    private final int totalResults;
    private final List<OmdbSearchResult> resultList;

    public OmdbSearchResults(int totalResults, List<OmdbSearchResult> resultList) {
        this.totalResults = totalResults;
        this.resultList = resultList;
    }

    /**
     * Returns the total number of results from the search, including those
     * not in this page (result list)
     */
    public int getTotalResults() {
        return totalResults;
    }

    /**
     * Returns a list of search results with a max size of 10. This is
     * a limitation of OMDb; only 10 results per page. You need to query again
     * to get the next page of results.
     */
    public List<OmdbSearchResult> getResultList() {
        return resultList;
    }
}
