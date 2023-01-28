package ru.reviewer.database.shows

import ru.reviewer.features.shows.models.CreateShowRequest
import ru.reviewer.features.shows.models.CreateShowResponse
import java.util.*

@kotlinx.serialization.Serializable
data class ShowDTO(
    val id: String,
    val title: String,
    val date: String,
    val length: String,
    val genre: String,
    val imdb: String,
    val description: String,
    val imageUrl: String
)

fun CreateShowRequest.mapToShowDTO(): ShowDTO =
    ShowDTO(
        id = UUID.randomUUID().toString(),
        title = title,
        date = date,
        length = length,
        genre = genre,
        imdb = imdb,
        description = description,
        imageUrl = imageUrl
    )

fun ShowDTO.mapToCreateShowResponse(): CreateShowResponse =
    CreateShowResponse(
        id = id,
        title = title,
        date = date,
        length = length,
        genre = genre,
        imdb = imdb,
        description = description,
        imageUrl = imageUrl
    )