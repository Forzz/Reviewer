package ru.reviewer.features.movies.models

@kotlinx.serialization.Serializable
data class CreateMovieRequest(
    val title: String,
    val date: String,
    val length: String,
    val genre: String,
    val imdb: String,
    val description: String,
    val imageUrl: String
)

@kotlinx.serialization.Serializable
data class CreateMovieResponse(
    val id: String,
    val title: String,
    val date: String,
    val length: String,
    val genre: String,
    val imdb: String,
    val description: String,
    val imageUrl: String
)