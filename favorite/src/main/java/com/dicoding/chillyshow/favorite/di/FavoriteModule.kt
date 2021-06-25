package com.dicoding.chillyshow.favorite.di

import com.dicoding.chillyshow.favorite.movies.FavoriteMoviesViewModel
import com.dicoding.chillyshow.favorite.tvshows.FavoriteTvShowsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteMoviesViewModel(get()) }
    viewModel { FavoriteTvShowsViewModel(get()) }
}