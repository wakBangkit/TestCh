package com.dicoding.chillyshow.di

import com.dicoding.chillyshow.core.domain.usecase.ChillyInteractor
import com.dicoding.chillyshow.core.domain.usecase.ChillyUseCase
import com.dicoding.chillyshow.detail.movie.MovieDetailViewModel
import com.dicoding.chillyshow.detail.tvshow.TvShowDetailViewModel
import com.dicoding.chillyshow.home.movies.MoviesViewModel
import com.dicoding.chillyshow.home.tvshows.TVShowsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ChillyUseCase> { ChillyInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { TVShowsViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
    viewModel { TvShowDetailViewModel(get()) }
}