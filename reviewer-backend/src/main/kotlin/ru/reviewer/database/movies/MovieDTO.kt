package ru.reviewer.database.movies

import ru.reviewer.features.movies.models.CreateMovieRequest
import ru.reviewer.features.movies.models.CreateMovieResponse
import java.util.*

@kotlinx.serialization.Serializable
data class MovieDTO(
    val id: String,
    val title: String,
    val date: String,
    val length: String,
    val genre: String,
    val imdb: String,
    val description: String,
    val imageUrl: String
)

fun CreateMovieRequest.mapToMovieDTO(): MovieDTO =
    MovieDTO(
        id = UUID.randomUUID().toString(),
        title = title,
        date = date,
        length = length,
        genre = genre,
        imdb = imdb,
        description = description,
        imageUrl = imageUrl
    )

fun MovieDTO.mapToCreateMovieResponse(): CreateMovieResponse =
    CreateMovieResponse(
        id = id,
        title = title,
        date = date,
        length = length,
        genre = genre,
        imdb = imdb,
        description = description,
        imageUrl = imageUrl
    )