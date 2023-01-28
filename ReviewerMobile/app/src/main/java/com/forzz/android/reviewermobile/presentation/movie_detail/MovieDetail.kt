package com.forzz.android.reviewermobile.presentation.movie_detail

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.forzz.android.reviewermobile.R
import com.forzz.android.reviewermobile.databinding.ActivityMovieDetailBinding
import com.forzz.android.reviewermobile.domain.model.Movie
import com.forzz.android.reviewermobile.presentation.movie_list.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetail : AppCompatActivity() {

    private lateinit var activityMovieDetailBinding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMovieDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        val viewModel: MovieDetailViewModel by viewModels()

        val bundle = intent.getStringArrayListExtra("movie_data")

        viewModel.movieData.value = bundle?.let { viewModel.listToMovie(it) }

        Glide.with(activityMovieDetailBinding.root).load(viewModel.movieData.value?.imageUrl).into(activityMovieDetailBinding.movieImage)
        activityMovieDetailBinding.movieTitleText.text = viewModel.getTitle()
        activityMovieDetailBinding.movieDateText.text = viewModel.getCorrectDate()
        activityMovieDetailBinding.movieDurationText.text = viewModel.getCorrectLength()
        activityMovieDetailBinding.movieGenreText.text = viewModel.getGenre()
        activityMovieDetailBinding.movieImdbRating.text = viewModel.getCorrectImdb()
        activityMovieDetailBinding.movieDescriptionText.text = viewModel.getDescription()


        setContentView(activityMovieDetailBinding.root)
    }
}