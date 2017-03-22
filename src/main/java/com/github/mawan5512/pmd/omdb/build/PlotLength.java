package com.github.mawan5512.pmd.omdb.build;

public enum PlotLength {

    SHORT("short"), FULL("full");

    private final String value;

    PlotLength(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
