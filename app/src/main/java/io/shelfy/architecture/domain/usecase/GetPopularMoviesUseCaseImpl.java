package io.shelfy.architecture.domain.usecase;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.shelfy.architecture.domain.Repository;
import io.shelfy.architecture.domain.entity.Movie;

public class GetPopularMoviesUseCaseImpl implements GetPopularMoviesUseCase{

    @NonNull
    private final Repository moviesRepository;

    public GetPopularMoviesUseCaseImpl(@NonNull Repository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @Override
    public Single<List<Movie>> getMovies() {
        return moviesRepository.getPopularMovies();
    }
}
