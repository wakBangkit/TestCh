package com.dicoding.chillyshow.core.utils

import com.dicoding.chillyshow.core.data.source.local.entity.MovieEntity
import com.dicoding.chillyshow.core.data.source.local.entity.TvShowEntity
import com.dicoding.chillyshow.core.data.source.remote.response.MovieDetailResponse
import com.dicoding.chillyshow.core.data.source.remote.response.TvDetailResponse
import com.dicoding.chillyshow.core.domain.model.Movie
import com.dicoding.chillyshow.core.domain.model.TvShow

object DataMapper {

    fun adjustMoviesPosterPathForEachMovies(rawData: List<MovieDetailResponse>): List<MovieDetailResponse> {
        val adjusted = ArrayList<MovieDetailResponse>()

        for (item in rawData) {
            adjusted.add(adjustMoviePosterPath(item))
        }
        return adjusted
    }

    fun adjustTvShowPosterPathForEachTvShows(rawData: List<TvDetailResponse>): List<TvDetailResponse> {
        val adjusted = ArrayList<TvDetailResponse>()

        for (item in rawData) {
            adjusted.add(adjustTvShowsPosterPath(item))
        }
        return adjusted
    }

    fun adjustMoviePosterPath(movie: MovieDetailResponse): MovieDetailResponse {
        return MovieDetailResponse(
            movie.overview,
            movie.releaseDate,
            movie.voteAverage,
            movie.id,
            movie.title,
            "https://www.themoviedb.org/t/p/w1280" + movie.posterPath
        )
    }

    fun adjustTvShowsPosterPath(tv: TvDetailResponse): TvDetailResponse {
        return TvDetailResponse(
            tv.firstAirDate,
            tv.overview,
            tv.voteAverage,
            tv.name,
            tv.id,
            "https://www.themoviedb.org/t/p/w1280" + tv.posterPath
        )
    }

    fun convertMovieResponseToEntity(response: MovieDetailResponse): MovieEntity {
        return MovieEntity(
            response.id,
            response.title,
            response.posterPath,
            response.overview,
            response.releaseDate,
            response.voteAverage
        )
    }

    fun convertTvResponseToEntity(response: TvDetailResponse): TvShowEntity {
        return TvShowEntity(
            response.id,
            response.name,
            response.posterPath,
            response.overview,
            response.firstAirDate,
            response.voteAverage
        )
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> {
        return input.map {
            Movie(
                it.id,
                it.title,
                it.posterUrl,
                it.description,
                it.year,
                it.userScore,
                it.favorite
            )
        }
    }

    fun mapTvShowEntitiesToDomain(input: List<TvShowEntity>): List<TvShow> {
        return input.map {
            TvShow(
                it.id,
                it.title,
                it.posterUrl,
                it.description,
                it.year,
                it.userScore,
                it.favorite
            )
        }
    }

    fun mapMovieDomainToEntity(it: Movie): MovieEntity {
        return MovieEntity(
            it.id,
            it.title,
            it.posterUrl,
            it.description,
            it.year,
            it.userScore,
            it.favorite
        )
    }

    fun mapTvShowDomainToEntity(it: TvShow): TvShowEntity {
        return TvShowEntity(
            it.id,
            it.title,
            it.posterUrl,
            it.description,
            it.year,
            it.userScore,
            it.favorite
        )
    }
}