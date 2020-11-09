package io.shelfy.architecture.presentation.details;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.shelfy.architecture.domain.entity.Movie;
import io.shelfy.architecture.domain.entity.MovieVideo;
import io.shelfy.architecture.domain.usecase.GetMovieByIdUseCase;
import io.shelfy.architecture.domain.usecase.GetMovieTrailerUseCase;
import io.shelfy.architecture.domain.usecase.GetPopularMoviesUseCase;

import static androidx.lifecycle.LiveDataReactiveStreams.fromPublisher;

public class MovieDetailsViewModel extends ViewModel {

    @NonNull
    private final GetMovieTrailerUseCase getMovieTrailerUseCase;

    @NonNull
    private final GetMovieByIdUseCase getMovieByIdUseCase;

    public MovieDetailsViewModel(@NonNull GetMovieTrailerUseCase getMovieTrailerUseCase,
                                 @NonNull GetMovieByIdUseCase getMovieByIdUseCase) {
        this.getMovieTrailerUseCase = getMovieTrailerUseCase;
        this.getMovieByIdUseCase = getMovieByIdUseCase;
    }

    public LiveData<MovieVideo> loadTrailer(int movieId) {
        return fromPublisher(getMovieTrailerUseCase.getTrailer(movieId).toFlowable());
    }

    public LiveData<Movie> getMovie(int movieId) {
        return fromPublisher(getMovieByIdUseCase.getMovie(movieId).toFlowable());
    }

}
