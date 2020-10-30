package io.shelfy.architecture.common;

import androidx.annotation.NonNull;

public interface Mapper<From, To> {

    @NonNull
    To map(@NonNull From from);
}
