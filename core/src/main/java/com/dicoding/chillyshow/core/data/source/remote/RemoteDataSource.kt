package com.dicoding.chillyshow.core.data.source.remote

import android.util.Log
import com.dicoding.chillyshow.core.data.source.remote.response.MovieDetailResponse
import com.dicoding.chillyshow.core.data.source.remote.response.TvDetailResponse
import com.dicoding.chillyshow.core.data.source.remote.retrofit.ApiResponse
import com.dicoding.chillyshow.core.data.source.remote.retrofit.ApiService
import com.dicoding.chillyshow.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource constructor(private val apiService: ApiService){

    companion object {
        private val TAG = this::class.java.simpleName
    }

    fun loadMovies(): Flow<ApiResponse<List<MovieDetailResponse>>> {
        return flow {
            try {
                val response = apiService.getListMovies()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(DataMapper.adjustMoviesPosterPathForEachMovies(dataArray)))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun loadTvShows(): Flow<ApiResponse<List<TvDetailResponse>>> {
        return flow {
            try {
                val response = apiService.getListTv()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(DataMapper.adjustTvShowPosterPathForEachTvShows(dataArray)))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
