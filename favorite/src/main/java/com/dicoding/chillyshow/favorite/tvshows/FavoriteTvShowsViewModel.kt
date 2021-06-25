package com.dicoding.chillyshow.favorite.tvshows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.chillyshow.core.domain.usecase.ChillyUseCase

class FavoriteTvShowsViewModel(chillyUseCase: ChillyUseCase) : ViewModel() {

    val loadFavoriteTvShows = chillyUseCase.loadFavoriteTvShows().asLiveData()

}