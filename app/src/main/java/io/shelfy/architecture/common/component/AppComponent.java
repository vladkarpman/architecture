package io.shelfy.architecture.common.component;

import android.app.Application;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import io.realm.RealmConfiguration;
import io.shelfy.architecture.data.repository.Repository;
import io.shelfy.architecture.data.repository.RepositoryImpl;
import io.shelfy.architecture.data.source.local.LocalDataSource;
import io.shelfy.architecture.data.source.local.LocalDataSourceImpl;
import io.shelfy.architecture.data.source.remote.MoviesApi;
import io.shelfy.architecture.data.source.remote.RemoteDataSource;
import io.shelfy.architecture.data.source.remote.RemoteDataSourceImpl;
import io.shelfy.architecture.domain.usecase.getmoviebyid.GetMovieByIdUseCase;
import io.shelfy.architecture.domain.usecase.getmoviebyid.GetMovieByIdUseCaseImpl;
import io.shelfy.architecture.domain.usecase.getmovies.GetPopularMoviesUseCase;
import io.shelfy.architecture.domain.usecase.getmovies.GetPopularMoviesUseCaseImpl;
import io.shelfy.architecture.domain.usecase.getmoviesbyquery.GetMoviesByQueryUseCase;
import io.shelfy.architecture.domain.usecase.getmoviesbyquery.GetMoviesByQueryUseCaseImpl;
import io.shelfy.architecture.domain.usecase.getmovietrailer.GetMovieTrailerUseCase;
import io.shelfy.architecture.domain.usecase.getmovietrailer.GetMovieTrailerUseCaseImpl;
import io.shelfy.architecture.util.NetworkConnectivityHelper;
import io.shelfy.architecture.util.NetworkConnectivityHelperImpl;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static io.shelfy.architecture.Constants.BASE_URL;
import static io.shelfy.architecture.Constants.DB_FILE_NAME;
import static io.shelfy.architecture.Constants.SCHEMA_VERSION;
import static io.shelfy.architecture.Constants.SERVER_API_KEY;

public class AppComponent {

    @NonNull
    private final Application application;

    private Repository repository;
    private Retrofit retrofit;
    private RemoteDataSource remoteDataSource;
    private MoviesApi moviesApi;
    private LocalDataSource localDataSource;
    private GetPopularMoviesUseCase getPopularMoviesUseCase;
    private GetMoviesByQueryUseCase getMoviesByQueryUseCase;
    private GetMovieTrailerUseCase getMovieTrailerUseCase;
    private GetMovieByIdUseCase getMovieByIdUseCase;
    private NetworkConnectivityHelper networkConnectivityHelper;
    private RealmConfiguration realmConfiguration;

    public AppComponent(@NonNull Application application) {
        this.application = application;
    }

    @NonNull
    public Repository provideRepository() {
        if (repository == null) {
            repository = new RepositoryImpl(provideLocalDataSource(),
                    provideRemoteDataSource(),
                    provideNetworkConnectivityHelper());
        }
        return repository;
    }

    @NotNull
    private NetworkConnectivityHelper provideNetworkConnectivityHelper() {
        if (networkConnectivityHelper == null) {
            networkConnectivityHelper = new NetworkConnectivityHelperImpl(application);
        }
        return networkConnectivityHelper;
    }

    @NonNull
    private Retrofit provideRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
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

    @NonNull
    private MoviesApi provideMoviesApi() {
        if (moviesApi == null) {
            moviesApi = provideRetrofit().create(MoviesApi.class);
        }
        return moviesApi;
    }

    @NonNull
    private RemoteDataSource provideRemoteDataSource() {
        if (remoteDataSource == null) {
            remoteDataSource = new RemoteDataSourceImpl(
                    provideMoviesApi(),
                    SERVER_API_KEY
            );
        }
        return remoteDataSource;
    }

    @NonNull
    private LocalDataSource provideLocalDataSource() {
        if (localDataSource == null) {
            localDataSource = new LocalDataSourceImpl(provideRealmConfiguration());
        }
        return localDataSource;
    }

    @NonNull
    public GetPopularMoviesUseCase provideGetPopularMoviesUseCase() {
        if (getPopularMoviesUseCase == null) {
            getPopularMoviesUseCase = new GetPopularMoviesUseCaseImpl(provideRepository());
        }
        return getPopularMoviesUseCase;
    }

    @NonNull
    public GetMoviesByQueryUseCase provideGetMoviesByQueryUseCase() {
        if (getMoviesByQueryUseCase == null) {
            getMoviesByQueryUseCase = new GetMoviesByQueryUseCaseImpl(provideRepository());
        }
        return getMoviesByQueryUseCase;
    }

    @NonNull
    public GetMovieTrailerUseCase provideGetMovieTrailerUseCase() {
        if (getMovieTrailerUseCase == null) {
            getMovieTrailerUseCase = new GetMovieTrailerUseCaseImpl(provideRepository());
        }
        return getMovieTrailerUseCase;
    }

    @NonNull
    public GetMovieByIdUseCase provideGetMovieByIdUseCase() {
        if (getMovieByIdUseCase == null) {
            getMovieByIdUseCase = new GetMovieByIdUseCaseImpl(provideRepository());
        }
        return getMovieByIdUseCase;
    }
}
