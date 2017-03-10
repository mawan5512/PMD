package com.github.mawan5512.pmd.omdb;

import static com.github.mawan5512.pmd.omdb.OmdbArgumentType.*;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

public class OmdbMovieSearcher {

    private final UrlReader reader;
    private final int defaultTimeout;

    public OmdbMovieSearcher(UrlReader reader, int defaultTimeout) {
        this.reader = reader;
        this.defaultTimeout = defaultTimeout;
    }

    /**
     * Searches OMDb for movie info given the provided arguments
     * @param args Arguments to be supplied to OMDb
     * @return movie info from the search result if there is a result or no movie info otherwise
     * @throws IOException if an IO error occurs
     * @throws SocketTimeoutException after default timeout milliseconds have passed since connection attempt
     * @throws IllegalArgumentException if a TITLE_SEARCH or PAGE argument is provided
     */
    public Optional<MovieInfo> search(OmdbArgument<?>... args) throws IOException {
        return search(defaultTimeout, args);
    }

    /**
     * Searches OMDb for movie info given the provided arguments
     * @param timeout the time in milliseconds before this method throws SocketTimeoutException because a connection
     *                could not be made
     * @param args Arguments to be supplied to OMDb
     * @return movie info from the search result if there is a result or no movie info otherwise
     * @throws IOException if an IO error occurs
     * @throws SocketTimeoutException after timeout milliseconds have passed since connection attempt
     * @throws IllegalArgumentException if a TITLE_SEARCH or PAGE argument is provided
     */
    public Optional<MovieInfo> search(int timeout, OmdbArgument<?>... args) throws IOException {
        Objects.requireNonNull(args);
        ensureDoesNotContain(TITLE_SEARCH, args);
        ensureDoesNotContain(PAGE, args);

        URL url = OmdbUrlBuilder.buildWithArgs(args);
        String jsonText = reader.getResponse(url, timeout);
        return WebScraper.getInfo(jsonText);
    }

    private static void ensureDoesNotContain(OmdbArgumentType<?> arg, OmdbArgument<?>... args) {
        if (contains(arg, args))
            throw new IllegalArgumentException("Args must not contain " + arg.getKeyString());
    }

    private static boolean contains(OmdbArgumentType<?> type, OmdbArgument<?>... args) {
        for (OmdbArgument<?> arg : args)
            if (arg.getType().equals(type))
                return true;
        return false;
    }

}
