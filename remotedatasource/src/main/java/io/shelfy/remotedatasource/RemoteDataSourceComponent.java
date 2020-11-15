package io.shelfy.remotedatasource;

import androidx.annotation.NonNull;

import io.shelfy.repository.RemoteDataSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static io.shelfy.remotedatasource.Constants.*;

public class RemoteDataSourceComponent {

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
