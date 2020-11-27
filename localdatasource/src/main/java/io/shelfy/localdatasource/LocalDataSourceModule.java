package io.shelfy.localdatasource;

import androidx.annotation.NonNull;

import io.shelfy.repository.LocalDataSource;

interface LocalDataSourceModule {
    @NonNull
    LocalDataSource provideLocalDataSource();
}
