package com.dicoding.chillyshow.core.di

import androidx.room.Room
import com.dicoding.chillyshow.core.data.ChillyRepository
import com.dicoding.chillyshow.core.data.source.local.LocalDataSource
import com.dicoding.chillyshow.core.data.source.local.room.ChillyDatabase
import com.dicoding.chillyshow.core.data.source.remote.RemoteDataSource
import com.dicoding.chillyshow.core.data.source.remote.retrofit.ApiService
import com.dicoding.chillyshow.core.domain.repository.IChillyRepository
import com.dicoding.chillyshow.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<ChillyDatabase>().chillyDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            ChillyDatabase::class.java,
            "ChillyShow.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IChillyRepository> {
        ChillyRepository(
            get(),
            get(),
            get()
        )
    }
}