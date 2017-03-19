package com.github.mawan5512.pmd.omdb;

import com.github.mawan5512.pmd.omdb.build.OmdbArgument;
import com.github.mawan5512.pmd.omdb.build.OmdbArgumentType;
import com.github.mawan5512.pmd.omdb.build.OmdbUrlBuilder;
import com.github.mawan5512.pmd.omdb.build.UrlReader;

import static com.github.mawan5512.pmd.omdb.build.OmdbArgumentType.*;

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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // The following 2 methods are for searching OMDb. They return a list of results.
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Searches OMDb for a list of titles matching the given criteria.
     *
     * <p>There are never more than 10 titles in the result, but more pages
     * may be retrieved by calling this method with the same argument and an
     * additional page argument with a value for the page number.</p>
     *
     * @param args Arguments to be supplied to OMDb
     * @return a list of results
     * @throws IOException if an IO error occurs
     * @throws SocketTimeoutException after default timeout milliseconds have
     * passed since connection attempt
     * @throws IllegalArgumentException if a TITLE or ID argument is provided
     */
    public OmdbSearchResults search(OmdbArgument<?>... args) throws IOException {
        return search(defaultTimeout, args);
    }

    /**
     * Searches OMDb for a list of titles matching the given criteria.
     *
     * <p>There are never more than 10 titles in the result, but more pages
     * may be retrieved by calling this method with the same argument and an
     * additional page argument with a value for the page number.</p>
     *
     * @param timeout the time in milliseconds before this method throws
     *                SocketTimeoutException because a connection
     *                could not be made
     * @param args Arguments to be supplied to OMDb
     * @return a list of results
     * @throws IOException if an IO error occurs
     * @throws SocketTimeoutException after timeout milliseconds have passed
     * since connection attempt
     * @throws IllegalArgumentException if a TITLE or ID argument is provided
     */
    public OmdbSearchResults search(int timeout, OmdbArgument<?>... args) throws IOException {
        Objects.requireNonNull(args);
        ensureDoesNotContain(TITLE, args);
        ensureDoesNotContain(ID, args);

        URL url = OmdbUrlBuilder.buildWithArgs(args);
        String jsonText = reader.getResponse(url, timeout);
        return WebScraper.parseResultList(jsonText);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // The following 2 methods are for getting info for one title from OMDb. They return one result.
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Searches OMDb for movie info given the provided arguments
     *
     * @param args Arguments to be supplied to OMDb
     * @return movie info from the search result if there is a result or no
     * movie info otherwise
     * @throws IOException if an IO error occurs
     * @throws SocketTimeoutException after default timeout milliseconds have
     * passed since connection attempt
     * @throws IllegalArgumentException if a TITLE_SEARCH argument is provided
     */
    public Optional<MovieInfo> getInfo(OmdbArgument<?>... args) throws IOException {
        return getInfo(defaultTimeout, args);
    }

    /**
     * Searches OMDb for movie info given the provided arguments
     *
     * @param timeout the time in milliseconds before this method throws
     *                SocketTimeoutException because a connection
     *                could not be made
     * @param args Arguments to be supplied to OMDb
     * @return movie info from the search result if there is a result or no
     * movie info otherwise
     * @throws IOException if an IO error occurs
     * @throws SocketTimeoutException after timeout milliseconds have passed
     * since connection attempt
     * @throws IllegalArgumentException if a TITLE_SEARCH argument is provided
     */
    public Optional<MovieInfo> getInfo(int timeout, OmdbArgument<?>... args) throws IOException {
        Objects.requireNonNull(args);
        ensureDoesNotContain(TITLE_SEARCH, args);

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
