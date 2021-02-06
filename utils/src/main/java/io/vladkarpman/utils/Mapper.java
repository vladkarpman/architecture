package io.vladkarpman.utils;

import androidx.annotation.NonNull;

/**
 * interface for mapper/factory/converter
 */
public interface Mapper<From, To> {

    @NonNull
    To map(From from);
}
