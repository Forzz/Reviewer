package ru.reviewer.features.games.models

data class FetchGamesResponse(
    val games: List<GameResponse>
)

data class GameResponse(
    val id: String,
    val title: String,
    val date: String,
    val length: String,
    val genre: String,
    val imdb: String,
    val description: String,
    val imageUrl: String
)

