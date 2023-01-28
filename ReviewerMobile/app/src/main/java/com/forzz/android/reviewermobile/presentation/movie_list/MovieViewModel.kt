package com.forzz.android.reviewermobile.presentation.movie_list

import androidx.lifecycle.MutableLiveData
import com.forzz.android.reviewermobile.domain.model.Movie

class MovieViewModel(val movie: Movie) {

    private val TAG = MovieViewModel::class.java.simpleName
    val movieData = MutableLiveData<Movie>()

    init {
        movieData.value = movie
    }
}