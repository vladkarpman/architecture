package io.shelfy.architecture.domain.usecase;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.shelfy.architecture.domain.MoviesRepository;
import io.shelfy.architecture.domain.entity.Movie;

public class GetMoviesByQueryUseCaseImpl implements GetMoviesByQueryUseCase {

    @NonNull
    private final MoviesRepository moviesRepository;

    public GetMoviesByQueryUseCaseImpl(@NonNull MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @Override
    public Single<List<Movie>> getMovies(String query) {
        return moviesRepository.getMoviesStartWith(query);
    }
}
