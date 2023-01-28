package com.forzz.android.reviewermobile.presentation.movie_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.forzz.android.reviewermobile.domain.model.Movie

class MovieDetailViewModel : ViewModel() {

    val movieData = MutableLiveData<Movie?>()

    init {
        movieData.value = null
    }

    fun getTitle() = movieData.value?.title
    fun getCorrectDate() = (movieData.value?.date ?: "") + ","
    fun getCorrectLength() = (movieData.value?.length ?: "") + ","
    fun getGenre() = movieData.value?.genre
    fun getCorrectImdb() = "${movieData.value?.imdb} IMDb"
    fun getDescription() = movieData.value?.description

    fun listToMovie(list: ArrayList<String>): Movie {
        return Movie(
            id = list[0],
            title = list[1],
            date = list[2],
            length = list[3],
            genre = list[4],
            imdb = list[5],
            description = list[6],
            imageUrl = list[7]
        )
    }
}