package io.shelfy.utils;

import androidx.annotation.NonNull;

public interface Mapper<From, To> {

    @NonNull
    To map(@NonNull From from);
}
