package com.dicoding.chillyshow

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.dicoding.chillyshow.databinding.ActivityMainBinding
import com.dicoding.chillyshow.home.TabsAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    companion object {
        val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_shows)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeFragments()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_favorite_activity -> {
                val intent = Intent(this, Class.forName("com.dicoding.chillyshow.favorite.FavoriteActivity"))
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initializeFragments() {
        val tabsAdapter = TabsAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager

        viewPager.adapter = tabsAdapter

        val tabs = binding.tabs

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }
}