package io.shelfy.repository;

import androidx.annotation.NonNull;

import io.shelfy.domain.Repository;

public class RepositoryComponent {

    private Repository repository;

    @NonNull
    private final LocalDataSource localDataSource;

    @NonNull
    private final RemoteDataSource remoteDataSource;

    public RepositoryComponent(@NonNull LocalDataSource localDataSource,
                               @NonNull RemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @NonNull
    public Repository provideRepository() {
        if (repository == null) {
            repository = new RepositoryImpl(localDataSource, remoteDataSource);
        }
        return repository;
    }
}
