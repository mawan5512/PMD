package com.github.mawan5512.pmd.omdb;

/**
 * One of the results in {@code OmdbSearchResults}
 */
public class OmdbSearchResult {

    private final String title;
    private final String year;
    private final String id;
    private final String type;
    private final String poster;

    public OmdbSearchResult(String title,String year,String id,String type,String poster ){
        this.title = title;
        this.year = year;
        this.id = id;
        this.type = type;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPoster() {
        return poster;
    }

}
