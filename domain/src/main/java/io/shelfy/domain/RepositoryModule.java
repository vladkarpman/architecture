package io.shelfy.domain;

import androidx.annotation.NonNull;

public interface RepositoryModule {
    @NonNull
    Repository provideRepository();
}
