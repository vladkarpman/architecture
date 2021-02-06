package io.vladkarpman.repository.datasource.local;

import androidx.annotation.NonNull;

public interface LocalDataSourceModule {
    @NonNull
    LocalDataSource provideLocalDataSource();
}
