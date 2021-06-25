package com.dicoding.chillyshow.core.data.source.remote.retrofit

import com.dicoding.chillyshow.core.BuildConfig
import com.dicoding.chillyshow.core.data.source.remote.response.MovieListResponse
import com.dicoding.chillyshow.core.data.source.remote.response.TvListResponse
import retrofit2.http.GET

interface ApiService {

    @GET("3/trending/movie/week?api_key=${BuildConfig.TMDB_API}")
    suspend fun getListMovies(): MovieListResponse

    @GET("3/trending/tv/week?api_key=${BuildConfig.TMDB_API}")
    suspend fun getListTv(): TvListResponse

}