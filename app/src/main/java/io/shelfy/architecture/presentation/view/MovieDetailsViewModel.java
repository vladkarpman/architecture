package io.shelfy.architecture.presentation.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import io.shelfy.architecture.domain.entity.MovieVideo;
import io.shelfy.architecture.domain.usecase.GetMovieTrailerUseCase;

import static androidx.lifecycle.LiveDataReactiveStreams.fromPublisher;

public class MovieDetailsViewModel extends ViewModel {

    @NonNull
    private final GetMovieTrailerUseCase getMovieTrailerUseCase;

    public MovieDetailsViewModel(@NonNull GetMovieTrailerUseCase getMovieTrailerUseCase) {
        this.getMovieTrailerUseCase = getMovieTrailerUseCase;
    }

    public LiveData<MovieVideo> loadTrailer(int movieId) {
        return fromPublisher(getMovieTrailerUseCase.getTrailer(movieId).toFlowable());
    }
}
