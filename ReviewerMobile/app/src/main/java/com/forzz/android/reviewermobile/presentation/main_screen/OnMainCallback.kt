package com.forzz.android.reviewermobile.presentation.main_screen

import com.forzz.android.reviewermobile.domain.model.Movie

interface OnMainCallback {
    fun navigateToMoviePage(movie: Movie)
}