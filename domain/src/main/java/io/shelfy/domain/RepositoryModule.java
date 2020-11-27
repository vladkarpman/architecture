package io.shelfy.domain;


import io.reactivex.annotations.NonNull;

public interface RepositoryModule {
    @NonNull
    Repository provideRepository();
}
