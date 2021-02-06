package io.vladkarpman.app;

import io.reactivex.annotations.NonNull;
import io.vladkarpman.domain.DomainModule;
import io.vladkarpman.domain.DomainModuleImpl;
import io.vladkarpman.presentation.common.CommonApplication;
import io.vladkarpman.presentation.common.component.ApplicationComponent;
import io.vladkarpman.realmdb.RealmDBModule;
import io.vladkarpman.repository.RepositoryModuleImpl;
import io.vladkarpman.repository.datasource.local.LocalDataSource;
import io.vladkarpman.repository.datasource.remote.RemoteDataSource;
import io.vladkarpman.restapi.RestApiModule;

public class App extends CommonApplication {

    @NonNull
    @Override
    public ApplicationComponent createApplicationComponent() {
        final RemoteDataSource remoteDataSource = new RestApiModule().provideRemoteDataSource();
        final LocalDataSource localDataSource = new RealmDBModule(this).provideLocalDataSource();
        final RepositoryModuleImpl repositoryModule = new RepositoryModuleImpl(localDataSource, remoteDataSource);
        final DomainModule domainModule = new DomainModuleImpl(repositoryModule.provideRepository());
        return () -> domainModule;
    }
}
