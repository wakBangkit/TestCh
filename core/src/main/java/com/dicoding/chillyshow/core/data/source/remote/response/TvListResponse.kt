package com.dicoding.chillyshow.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvListResponse(

	@field:SerializedName("results")
	val results: List<TvDetailResponse>
)

data class TvDetailResponse(

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("poster_path")
	val posterPath: String
)
