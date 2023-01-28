package com.forzz.android.reviewermobile.domain.use_cases.get_movies

import android.util.Log
import com.forzz.android.reviewermobile.data.remote.dto.MovieDTO
import com.forzz.android.reviewermobile.domain.repository.ContentRepository
import com.forzz.android.reviewermobile.domain.use_cases.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: ContentRepository
) : SingleUseCase<List<MovieDTO>>() {

    override fun buildUseCaseSingle(): Single<List<MovieDTO>> {
        return repository.getMovies()
    }
}