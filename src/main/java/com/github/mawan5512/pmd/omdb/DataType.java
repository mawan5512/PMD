package com.github.mawan5512.pmd.omdb;

public enum DataType {

    JSON("json"), XML("xml");

    private final String value;

    DataType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
