package com.dicoding.chillyshow.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var id: Int,
    var title: String,
    var posterUrl: String,
    var description: String,
    var year: String,
    var userScore: Double,
    var favorite: Boolean
) : Parcelable
