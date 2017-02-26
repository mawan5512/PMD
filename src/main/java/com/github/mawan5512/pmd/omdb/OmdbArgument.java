package com.github.mawan5512.pmd.omdb;

import java.util.Objects;

/**
 * Represents a key-value pair to be supplied in the query portion of the URL to OMDb.
 * @param <V> The type of the value in the key-value pair
 */
public final class OmdbArgument <V> {

    private final OmdbArgumentType<V> type;
    private final V value;

    OmdbArgument(OmdbArgumentType<V> type, V value) {
        this.type = type;
        this.value = Objects.requireNonNull(value);
    }

    OmdbArgumentType<V> getType() {
        return type;
    }

    V getValue() {
        return value;
    }

    String getKeyString() {
        return type.getKeyString();
    }

    String getValueAsString() {
        return type.getToStringFunction().apply(value);
    }

}
