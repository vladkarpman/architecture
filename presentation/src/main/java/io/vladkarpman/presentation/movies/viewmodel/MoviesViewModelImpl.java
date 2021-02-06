package io.vladkarpman.presentation.movies.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.vladkarpman.domain.entity.Movie;
import io.vladkarpman.domain.usecase.getmoviesbyquery.GetMoviesByQueryUseCase;
import io.vladkarpman.domain.usecase.getmovies.GetPopularMoviesUseCase;
import io.vladkarpman.presentation.common.viewmodel.BaseViewModel;

public class MoviesViewModelImpl extends BaseViewModel implements MoviesViewModel {

    @NonNull
    private final GetPopularMoviesUseCase getPopularMoviesUseCase;

    @NonNull
    private final GetMoviesByQueryUseCase getMoviesByQueryUseCase;

    private final MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();

    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    private Disposable searchDisposable = Disposables.disposed();

    private Disposable loadMoviesDisposable = Disposables.disposed();

    public MoviesViewModelImpl(@NonNull GetPopularMoviesUseCase getPopularMoviesUseCase,
                               @NonNull GetMoviesByQueryUseCase getMoviesByQueryUseCase) {
        this.getPopularMoviesUseCase = getPopularMoviesUseCase;
        this.getMoviesByQueryUseCase = getMoviesByQueryUseCase;

        loadPopularMovies();
    }

    @Override
    public LiveData<String> getErrorMessages() {
        return errorLiveData;
    }

    @Override
    public LiveData<List<Movie>> getMovies() {
        return moviesLiveData;
    }

    @Override
    public void loadPopularMovies() {
        onDestroyDisposables.remove(loadMoviesDisposable);
        loadMoviesDisposable = getPopularMoviesUseCase.getMovies()
                .subscribe(moviesLiveData::postValue, error -> errorLiveData.postValue(error.getMessage()));
        onDestroyDisposables.add(loadMoviesDisposable);
    }

    @Override
    public void search(@NonNull String query) {
        onDestroyDisposables.remove(searchDisposable);
        searchDisposable = getMoviesByQueryUseCase.getMovies(query)
                .subscribe(moviesLiveData::postValue, error -> errorLiveData.postValue(error.getMessage()));
        onDestroyDisposables.add(searchDisposable);
    }
}
