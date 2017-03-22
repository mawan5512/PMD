package com.github.mawan5512.pmd.omdb;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class RealUrlReader implements UrlReader {

    private final String charset;

    public RealUrlReader(String charset) {
        this.charset = charset;
    }

    @Override
    public String getResponse(URL url, int timeout) throws IOException {
        URLConnection connection = url.openConnection();
        connection.setConnectTimeout(timeout);
        connection.setReadTimeout(timeout);

        StringBuilder responseBuilder = new StringBuilder();
        try (InputStreamReader reader = new InputStreamReader(connection.getInputStream(), charset)) {
            int nextChar;
            while ((nextChar = reader.read()) != -1) {
                responseBuilder.appendCodePoint(nextChar);
            }
        }
        return responseBuilder.toString();
    }

}
