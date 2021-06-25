package com.dicoding.chillyshow.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movies_table")
data class MovieEntity(
    @PrimaryKey
    var id: Int,
    var title: String,
    var posterUrl: String,
    var description: String,
    var year: String,
    var userScore: Double,
    var favorite: Boolean = false
) : Parcelable