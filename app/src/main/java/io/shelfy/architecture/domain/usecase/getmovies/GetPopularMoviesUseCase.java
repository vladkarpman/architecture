package io.shelfy.architecture.domain.usecase.getmovies;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.shelfy.architecture.domain.entity.Movie;

public interface GetPopularMoviesUseCase {
    Single<List<Movie>> getMovies();
}
