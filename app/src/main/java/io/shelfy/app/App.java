package io.shelfy.app;

import android.app.Application;

import androidx.core.util.Supplier;

import io.shelfy.domain.DomainModule;
import io.shelfy.domain.DomainModuleImpl;
import io.shelfy.localdatasource.LocalDataSourceModuleImpl;
import io.shelfy.remotedatasource.RemoteDataSourceComponent;
import io.shelfy.repository.LocalDataSource;
import io.shelfy.repository.RemoteDataSource;
import io.shelfy.repository.RepositoryModuleImpl;


public class App extends Application implements Supplier<DomainModule> {

    private DomainModule domainModule;

    @Override
    public DomainModule get() {
        if (domainModule == null) {
            final RemoteDataSource remoteDataSource = new RemoteDataSourceComponent().provideRemoteDataSource();
            final LocalDataSource localDataSource = new LocalDataSourceModuleImpl(this).provideLocalDataSource();
            final RepositoryModuleImpl repositoryModule = new RepositoryModuleImpl(localDataSource, remoteDataSource);
            domainModule = new DomainModuleImpl(repositoryModule.provideRepository());
        }
        return domainModule;
    }
}
