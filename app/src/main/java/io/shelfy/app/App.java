package io.shelfy.app;

import android.app.Application;

import io.reactivex.annotations.NonNull;
import io.shelfy.domain.CommonApplication;
import io.shelfy.domain.DomainModule;
import io.shelfy.domain.DomainModuleImpl;
import io.shelfy.realmdb.RealmDBModule;
import io.shelfy.restapi.RestApiModule;
import io.shelfy.repository.LocalDataSource;
import io.shelfy.repository.RemoteDataSource;
import io.shelfy.repository.RepositoryModuleImpl;


public class App extends Application implements CommonApplication {

    private DomainModule domainModule;

    @NonNull
    @Override
    public DomainModule getDomainModule() {
        if (domainModule == null) {
            final RemoteDataSource remoteDataSource = new RestApiModule().provideRemoteDataSource();
            final LocalDataSource localDataSource = new RealmDBModule(this).provideLocalDataSource();
            final RepositoryModuleImpl repositoryModule = new RepositoryModuleImpl(localDataSource, remoteDataSource);
            domainModule = new DomainModuleImpl(repositoryModule.provideRepository());
        }
        return domainModule;
    }
}
