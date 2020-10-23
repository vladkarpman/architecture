package io.shelfy.architecture.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vladk.architecture.domain.model.Movie
import com.vladk.architecture.domain.usecase.GetMoviesByQueryUseCase
import com.vladk.architecture.domain.usecase.GetMoviesResult
import com.vladk.architecture.domain.usecase.GetPopularMoviesUseCase

class MoviesViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getMoviesByQueryUseCase: GetMoviesByQueryUseCase
) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> = _moviesLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    init {
        loadPopularMovies()
    }

    private fun loadPopularMovies() {
        getPopularMoviesUseCase.getMovies { result ->
            when (result) {
                is GetMoviesResult.Success -> {
                    _moviesLiveData.postValue(result.movies)
                }
                is GetMoviesResult.Error -> {
                    _errorLiveData.postValue(result.message)
                }
            }
        }
    }

    fun search(query: String) {
        getMoviesByQueryUseCase.getMovies(query) { result ->
            when (result) {
                is GetMoviesResult.Success -> {
                    _moviesLiveData.postValue(result.movies)
                }
                is GetMoviesResult.Error -> {
                    _errorLiveData.postValue(result.message)
                }
            }
        }
    }
}
