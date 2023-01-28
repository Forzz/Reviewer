package ru.reviewer.database.games

import ru.reviewer.features.games.models.CreateGameRequest
import ru.reviewer.features.games.models.CreateGameResponse
import java.util.*

@kotlinx.serialization.Serializable
data class GameDTO(
    val id: String,
    val title: String,
    val date: String,
    val developer: String,
    val genre: String,
    val metacritic: String,
    val description: String,
    val imageUrl: String
)

fun CreateGameRequest.mapToGameDTO(): GameDTO =
    GameDTO(
        id = UUID.randomUUID().toString(),
        title = title,
        date = date,
        developer = developer,
        genre = genre,
        metacritic = metacritic,
        description = description,
        imageUrl = imageUrl
    )

fun GameDTO.mapToCreateGameResponse(): CreateGameResponse =
    CreateGameResponse(
        id = id,
        title = title,
        date = date,
        developer = developer,
        genre = genre,
        metacritic = metacritic,
        description = description,
        imageUrl = imageUrl
    )