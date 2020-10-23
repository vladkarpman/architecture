package io.shelfy.architecture.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.shelfy.architecture.domain.model.Movie
import io.shelfy.architecture.domain.model.MovieVideo
import io.shelfy.architecture.domain.usecase.GetMovieTrailerUseCase
import io.shelfy.architecture.domain.usecase.GetTrailerResult

class MovieDetailsViewModel(
    private val movie: Movie,
    private val getMovieTrailerUseCase: GetMovieTrailerUseCase
) : ViewModel() {

    private val _movieLivaData = MutableLiveData<Movie>()
    val movieLiveData: LiveData<Movie> = _movieLivaData

    private val _movieTrailerLiveData = MutableLiveData<MovieVideo>()
    val movieTrailerLiveData: LiveData<MovieVideo> = _movieTrailerLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    init {
        _movieLivaData.postValue(movie)
    }

    fun loadTrailer() {
        getMovieTrailerUseCase.getTrailer(movie.id) { result ->
            when (result) {
                is GetTrailerResult.Success -> {
                    _movieTrailerLiveData.postValue(result.movieVideo)
                }
                is GetTrailerResult.Error -> {
                    _errorLiveData.postValue(result.message)
                }
            }
        }
    }
}
