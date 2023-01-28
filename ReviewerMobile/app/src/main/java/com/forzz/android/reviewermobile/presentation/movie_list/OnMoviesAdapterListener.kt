package com.forzz.android.reviewermobile.presentation.movie_list

import com.forzz.android.reviewermobile.domain.model.Movie

interface OnMoviesAdapterListener {
    fun showMovies(movie: Movie)
}