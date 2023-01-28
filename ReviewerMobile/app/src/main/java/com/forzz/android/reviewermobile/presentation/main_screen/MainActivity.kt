package com.forzz.android.reviewermobile.presentation.main_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.forzz.android.reviewermobile.R
import com.forzz.android.reviewermobile.domain.model.Movie
import com.forzz.android.reviewermobile.presentation.movie_detail.MovieDetail
import com.forzz.android.reviewermobile.presentation.movie_list.MoviesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMainCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigateToMovieListPage()
        }
    }

    fun navigateToMovieListPage() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.movie_container,
                MoviesFragment.newInstance(),
                MoviesFragment.FRAGMENT_NAME
            ).commitAllowingStateLoss()
    }

    override fun navigateToMoviePage(movie: Movie) {
        val intent = Intent(this, MovieDetail::class.java)
        val bundle = Bundle().apply {
            putStringArrayList(
                "movie_data",
                arrayListOf(
                    movie.id,
                    movie.title,
                    movie.date,
                    movie.length,
                    movie.genre,
                    movie.imdb,
                    movie.description,
                    movie.imageUrl
                )
            )
        }

        intent.putExtras(bundle)
        startActivity(intent)
    }
}