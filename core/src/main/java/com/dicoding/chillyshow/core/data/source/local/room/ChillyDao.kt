package com.dicoding.chillyshow.core.data.source.local.room

import androidx.room.*
import com.dicoding.chillyshow.core.data.source.local.entity.MovieEntity
import com.dicoding.chillyshow.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChillyDao {

    // movies
    @Query("SELECT * FROM movies_table")
    fun loadMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies_table WHERE favorite = 1")
    fun loadFavoriteMovies(): Flow<List<MovieEntity>>

    @Update
    fun toggleFavoriteMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)


    // tv shows
    @Query("SELECT * FROM tv_shows_table")
    fun loadTvShows(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tv_shows_table WHERE favorite = 1")
    fun loadFavoriteTvShows(): Flow<List<TvShowEntity>>

    @Update
    fun toggleFavoriteTvShow(tvShow: TvShowEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShows: List<TvShowEntity>)

}