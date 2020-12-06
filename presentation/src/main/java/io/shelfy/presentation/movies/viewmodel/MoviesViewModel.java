package io.shelfy.presentation.movies.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.shelfy.domain.entity.Movie;
import io.shelfy.domain.usecase.getmoviesbyquery.GetMoviesByQueryUseCase;
import io.shelfy.domain.usecase.getmovies.GetPopularMoviesUseCase;
import io.shelfy.presentation.common.viewmodel.BaseViewModel;

public class MoviesViewModel extends BaseViewModel {

    @NonNull
    private final GetPopularMoviesUseCase getPopularMoviesUseCase;

    @NonNull
    private final GetMoviesByQueryUseCase getMoviesByQueryUseCase;

    private final MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();

    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    private Disposable searchDisposable = Disposables.disposed();

    public MoviesViewModel(@NonNull GetPopularMoviesUseCase getPopularMoviesUseCase,
                           @NonNull GetMoviesByQueryUseCase getMoviesByQueryUseCase) {
        this.getPopularMoviesUseCase = getPopularMoviesUseCase;
        this.getMoviesByQueryUseCase = getMoviesByQueryUseCase;

        loadPopularMovies();
    }

    public LiveData<String> getErrorMessages() {
        return errorLiveData;
    }

    public LiveData<List<Movie>> getMovies() {
        return moviesLiveData;
    }

    public void loadPopularMovies() {
        onDestroyDisposables.add(getPopularMoviesUseCase.getMovies()
                .subscribe(moviesLiveData::postValue, error -> errorLiveData.postValue(error.getMessage())));
    }

    public void search(@NonNull String query) {
        onDestroyDisposables.remove(searchDisposable);
        searchDisposable = getMoviesByQueryUseCase.getMovies(query)
                .subscribe(moviesLiveData::postValue, error -> {
                    errorLiveData.postValue(error.getMessage());
                });
        onDestroyDisposables.add(searchDisposable);
    }
}
