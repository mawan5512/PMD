package com.github.mawan5512.pmd.omdb;

import static com.github.mawan5512.pmd.omdb.OmdbArgumentType.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A builder of {@code URL}s for querying OMDb.
 */
public class OmdbUrlBuilder {

    private final List<OmdbArgument<?>> args = new ArrayList<>();
    private final static String URI_SCHEME = "https";
    private final static String URI_AUTHORITY = "omdbapi.com";

    /**
     * Builds a URL for querying OMDb with the provided options.
     *
     * @param args An array of options to be included in the query
     * @return The built URL
     * @throws IllegalArgumentException If a criteria is not met as indicated
     * for the constants in OmdbArgumentType, an option other than TITLE_SEARCH
     * appears more than once, or no ID, TITLE, or TITLE_SEARCH is given
     * @throws NullPointerException If args is null or any element of args is
     * null
     */
    public static URL buildWithArgs(OmdbArgument<?>... args) {
        Objects.requireNonNull(args);
        OmdbUrlBuilder builder = new OmdbUrlBuilder();
        for (OmdbArgument<?> arg : args)
            builder.withArg(arg);
        return builder.build();
    }

    /**
     * Constructs a builder with no initial options.
     */
    public OmdbUrlBuilder() {}

    /**
     * Adds an argument to this builder.
     *
     * @param arg The argument to be added to the query
     * @return This OmdbUrlBuilder
     * @throws NullPointerException If arg is null
     */
    public OmdbUrlBuilder withArg(OmdbArgument<?> arg) {
        args.add(Objects.requireNonNull(arg));
        return this;
    }

    /**
     * Builds a URL for querying OMDb with the given options.
     *
     * @return The built URL
     * @throws IllegalArgumentException If a criteria is not met as indicated
     * for the constants in OmdbArgumentType, an option other than TITLE_SEARCH
     * appears more than once, or no ID, TITLE, or TITLE_SEARCH is given
     */
    public URL build() {
        checkArgs();

        StringBuilder queryBuilder = new StringBuilder();
        boolean first = true;

        for (OmdbArgument<?> arg : args) {
            if (!first)
                queryBuilder.append("&");

            queryBuilder.append(arg.getKeyString())
                    .append("=")
                    .append(arg.getValueAsString());

            first = false;
        }

        try {
            return new URI(URI_SCHEME, URI_AUTHORITY, null, queryBuilder.toString(), null).toURL();
        } catch (URISyntaxException|MalformedURLException impossible) {
            throw new AssertionError();
        }
    }

    private void checkArgs() {
        ensure(has(TITLE) || has(ID) || has(TITLE_SEARCH), "Must specify either title, id, or title search");
        ensure(!(has(TITLE) && has(ID)), "Must not specify both title and id");
        ensure(!(has(TITLE) && has(TITLE_SEARCH)), "Must not specify both title and title search");
        ensure(!(has(ID) && has(TITLE_SEARCH)), "Must not specify both id and title search");
        ensure(!(has(PAGE) && !has(TITLE_SEARCH)), "Must specify title search with page");
        ensure(!((has(SEASON) || has(EPISODE)) && doesNotHaveTitleOrId()), "Must specify season or episode with title or id");
        ensure(!(has(PLOT_LENGTH) && doesNotHaveTitleOrId()), "Must specify plot length with title or id");
        ensure(!(has(INCLUDE_TOMATOES) && doesNotHaveTitleOrId()), "Must specify tomatoes with title or id");
        ensure(!(has(DETAIL_TYPE) && doesNotHaveTitleOrId()), "Must specify detail type with title or id");
        ensure(!(has(EPISODE) && !(has(SEASON))), "Must specify season with episode");
        ensure(!(has(DETAIL_TYPE) && (!has(SEASON) || has(EPISODE))), "Must specify detail type with season and not episode");

        ensureNotMoreThanOne(ID);
        ensureNotMoreThanOne(TITLE);
        ensureNotMoreThanOne(TITLE_TYPE);
        ensureNotMoreThanOne(YEAR);
        ensureNotMoreThanOne(PLOT_LENGTH);
        ensureNotMoreThanOne(DATA_TYPE);
        ensureNotMoreThanOne(INCLUDE_TOMATOES);
        ensureNotMoreThanOne(JSONP_CALLBACK);
        ensureNotMoreThanOne(API_VERSION);
        ensureNotMoreThanOne(PAGE);
        ensureNotMoreThanOne(SEASON);
        ensureNotMoreThanOne(EPISODE);
        ensureNotMoreThanOne(DETAIL_TYPE);
        //only type that can have more than one is TITLE_SEARCH
    }

    private void ensureNotMoreThanOne(OmdbArgumentType<?> type) {
        ensure(isNotMoreThanOne(type), "Must not specify more than one of " + type.getKeyString());
    }

    private boolean isNotMoreThanOne(OmdbArgumentType<?> type) {
        return args.stream().filter(arg -> arg.getType().equals(type)).count() <= 1;
    }

    private boolean doesNotHaveTitleOrId() {
        return !(has(TITLE) || has(ID));
    }

    private void ensure(boolean b, String errorMsg) {
        if (!b)
            throw new IllegalArgumentException(errorMsg);
    }

    private boolean has(OmdbArgumentType<?> type) {
        return args.stream().anyMatch(arg -> arg.getType().equals(type));
    }

}