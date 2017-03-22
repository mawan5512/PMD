package com.github.mawan5512.pmd.omdb;

public enum DetailType {

    FULL("full"), SHORT("short");

    private final String value;

    DetailType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
