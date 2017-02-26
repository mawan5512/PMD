package com.github.mawan5512.pmd.omdb;

public enum TitleType {

    MOVIE("movie"), SERIES("series"), EPISODE("episode");

    private final String value;

    TitleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
