package com.forzz.android.reviewermobile.presentation.movie_list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.forzz.android.reviewermobile.R
import com.forzz.android.reviewermobile.databinding.FragmentMoviesBinding
import com.forzz.android.reviewermobile.domain.model.Movie
import com.forzz.android.reviewermobile.presentation.main_screen.OnMainCallback
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ClassCastException

@AndroidEntryPoint
class MoviesFragment : Fragment(), OnMoviesAdapterListener {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding
    private var adapter: MoviesAdapter? = null
    private var mCallback: OnMainCallback? = null

    private val viewModel: MovieListViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMainCallback) {
            mCallback = context
        } else throw ClassCastException(context.toString() + "must implement OnMainCallback!")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MoviesAdapter(this)
        viewModel.loadMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMoviesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        fragmentMoviesBinding.moviesListViewModel = viewModel
        fragmentMoviesBinding.moviesRecyclerView.adapter = adapter

        viewModel.isLoad.observe(viewLifecycleOwner, Observer {
            it?.let { visibility ->
                fragmentMoviesBinding.moviesProgressBar.visibility = if (visibility) View.GONE else View.VISIBLE
            }
        })

        viewModel.moviesReceivedLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                initRecyclerView(it)
            }
        })

        return fragmentMoviesBinding.root
    }

    override fun showMovies(movie: Movie) {
        mCallback?.navigateToMoviePage(movie)
    }

    private fun initRecyclerView(movies: List<Movie>) {
        adapter?.addData(movies)
    }

    override fun onDetach() {
        super.onDetach()
        adapter = null
        mCallback = null
    }

    companion object {

        val FRAGMENT_NAME = MoviesFragment::class.java.name

        @JvmStatic
        fun newInstance() =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }


}