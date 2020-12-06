package io.shelfy.presentation.details.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.Maybe;
import io.shelfy.domain.entity.Movie;
import io.shelfy.domain.entity.MovieVideo;
import io.shelfy.domain.usecase.getmoviebyid.GetMovieByIdUseCase;
import io.shelfy.domain.usecase.getmovietrailer.GetMovieTrailerUseCase;
import io.shelfy.presentation.common.viewmodel.BaseViewModel;

import static androidx.lifecycle.LiveDataReactiveStreams.fromPublisher;

public class MovieDetailsViewModel extends BaseViewModel {

    @NonNull
    private final GetMovieTrailerUseCase getMovieTrailerUseCase;

    @NonNull
    private final GetMovieByIdUseCase getMovieByIdUseCase;

    public MovieDetailsViewModel(@NonNull GetMovieTrailerUseCase getMovieTrailerUseCase,
                                 @NonNull GetMovieByIdUseCase getMovieByIdUseCase) {
        this.getMovieTrailerUseCase = getMovieTrailerUseCase;
        this.getMovieByIdUseCase = getMovieByIdUseCase;
    }

    public Maybe<MovieVideo> loadTrailer(int movieId) {
        return getMovieTrailerUseCase.getTrailer(movieId);
    }

    public LiveData<Movie> getMovie(int movieId) {
        return fromPublisher(getMovieByIdUseCase.getMovie(movieId).toFlowable());
    }
}
