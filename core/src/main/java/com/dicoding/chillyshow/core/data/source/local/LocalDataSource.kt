package com.dicoding.chillyshow.core.data.source.local

import com.dicoding.chillyshow.core.data.source.local.entity.MovieEntity
import com.dicoding.chillyshow.core.data.source.local.entity.TvShowEntity
import com.dicoding.chillyshow.core.data.source.local.room.ChillyDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val mChillyDao: ChillyDao) {

    fun loadMovies(): Flow<List<MovieEntity>> = mChillyDao.loadMovies()
    fun loadFavoriteMovies(): Flow<List<MovieEntity>> = mChillyDao.loadFavoriteMovies()
    fun toggleFavoriteMovie(movie: MovieEntity) = mChillyDao.toggleFavoriteMovie(movie)
    suspend fun insertMovies(movies: List<MovieEntity>) = mChillyDao.insertMovies(movies)

    fun loadTvShows(): Flow<List<TvShowEntity>> = mChillyDao.loadTvShows()
    fun loadFavoriteTvShows(): Flow<List<TvShowEntity>> = mChillyDao.loadFavoriteTvShows()
    fun toggleFavoriteTvShow(tvShow: TvShowEntity) = mChillyDao.toggleFavoriteTvShow(tvShow)
    suspend fun insertTvShows(tvShows: List<TvShowEntity>) = mChillyDao.insertTvShows(tvShows)

}