package ru.reviewer.features.shows.models

@kotlinx.serialization.Serializable
data class DeleteShowRequest(
    val id: String
)

@kotlinx.serialization.Serializable
data class DeleteShowResponse(
    val message: String
)