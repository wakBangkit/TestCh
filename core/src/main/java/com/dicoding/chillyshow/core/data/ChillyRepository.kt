package com.dicoding.chillyshow.core.data

import com.dicoding.chillyshow.core.data.source.local.LocalDataSource
import com.dicoding.chillyshow.core.data.source.local.entity.MovieEntity
import com.dicoding.chillyshow.core.data.source.local.entity.TvShowEntity
import com.dicoding.chillyshow.core.data.source.remote.RemoteDataSource
import com.dicoding.chillyshow.core.data.source.remote.response.MovieDetailResponse
import com.dicoding.chillyshow.core.data.source.remote.response.TvDetailResponse
import com.dicoding.chillyshow.core.data.source.remote.retrofit.ApiResponse
import com.dicoding.chillyshow.core.domain.model.Movie
import com.dicoding.chillyshow.core.domain.model.TvShow
import com.dicoding.chillyshow.core.domain.repository.IChillyRepository
import com.dicoding.chillyshow.core.utils.AppExecutors
import com.dicoding.chillyshow.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChillyRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IChillyRepository {

    override fun loadMovies(): Flow<Resource<List<Movie>>> {
        return object :
            NetworkBoundResource<List<Movie>, List<MovieDetailResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.loadMovies().map { DataMapper.mapMovieEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieDetailResponse>>> {
                return remoteDataSource.loadMovies()
            }

            override suspend fun saveCallResult(data: List<MovieDetailResponse>) {
                val movieList = ArrayList<MovieEntity>()

                for (response in data) {
                    val movie = DataMapper.convertMovieResponseToEntity(response)
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()
    }

    override fun loadFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.loadFavoriteMovies().map { DataMapper.mapMovieEntitiesToDomain(it) }
    }

    override fun toggleFavoriteMovie(movie: Movie) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.toggleFavoriteMovie(movieEntity) }
    }


    override fun loadTvShows(): Flow<Resource<List<TvShow>>> {
        return object :
            NetworkBoundResource<List<TvShow>, List<TvDetailResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.loadTvShows().map { DataMapper.mapTvShowEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvDetailResponse>>> {
                return remoteDataSource.loadTvShows()
            }

            override suspend fun saveCallResult(data: List<TvDetailResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()

                for (response in data) {
                    val tvShow = DataMapper.convertTvResponseToEntity(response)
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShows(tvShowList)
            }

        }.asFlow()
    }

    override fun loadFavoriteTvShows(): Flow<List<TvShow>> {
        return localDataSource.loadFavoriteTvShows().map { DataMapper.mapTvShowEntitiesToDomain(it) }
    }

    override fun toggleFavoriteTvShow(tvShow: TvShow) {
        val tvShowEntity = DataMapper.mapTvShowDomainToEntity(tvShow)
        appExecutors.diskIO().execute { localDataSource.toggleFavoriteTvShow(tvShowEntity) }
    }

}