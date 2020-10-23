package io.shelfy.architecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vladk.architecture.domain.usecase.GetMoviesByQueryUseCase
import com.vladk.architecture.domain.usecase.GetPopularMoviesUseCase

class MoviesViewModelFactory(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getMoviesByQueryUseCase: GetMoviesByQueryUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(getPopularMoviesUseCase, getMoviesByQueryUseCase) as T
    }
}
