package io.shelfy.localdatasource;

import android.app.Application;

import androidx.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.shelfy.repository.LocalDataSource;

public class LocalDataSourceComponent {

    // DB
    public static final String DB_FILE_NAME = "movies_db";
    public static long SCHEMA_VERSION = 1;

    private LocalDataSource localDataSource;
    private RealmConfiguration realmConfiguration;

    public LocalDataSourceComponent(Application application) {
        // need to init realm
        Realm.init(application);
    }

    @NonNull
    public LocalDataSource provideLocalDataSource() {
        if (localDataSource == null) {
            localDataSource = new RealmDB(provideRealmConfiguration());
        }
        return localDataSource;
    }

    @NonNull
    private RealmConfiguration provideRealmConfiguration() {
        if (realmConfiguration == null) {
            realmConfiguration = new RealmConfiguration.Builder()
                    .name(DB_FILE_NAME)
                    .schemaVersion(SCHEMA_VERSION)
                    .build();
        }
        return realmConfiguration;
    }
}
