package ru.reviewer.features.movies.models

@kotlinx.serialization.Serializable
data class FetchMoviesResponse(
    val movies: List<MovieResponse>
)

@kotlinx.serialization.Serializable
data class MovieResponse(
    val id: String,
    val title: String,
    val date: String,
    val length: String,
    val genre: String,
    val imdb: String,
    val description: String,
    val imageUrl: String
)

