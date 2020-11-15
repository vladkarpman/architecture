package io.shelfy.domain.usecase.getmovies;

import java.util.List;

import io.reactivex.Single;
import io.shelfy.domain.entity.Movie;

public interface GetPopularMoviesUseCase {
    Single<List<Movie>> getMovies();
}
