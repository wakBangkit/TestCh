package com.dicoding.chillyshow.home.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.chillyshow.core.domain.usecase.ChillyUseCase

class MoviesViewModel(chillyUseCase: ChillyUseCase) : ViewModel() {

    val loadMovies = chillyUseCase.loadMovies().asLiveData()

}