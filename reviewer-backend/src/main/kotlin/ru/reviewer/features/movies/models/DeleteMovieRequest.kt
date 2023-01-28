package ru.reviewer.features.movies.models

@kotlinx.serialization.Serializable
data class DeleteMovieRequest(
    val id: String
)

@kotlinx.serialization.Serializable
data class DeleteMovieResponse(
    val message: String
)