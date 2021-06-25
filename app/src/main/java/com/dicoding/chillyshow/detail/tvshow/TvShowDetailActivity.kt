package com.dicoding.chillyshow.detail.tvshow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dicoding.chillyshow.R
import com.dicoding.chillyshow.core.domain.model.TvShow
import com.dicoding.chillyshow.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ITEM = "extra_item"
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: TvShowDetailViewModel by viewModel()

    private var _isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tvShow = intent.getParcelableExtra<TvShow>(EXTRA_ITEM)
        showDetail(tvShow!!)
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.title = getString(R.string.movie_detail)
    }

    private fun showDetail (item: TvShow) {
        var colorOfUserScore = 0
        val userScore = (item.userScore * 10).toInt()

        when (userScore) {
            in 0..50 -> colorOfUserScore = ContextCompat.getColor(this,R.color.red_progress)
            in 51..69 -> colorOfUserScore = ContextCompat.getColor(this,R.color.yellow_progress)
            in 70..100 -> colorOfUserScore = ContextCompat.getColor(this, R.color.green_progress)
        }

        binding.apply {
            title.text = item.title
            description.text = item.description
            year.text = item.year

            Glide.with(this@TvShowDetailActivity)
                    .load(item.posterUrl)
                    .error(R.drawable.ic_placeholder_poster)
                    .into(poster)

            userScoreBar.progress = userScore.toFloat()
            userScoreBar.progressBarColor = colorOfUserScore

            userScoreNumber.text = userScore.toString()
            userScoreNumber.setTextColor(colorOfUserScore)

            utilsPercent.setTextColor(colorOfUserScore)

            _isFavorite = item.favorite
            setFavoriteButtonDrawable()

            btnFavorite?.setOnClickListener {
                toggleFavoriteButton()
                viewModel.toggleFavoriteTvShow(item)
            }
        }
    }

    private fun toggleFavoriteButton() {
        _isFavorite = !_isFavorite
        setFavoriteButtonDrawable()
    }

    private fun setFavoriteButtonDrawable() {
        if (_isFavorite) {
            binding.btnFavorite?.setBackgroundResource(R.drawable.ic_favorite)
        } else {
            binding.btnFavorite?.setBackgroundResource(R.drawable.ic_favorite_border)
        }
    }
}