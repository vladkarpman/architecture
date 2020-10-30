package io.shelfy.architecture.common;

import io.shelfy.architecture.ApiConstants;
import io.shelfy.architecture.data.datasource.remote.MoviesApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {

    public static MoviesApi api;

    public RestService() {
        api = createRetrofit().create(MoviesApi.class);
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
