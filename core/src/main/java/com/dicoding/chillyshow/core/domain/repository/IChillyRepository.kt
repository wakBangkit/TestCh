package com.dicoding.chillyshow.core.domain.repository

import com.dicoding.chillyshow.core.data.Resource
import com.dicoding.chillyshow.core.domain.model.Movie
import com.dicoding.chillyshow.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface IChillyRepository {

    fun loadMovies(): Flow<Resource<List<Movie>>>
    fun loadFavoriteMovies(): Flow<List<Movie>>
    fun toggleFavoriteMovie(movie: Movie)

    fun loadTvShows(): Flow<Resource<List<TvShow>>>
    fun loadFavoriteTvShows(): Flow<List<TvShow>>
    fun toggleFavoriteTvShow(tvShow: TvShow)

}