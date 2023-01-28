package ru.reviewer.features.games.models

@kotlinx.serialization.Serializable
data class CreateGameRequest(
    val title: String,
    val date: String,
    val developer: String,
    val genre: String,
    val metacritic: String,
    val description: String,
    val imageUrl: String
)

@kotlinx.serialization.Serializable
data class CreateGameResponse(
    val id: String,
    val title: String,
    val date: String,
    val developer: String,
    val genre: String,
    val metacritic: String,
    val description: String,
    val imageUrl: String
)