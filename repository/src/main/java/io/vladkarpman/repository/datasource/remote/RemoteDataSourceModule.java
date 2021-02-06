package io.vladkarpman.repository.datasource.remote;

import androidx.annotation.NonNull;

public interface RemoteDataSourceModule {
    @NonNull
    RemoteDataSource provideRemoteDataSource();
}
