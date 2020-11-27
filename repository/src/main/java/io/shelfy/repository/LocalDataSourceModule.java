package io.shelfy.repository;

import androidx.annotation.NonNull;

import io.shelfy.repository.LocalDataSource;

public interface LocalDataSourceModule {
    @NonNull
    LocalDataSource provideLocalDataSource();
}
