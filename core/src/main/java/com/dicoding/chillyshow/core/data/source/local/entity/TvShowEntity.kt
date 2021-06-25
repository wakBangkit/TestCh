package com.dicoding.chillyshow.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tv_shows_table")
data class TvShowEntity(
    @PrimaryKey
    var id: Int,
    var title: String,
    var posterUrl: String,
    var description: String,
    var year: String,
    var userScore: Double,
    var favorite: Boolean = false
) : Parcelable