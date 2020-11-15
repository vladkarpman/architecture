package io.shelfy.domain.usecase.getmoviesbyquery;

import java.util.List;

import io.reactivex.Single;
import io.shelfy.domain.entity.Movie;

public interface GetMoviesByQueryUseCase {

    Single<List<Movie>> getMovies(String query);
}
