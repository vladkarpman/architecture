package io.shelfy.architecture.api.response

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(
    @SerializedName("results")
    val movies: List<MovieJson>
)
