package com.dicoding.chillyshow.detail.movie

import androidx.lifecycle.ViewModel
import com.dicoding.chillyshow.core.domain.model.Movie
import com.dicoding.chillyshow.core.domain.usecase.ChillyUseCase


class MovieDetailViewModel(private val chillyUseCase: ChillyUseCase) : ViewModel() {

    fun toggleFavoriteMovie(_movie: Movie) {
        val movie = _movie
        movie.favorite = !_movie.favorite

        chillyUseCase.toggleFavoriteMovie(movie)
    }
}