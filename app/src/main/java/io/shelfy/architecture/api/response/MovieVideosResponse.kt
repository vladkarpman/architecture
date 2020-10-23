package io.shelfy.architecture.api.response

import com.google.gson.annotations.SerializedName

data class MovieVideosResponse(
    @SerializedName("results")
    val videos: List<MovieVideoJson>
)
