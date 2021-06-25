package com.dicoding.chillyshow.detail.tvshow

import androidx.lifecycle.ViewModel
import com.dicoding.chillyshow.core.domain.model.TvShow
import com.dicoding.chillyshow.core.domain.usecase.ChillyUseCase


class TvShowDetailViewModel(private val chillyUseCase: ChillyUseCase) : ViewModel() {

    fun toggleFavoriteTvShow(_tvShow: TvShow) {
        val tvShow = _tvShow
        tvShow.favorite = !_tvShow.favorite

        chillyUseCase.toggleFavoriteTvShow(tvShow)
    }

}