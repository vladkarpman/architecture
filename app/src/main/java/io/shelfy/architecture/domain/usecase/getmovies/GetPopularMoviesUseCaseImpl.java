package io.shelfy.architecture.domain.usecase.getmovies;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.Single;
import io.shelfy.architecture.data.repository.Repository;
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
