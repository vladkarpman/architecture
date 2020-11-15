package io.shelfy.remotedatasource;

import androidx.annotation.NonNull;

import io.shelfy.repository.RemoteDataSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSourceComponent {

    // API
    private static final String SERVER_API_KEY = "0a5b22b08ce1ce408ab636a7c674b85e";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342";
    private static final String BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w780";
    private static final String YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v=";

    private Retrofit retrofit;
    private RemoteDataSource remoteDataSource;
    private MoviesApi moviesApi;

    @NonNull
    private Retrofit provideRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
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
    public RemoteDataSource provideRemoteDataSource() {
        if (remoteDataSource == null) {
            remoteDataSource = new RestApi(
                    provideMoviesApi(),
                    SERVER_API_KEY
            );
        }
        return remoteDataSource;
    }
}
