package io.shelfy.repository;

import androidx.annotation.NonNull;

public interface RemoteDataSourceModule {
    @NonNull
    RemoteDataSource provideRemoteDataSource();
}
