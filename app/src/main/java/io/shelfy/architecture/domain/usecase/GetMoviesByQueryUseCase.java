package io.shelfy.architecture.domain.usecase;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.shelfy.architecture.domain.entity.Movie;

public interface GetMoviesByQueryUseCase {

    Single<List<Movie>> getMovies(String query);
}
