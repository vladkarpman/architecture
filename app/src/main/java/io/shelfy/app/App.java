package io.shelfy.app;

import io.reactivex.annotations.NonNull;
import io.shelfy.domain.DomainModule;
import io.shelfy.domain.DomainModuleImpl;
import io.shelfy.presentation.common.CommonApplication;
import io.shelfy.presentation.common.component.ApplicationComponent;
import io.shelfy.realmdb.RealmDBModule;
import io.shelfy.repository.RepositoryModuleImpl;
import io.shelfy.repository.datasource.local.LocalDataSource;
import io.shelfy.repository.datasource.remote.RemoteDataSource;
import io.shelfy.restapi.RestApiModule;

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
