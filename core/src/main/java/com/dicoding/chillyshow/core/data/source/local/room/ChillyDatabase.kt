package com.dicoding.chillyshow.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.chillyshow.core.data.source.local.entity.MovieEntity
import com.dicoding.chillyshow.core.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1)
abstract class ChillyDatabase : RoomDatabase() {
    abstract fun chillyDao(): ChillyDao
}