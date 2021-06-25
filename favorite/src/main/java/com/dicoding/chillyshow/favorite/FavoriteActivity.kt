package com.dicoding.chillyshow.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.dicoding.chillyshow.R
import com.dicoding.chillyshow.favorite.databinding.ActivityFavoriteBinding
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteActivity : AppCompatActivity() {

    companion object {
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_shows)
    }

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeFragments()
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.title = getString(R.string.favorite_movies_and_tv)
    }

    private fun initializeFragments() {
        val tabsAdapter = FavoriteTabsAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager

        viewPager.adapter = tabsAdapter

        val tabs = binding.tabs

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }
}