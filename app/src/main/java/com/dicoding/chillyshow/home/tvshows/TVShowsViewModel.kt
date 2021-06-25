package com.dicoding.chillyshow.home.tvshows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.chillyshow.core.domain.usecase.ChillyUseCase

class TVShowsViewModel(chillyUseCase: ChillyUseCase) : ViewModel() {

    val loadTvShows = chillyUseCase.loadTvShows().asLiveData()
}
