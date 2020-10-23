package io.shelfy.architecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vladk.architecture.domain.model.Movie
import com.vladk.architecture.domain.usecase.GetMovieTrailerUseCase

class MovieDetailsViewModelFactory(
    private val movie: Movie,
    private val getMovieTrailerUseCase: GetMovieTrailerUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(movie, getMovieTrailerUseCase) as T
    }
}
