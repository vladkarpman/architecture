package io.vladkarpman.domain.usecase.getmoviesbyquery;


import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.internal.functions.Functions;
import io.vladkarpman.domain.repository.Repository;
import io.vladkarpman.domain.entity.Movie;

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
