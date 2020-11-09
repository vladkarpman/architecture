package io.shelfy.architecture.domain.usecase.getmoviesbyquery;

import java.util.List;

import io.reactivex.Single;
import io.shelfy.architecture.domain.entity.Movie;

public interface GetMoviesByQueryUseCase {

    Single<List<Movie>> getMovies(String query);
}
