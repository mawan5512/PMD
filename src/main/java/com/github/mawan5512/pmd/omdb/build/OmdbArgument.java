package com.github.mawan5512.pmd.omdb.build;

import java.util.Objects;

/**
 * An option that can be included in a query to OMDb, complete with a key and
 * value.
 *
 * @param <V> The type of the value in the key-value pair
 */
public final class OmdbArgument <V> {

    private final OmdbArgumentType<V> type;
    private final V value;

    OmdbArgument(OmdbArgumentType<V> type, V value) {
        this.type = type;
        this.value = Objects.requireNonNull(value);
        if (!type.getIsValidValuePredicate().test(value))
            throw new IllegalArgumentException("value is not valid for this type");
    }

    public OmdbArgumentType<V> getType() {
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
