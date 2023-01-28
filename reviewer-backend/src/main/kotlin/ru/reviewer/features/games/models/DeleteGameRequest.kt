package ru.reviewer.features.games.models

@kotlinx.serialization.Serializable
data class DeleteGameRequest(
    val id: String
)

@kotlinx.serialization.Serializable
data class DeleteGameResponse(
    val message: String
)