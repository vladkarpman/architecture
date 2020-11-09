package io.shelfy.architecture.domain.usecase.getmoviesbyquery;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.shelfy.architecture.data.repository.Repository;
import io.shelfy.architecture.domain.entity.Movie;

public class GetMoviesByQueryUseCaseImpl implements GetMoviesByQueryUseCase {

    @NonNull
    private final Repository moviesRepository;

    public GetMoviesByQueryUseCaseImpl(@NonNull Repository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @Override
    public Single<List<Movie>> getMovies(String query) {
        return moviesRepository.getMoviesStartWith(query);
    }
}
