package com.github.mawan5512.pmd.omdb;

public class OmdbResponseException extends RuntimeException {

    public OmdbResponseException() {
    }

    public OmdbResponseException(String message) {
        super(message);
    }

    public OmdbResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public OmdbResponseException(Throwable cause) {
        super(cause);
    }

    public OmdbResponseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
