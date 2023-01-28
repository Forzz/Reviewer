package ru.reviewer.features.login.models

@kotlinx.serialization.Serializable
data class FetchUserDataRequest(
    val token: String
)

@kotlinx.serialization.Serializable
data class UserDataResponse(
    val login: String,
    val email: String
)