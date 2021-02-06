package io.vladkarpman.domain.usecase.getmovies;

import java.util.List;

import io.reactivex.Single;
import io.vladkarpman.domain.entity.Movie;

public interface GetPopularMoviesUseCase {
    Single<List<Movie>> getMovies();
}
