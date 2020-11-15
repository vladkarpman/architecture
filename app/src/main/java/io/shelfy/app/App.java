package io.shelfy.app;

import android.app.Application;

import androidx.core.util.Supplier;

import io.shelfy.domain.DomainComponent;
import io.shelfy.localdatasource.LocalDataSourceComponent;
import io.shelfy.remotedatasource.RemoteDataSourceComponent;
import io.shelfy.repository.LocalDataSource;
import io.shelfy.repository.RemoteDataSource;
import io.shelfy.repository.RepositoryComponent;


public class App extends Application implements Supplier<DomainComponent> {

    private DomainComponent domainComponent;

    @Override
    public DomainComponent get() {
        if (domainComponent == null) {
            final RemoteDataSource remoteDataSource = new RemoteDataSourceComponent().provideRemoteDataSource();
            final LocalDataSource localDataSource = new LocalDataSourceComponent(this).provideLocalDataSource();
            final RepositoryComponent repositoryComponent = new RepositoryComponent(localDataSource, remoteDataSource);
            domainComponent = new DomainComponent(repositoryComponent.provideRepository());
        }
        return domainComponent;
    }
}
