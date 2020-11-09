package io.shelfy.architecture.common.component;

import android.app.Application;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import io.shelfy.architecture.ApiConstants;
import io.shelfy.architecture.common.Mapper;
import io.shelfy.architecture.data.repository.LocalDataSource;
import io.shelfy.architecture.data.repository.RemoteDataSource;
import io.shelfy.architecture.data.repository.RepositoryImpl;
import io.shelfy.architecture.data.source.local.LocalDataSourceImpl;
import io.shelfy.architecture.data.source.remote.MoviesApi;
import io.shelfy.architecture.data.source.remote.RemoteDataSourceImpl;
import io.shelfy.architecture.data.source.remote.mappers.MovieMapper;
import io.shelfy.architecture.data.source.remote.mappers.MovieVideoMapper;
import io.shelfy.architecture.data.source.remote.response.MovieJson;
import io.shelfy.architecture.data.source.remote.response.MovieVideoJson;
import io.shelfy.architecture.domain.Repository;
import io.shelfy.architecture.domain.entity.Movie;
import io.shelfy.architecture.domain.entity.MovieVideo;
import io.shelfy.architecture.domain.usecase.GetMovieByIdUseCase;
import io.shelfy.architecture.domain.usecase.GetMovieByIdUseCaseImpl;
import io.shelfy.architecture.domain.usecase.GetMovieTrailerUseCase;
import io.shelfy.architecture.domain.usecase.GetMovieTrailerUseCaseImpl;
import io.shelfy.architecture.domain.usecase.GetMoviesByQueryUseCase;
import io.shelfy.architecture.domain.usecase.GetMoviesByQueryUseCaseImpl;
import io.shelfy.architecture.domain.usecase.GetPopularMoviesUseCase;
import io.shelfy.architecture.domain.usecase.GetPopularMoviesUseCaseImpl;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppComponent {

    @NonNull
    private final Application application;

    private Repository repository;
    private Retrofit retrofit;
    private RemoteDataSource remoteDataSource;
    private MoviesApi moviesApi;
    private Mapper<MovieJson, Movie> movieMapper;
    private Mapper<MovieVideoJson, MovieVideo> movieVideoMapper;
    private LocalDataSource localDataSource;
    private GetPopularMoviesUseCase getPopularMoviesUseCase;
    private GetMoviesByQueryUseCase getMoviesByQueryUseCase;
    private GetMovieTrailerUseCase getMovieTrailerUseCase;
    private GetMovieByIdUseCase getMovieByIdUseCase;

    public AppComponent(@NonNull Application application) {
        this.application = application;
    }

    @NonNull
    public Repository provideRepository() {
        if (repository == null) {
            repository = new RepositoryImpl(provideLocalDataSource(), provideRemoteDataSource());
        }
        return repository;
    }

    @NonNull
    private Retrofit provideRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
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
                    ApiConstants.SERVER_API_KEY,
                    provideMovieMapper(),
                    provideMovieVideoMapper()
            );
        }
        return remoteDataSource;
    }

    @NonNull
    private Mapper<MovieJson, Movie> provideMovieMapper() {
        if (movieMapper == null) {
            movieMapper = new MovieMapper();
        }
        return movieMapper;
    }

    @NotNull
    private Mapper<MovieVideoJson, MovieVideo> provideMovieVideoMapper() {
        if (movieVideoMapper == null) {
            movieVideoMapper = new MovieVideoMapper();
        }
        return movieVideoMapper;
    }

    @NonNull
    private LocalDataSource provideLocalDataSource() {
        if (localDataSource == null) {
            localDataSource = new LocalDataSourceImpl();
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
