package io.shelfy.domain.usecase.getmovies;


import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.shelfy.domain.repository.Repository;
import io.shelfy.domain.entity.Movie;

public class GetPopularMoviesUseCaseImpl implements GetPopularMoviesUseCase {

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
