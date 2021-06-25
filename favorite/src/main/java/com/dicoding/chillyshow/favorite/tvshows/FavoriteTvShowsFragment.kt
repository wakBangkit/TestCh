package com.dicoding.chillyshow.favorite.tvshows

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.chillyshow.core.domain.model.TvShow
import com.dicoding.chillyshow.core.ui.TvShowsGridAdapter
import com.dicoding.chillyshow.databinding.FragmentTvShowsBinding
import com.dicoding.chillyshow.detail.tvshow.TvShowDetailActivity
import com.dicoding.chillyshow.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteTvShowsFragment : Fragment() {

    private lateinit var binding: FragmentTvShowsBinding
    private lateinit var adapter: TvShowsGridAdapter
    private val viewModel: FavoriteTvShowsViewModel by viewModel()

    private var columnsOfRV: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)

        columnsOfRV =
            if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
                3
            } else {
                5
            }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showProgressBar()

        loadKoinModules(favoriteModule)

        adapter = TvShowsGridAdapter()
        adapter.setCallback(object : TvShowsGridAdapter.Callback {
            override fun onItemClick(item: TvShow) {
                showDetailActivity(item)
            }
        })

        viewModel.loadFavoriteTvShows.observe(viewLifecycleOwner, { tvShows ->
            hideProgressBar()
            adapter.setItems(tvShows)
            adapter.notifyDataSetChanged()
        })

        binding.rvTvShows.layoutManager = GridLayoutManager(activity, columnsOfRV)
        binding.rvTvShows.setHasFixedSize(true)
        binding.rvTvShows.adapter = adapter
    }

    private fun showDetailActivity(item: TvShow) {
        val intent = Intent(activity, TvShowDetailActivity::class.java)
        intent.putExtra(TvShowDetailActivity.EXTRA_ITEM, item)
        startActivity(intent)
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

}