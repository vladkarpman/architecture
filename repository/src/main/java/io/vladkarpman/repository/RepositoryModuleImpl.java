package io.vladkarpman.repository;

import androidx.annotation.NonNull;

import io.vladkarpman.domain.repository.Repository;
import io.vladkarpman.domain.repository.RepositoryModule;
import io.vladkarpman.repository.datasource.local.LocalDataSource;
import io.vladkarpman.repository.datasource.remote.RemoteDataSource;

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
