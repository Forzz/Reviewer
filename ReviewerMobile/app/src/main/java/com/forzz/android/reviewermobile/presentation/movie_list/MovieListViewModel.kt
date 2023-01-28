package com.forzz.android.reviewermobile.presentation.movie_list

import android.R
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.forzz.android.reviewermobile.data.remote.dto.toMovie
import com.forzz.android.reviewermobile.domain.model.Movie
import com.forzz.android.reviewermobile.domain.use_cases.get_movies.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    val moviesReceivedLiveData = MutableLiveData<List<Movie>>()
    val isLoad = MutableLiveData<Boolean>()
    val movieData = MutableLiveData<Movie>()

    init {
        isLoad.value = false
    }

    val movie: Movie? get() = movieData.value

    fun set(movie: Movie) = kotlin.run { movieData.value = movie }

    fun loadMovies() {
        getMoviesUseCase.execute(
            onSuccess = { it ->
                isLoad.value = true
                Log.d("CHECK", "OK")
                moviesReceivedLiveData.value = it.map { it.toMovie() }.toList()
            },
            onError = {
                Log.d("CHECK", "NOT OK")
                it.printStackTrace()
            }
        )
    }
}