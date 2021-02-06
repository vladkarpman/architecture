package io.vladkarpman.domain.repository;


import io.reactivex.annotations.NonNull;

public interface RepositoryModule {
    @NonNull
    Repository provideRepository();
}
