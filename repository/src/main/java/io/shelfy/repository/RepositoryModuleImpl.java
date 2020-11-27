package io.shelfy.repository;

import androidx.annotation.NonNull;

import io.shelfy.domain.Repository;
import io.shelfy.domain.RepositoryModule;

public class RepositoryModuleImpl implements RepositoryModule {

    private Repository repository;

    @NonNull
    private final LocalDataSource localDataSource;

    @NonNull
    private final RemoteDataSource remoteDataSource;

    public RepositoryModuleImpl(@NonNull LocalDataSource localDataSource,
                                @NonNull RemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    @NonNull
    public Repository provideRepository() {
        if (repository == null) {
            repository = new RepositoryImpl(localDataSource, remoteDataSource);
        }
        return repository;
    }
}
