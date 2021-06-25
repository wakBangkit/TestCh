package com.dicoding.chillyshow.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.chillyshow.home.movies.MoviesFragment
import com.dicoding.chillyshow.home.tvshows.TvShowsFragment

class TabsAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MoviesFragment()
            1 -> fragment = TvShowsFragment()
        }
        return fragment as Fragment
    }

}