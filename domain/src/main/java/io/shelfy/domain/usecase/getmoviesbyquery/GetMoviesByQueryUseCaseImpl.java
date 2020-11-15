package io.shelfy.domain.usecase.getmoviesbyquery;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.internal.functions.Functions;
import io.shelfy.domain.Repository;
import io.shelfy.domain.entity.Movie;

public class GetMoviesByQueryUseCaseImpl implements GetMoviesByQueryUseCase {

    @NonNull
    private final Repository moviesRepository;

    public GetMoviesByQueryUseCaseImpl(@NonNull Repository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @Override
    public Single<List<Movie>> getMovies(String query) {
        return moviesRepository.getPopularMovies()
                .flattenAsObservable(Functions.identity())
                .filter(movie -> movie.getDescription().contains(query)
                        || movie.getTitle().contains(query))
                .toList();
    }
}
