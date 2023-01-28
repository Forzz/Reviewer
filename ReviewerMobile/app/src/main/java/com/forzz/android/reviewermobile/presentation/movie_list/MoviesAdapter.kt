package com.forzz.android.reviewermobile.presentation.movie_list

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.forzz.android.reviewermobile.R
import com.forzz.android.reviewermobile.common.MyGlideModule
import com.forzz.android.reviewermobile.databinding.HolderMovieBinding
import com.forzz.android.reviewermobile.domain.model.Movie
import kotlin.coroutines.coroutineContext

internal class MoviesAdapter(val mListener: OnMoviesAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private val movies: MutableList<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderMovieBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_movie, parent, false
        )
        return MovieViewHolder(holderMovieBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): Movie {
        return movies[position]
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addData(list: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(list)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(
        private val dataBinding: ViewDataBinding
    ) : RecyclerView.ViewHolder(dataBinding.root) {

        fun onBind(movie: Movie) {
            val holderMovieBinding = dataBinding as HolderMovieBinding
            val movieViewModel = MovieViewModel(movie)
            holderMovieBinding.movieViewModel = movieViewModel

            Glide.with(dataBinding.root).load(movie.imageUrl).into(holderMovieBinding.imageView)

            itemView.setOnClickListener {
                mListener.showMovies(movie)
            }
        }
    }
}