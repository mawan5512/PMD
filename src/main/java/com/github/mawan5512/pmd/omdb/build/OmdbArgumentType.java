package com.github.mawan5512.pmd.omdb.build;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * The type of an option that can be included in a query to OMDb.
 *
 * <p>An OMDb option can be constructed with a type and value by invoking
 * {@code withValue} on any instance of this class. The option can then be
 * passed to an {@code OmdbUrlBuilder} which can create a URL for querying
 * OMDb.</p>
 *
 * <p>For each constant of this class, it is documented what values may be
 * passed to {@code withValue} as well as which other options are required
 * for each option and which are an exception if included together in a
 * query.</p>
 *
 * @param <V> The type of value this argument can have
 */
public class OmdbArgumentType <V> {

    /**
     * The "i" option to OMDb, specifying the IMDb ID of a title. The response
     * is just one title.
     * <p>
     * The provided value must not be the empty string.
     * <p>
     * Must not be included with TITLE or TITLE_SEARCH in a query.
     */
    public final static OmdbArgumentType<String>        ID
            = new OmdbArgumentType<>(
            "i",
            id -> !id.isEmpty(),
            id -> id);

    /**
     * The "t" option to OMDb, specifying the partial or full name of a
     * title. The response is just one title.
     * <p>
     * The provided value must not be the empty string.
     * <p>
     * Must not be included with ID or TITLE_SEARCH in a query.
     */
    public final static OmdbArgumentType<String>        TITLE
            = new OmdbArgumentType<>(
            "t",
            s -> !s.isEmpty(),
            s -> s);

    /**
     * The "type" option to OMDb, specifying the type of title to filter for.
     * <p>
     * If not included in a query, the default behavior is to not filter for any
     * particular type.
     */
    public final static OmdbArgumentType<TitleType>     TITLE_TYPE
            = new OmdbArgumentType<>(
            "type",
            t -> true,
            TitleType::getValue);

    /**
     * The "y" option to OMDb, specifying the year of a title to filter for.
     * <p>
     * The provided integer must be >= 1.
     * <p>
     * If not included in a query, the default behavior is to not filter for any
     * particular year.
     */
    public final static OmdbArgumentType<Integer>       YEAR
            = new OmdbArgumentType<>(
            "y",
            year -> year >= 1,
            Object::toString);

    /**
     * The "plot" option to OMDb, specifying the length of plot description
     * in the response.
     * <p>
     * If not included in a query, the default value is SHORT.
     * <p>
     * ID or TITLE must be included with this option in a query.
     */
    public final static OmdbArgumentType<PlotLength>    PLOT_LENGTH
            = new OmdbArgumentType<>(
            "plot",
            plot -> true,
            PlotLength::getValue);

    /**
     * The "r" option to OMDb, specifying the type of data to be returned.
     * <p>
     * If not included in a query, the default value is JSON.
     */
    public final static OmdbArgumentType<DataType>      DATA_TYPE
            = new OmdbArgumentType<>(
            "r",
            data -> true,
            DataType::getValue);

    /**
     * The "tomatoes" option to OMDb, specifying whether or not to include
     * the rotten tomatoes rating in the response.
     * <p>
     * If not included in a query, the default value is false.
     * <p>
     * ID or TITLE must be included with this option in a query.
     */
    public final static OmdbArgumentType<Boolean>       INCLUDE_TOMATOES
            = new OmdbArgumentType<>(
            "tomatoes",
            bool -> true,
            Object::toString);

    /**
     * The "callback" option to OMDb, specifying a javascript function to be
     * called.
     * <p>
     * The provided value must not be the empty string.
     * <p>
     * If not included in a query, no method is called.
     */
    public final static OmdbArgumentType<String>        JSONP_CALLBACK
            = new OmdbArgumentType<>(
            "callback",
            callback -> !callback.isEmpty(),
            callback -> callback);

    /**
     * The "v" option to OMDb, specifying the API version number.
     * <p>
     * The provided value must not be the empty string.
     * <p>
     * If not included in a query, the default value is "1".
     */
    public final static OmdbArgumentType<String>        API_VERSION
            = new OmdbArgumentType<>(
            "v",
            version -> !version.isEmpty(),
            version -> version);

    /**
     * The "s" option to OMDb, specifying the partial or full name of a title
     * to search for. The response is a list of titles. If more than one
     * of these is provided, then the responses are filtered further as if
     * by set intersection.
     * <p>
     * The provided value must not be the empty string.
     * <p>
     * Must not be included with ID or TITLE in a query.
     */
    public final static OmdbArgumentType<String>        TITLE_SEARCH
            = new OmdbArgumentType<>(
            "s",
            value -> !value.isEmpty(),
            s -> s);

    /**
     * The "page" option to OMDb, specifying the page of search results to get.
     * <p>
     * The provided value must be >= 1.
     * <p>
     * If not included in a query, the default value is 1.
     * <p>
     * TITLE_SEARCH must be included with this option in a query.
     */
    public final static OmdbArgumentType<Integer>       PAGE
            = new OmdbArgumentType<>(
            "page",
            page -> page >= 1,
            Object::toString);

    /**
     * The "Season" option to OMDb, specifying the season of a show. The
     * response includes a list of episodes in the season.
     * <p>
     * The provided value must be >= 1.
     * <p>
     * ID or TITLE must be included with this option in a query.
     */
    public final static OmdbArgumentType<Integer>       SEASON
            = new OmdbArgumentType<>(
            "Season",
            season -> season >= 1,
            Object::toString);

    /**
     * The "Episode" option to OMDb, specifying the episode of a season. The
     * response includes info on the episode.
     * <p>
     * The provided value must be >= 1.
     * <p>
     * SEASON must be included with this option in a query.
     */
    public final static OmdbArgumentType<Integer>       EPISODE
            = new OmdbArgumentType<>(
            "Episode",
            episode -> episode >= 1,
            Object::toString);

    /**
     * The "detail" option to OMDb, specifying the amount of detail for each
     * episode of a season.
     * <p>
     * If not included in a query, the default value is SHORT.
     * <p>
     * SEASON must be included with this option in a query. EPISODE must not be
     * included with this option in a query.
     */
    public final static OmdbArgumentType<DetailType>    DETAIL_TYPE
            = new OmdbArgumentType<>(
            "detail",
            detail -> true,
            DetailType::getValue);

    private final String keyString;
    private final Predicate<V> isValidValue;
    private final Function<V, String> toString;

    private OmdbArgumentType(String keyString, Predicate<V> isValidValue, Function<V, String> toString) {
        this.keyString = keyString;
        this.isValidValue = isValidValue;
        this.toString = toString;
    }

    /**
     * Returns an option that can be included in a query to OMDb with the given
     * value.
     *
     * @param value The value of this type
     * @return An OmdbArgument representing an option to OMDb with this
     * type and given value
     * @throws IllegalArgumentException If value is not a valid value for this
     * type of option as indicated for each constant of this class
     * @throws NullPointerException If value is null
     */
    public OmdbArgument<V> withValue(V value) {
        return new OmdbArgument<>(this, Objects.requireNonNull(value));
    }

    public String getKeyString() {
        return keyString;
    }

    Predicate<V> getIsValidValuePredicate() {
        return isValidValue;
    }

    Function<V, String> getToStringFunction() {
        return toString;
    }

}
