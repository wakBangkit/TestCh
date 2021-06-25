package com.dicoding.chillyshow.core.domain.usecase

import com.dicoding.chillyshow.core.domain.model.Movie
import com.dicoding.chillyshow.core.domain.model.TvShow
import com.dicoding.chillyshow.core.domain.repository.IChillyRepository

class ChillyInteractor(private val chillyRepository: IChillyRepository): ChillyUseCase {

    override fun loadMovies() = chillyRepository.loadMovies()
    override fun loadFavoriteMovies() = chillyRepository.loadFavoriteMovies()
    override fun toggleFavoriteMovie(movie: Movie) = chillyRepository.toggleFavoriteMovie(movie)

    override fun loadTvShows() = chillyRepository.loadTvShows()
    override fun loadFavoriteTvShows() = chillyRepository.loadFavoriteTvShows()
    override fun toggleFavoriteTvShow(tvShow: TvShow) = chillyRepository.toggleFavoriteTvShow(tvShow)
}