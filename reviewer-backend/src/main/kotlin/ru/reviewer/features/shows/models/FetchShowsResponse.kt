package ru.reviewer.features.shows.models

data class FetchMoviesResponse(
    val movies: List<ShowResponse>
)

data class ShowResponse(
    val id: String,
    val title: String,
    val date: String,
    val length: String,
    val genre: String,
    val imdb: String,
    val description: String,
    val imageUrl: String
)

