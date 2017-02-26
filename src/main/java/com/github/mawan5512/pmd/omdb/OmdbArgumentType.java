package com.github.mawan5512.pmd.omdb;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Represents a key that can be supplied to the query part of the URL to OMDb.
 * An OmdbArgument can be created from one of the instances of OmdbArgumentType in this class by invoking
 * withValue. An OmdbArgument can then be supplied to the OmdbMovieSearcher.search method.
 * For example, PMD.getOmdbMovieSearcher().search(OmdbArgumentType.TITLE.withValue("Inception")) returns a MovieInfo
 * for the film Inception.
 * @param <V> the type to be supplied to withValue, restricting the type of values for some keys
 */
public class OmdbArgumentType <V> {

    public final static OmdbArgumentType<String>        ID
            = new OmdbArgumentType<>(
            "i",
            id -> !id.isEmpty(),
            id -> id);

    public final static OmdbArgumentType<String>        TITLE
            = new OmdbArgumentType<>(
            "t",
            s -> !s.isEmpty(),
            s -> s);

    public final static OmdbArgumentType<TitleType>     TITLE_TYPE
            = new OmdbArgumentType<>(
            "type",
            t -> true,
            TitleType::getValue);

    public final static OmdbArgumentType<Integer>       YEAR
            = new OmdbArgumentType<>(
            "y",
            year -> year >= 1,
            Object::toString);

    public final static OmdbArgumentType<PlotLength>    PLOT_LENGTH
            = new OmdbArgumentType<>(
            "plot",
            plot -> true,
            PlotLength::getValue);

    public final static OmdbArgumentType<DataType>      DATA_TYPE
            = new OmdbArgumentType<>(
            "r",
            data -> true,
            DataType::getValue);

    public final static OmdbArgumentType<Boolean>       INCLUDE_TOMATOES
            = new OmdbArgumentType<>(
            "tomatoes",
            bool -> true,
            Object::toString);

    public final static OmdbArgumentType<String>        JSONP_CALLBACK
            = new OmdbArgumentType<>(
            "callback",
            callback -> !callback.isEmpty(),
            callback -> callback);

    public final static OmdbArgumentType<String>        API_VERSION
            = new OmdbArgumentType<>(
            "v",
            version -> !version.isEmpty(),
            version -> version);

    public final static OmdbArgumentType<String>        TITLE_SEARCH
            = new OmdbArgumentType<>(
            "s",
            value -> !value.isEmpty(),
            s -> s);

    public final static OmdbArgumentType<Integer>       PAGE
            = new OmdbArgumentType<>(
            "page",
            page -> page >= 1,
            Object::toString);

    public final static OmdbArgumentType<Integer>       SEASON
            = new OmdbArgumentType<>(
            "Season",
            season -> season >= 1,
            Object::toString);

    public final static OmdbArgumentType<Integer>       EPISODE
            = new OmdbArgumentType<>(
            "Episode",
            episode -> episode >= 1,
            Object::toString);

    public final static OmdbArgumentType<DetailType>    DETAIL_TYPE
            = new OmdbArgumentType<>(
            "detail",
            detail -> true,
            DetailType::getValue);

    private final String keyString;
    private final Predicate<V> isValidValue;
    private final Function<V, String> toString;

    private OmdbArgumentType(String keyString, Predicate<V> isValidValue, Function<V, String> toString) {
        this.keyString = Objects.requireNonNull(keyString);
        this.isValidValue = Objects.requireNonNull(isValidValue);
        this.toString = Objects.requireNonNull(toString);
    }

    /**
     * Returns an OmdbArgument representing an option in a query to OMDb with a key and value
     */
    public OmdbArgument<V> withValue(V value) {
        return new OmdbArgument<>(this, Objects.requireNonNull(value));
    }

    String getKeyString() {
        return keyString;
    }

    Predicate<V> getIsValidValuePredicate() {
        return isValidValue;
    }

    Function<V, String> getToStringFunction() {
        return toString;
    }

}
