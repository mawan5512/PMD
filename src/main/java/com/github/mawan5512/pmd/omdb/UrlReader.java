package com.github.mawan5512.pmd.omdb;

import java.io.IOException;
import java.net.URL;

public interface UrlReader {

    String getResponse(URL url, int timeout) throws IOException;

}
