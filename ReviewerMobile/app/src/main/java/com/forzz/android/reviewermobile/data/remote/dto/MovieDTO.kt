package com.forzz.android.reviewermobile.data.remote.dto

import com.forzz.android.reviewermobile.domain.model.Movie

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

fun MovieDTO.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        date = date,
        length = length,
        genre = genre,
        imdb = imdb,
        description = description,
        imageUrl = imageUrl)
}