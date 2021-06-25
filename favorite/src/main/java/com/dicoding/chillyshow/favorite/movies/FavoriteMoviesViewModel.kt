package com.dicoding.chillyshow.favorite.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.chillyshow.core.domain.usecase.ChillyUseCase

class FavoriteMoviesViewModel(chillyUseCase: ChillyUseCase) : ViewModel() {

    val loadFavoriteMovies = chillyUseCase.loadFavoriteMovies().asLiveData()

}