package com.dicoding.chillyshow.home.tvshows

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.chillyshow.R
import com.dicoding.chillyshow.core.data.Resource
import com.dicoding.chillyshow.core.domain.model.TvShow
import com.dicoding.chillyshow.core.ui.TvShowsGridAdapter
import com.dicoding.chillyshow.databinding.FragmentTvShowsBinding
import com.dicoding.chillyshow.detail.tvshow.TvShowDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowsFragment : Fragment() {

    private lateinit var binding: FragmentTvShowsBinding
    private lateinit var adapter: TvShowsGridAdapter
    private val viewModel: TVShowsViewModel by viewModel()

    private var columnsOfRV: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)

        columnsOfRV = if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            3
        } else {
            5
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TvShowsGridAdapter()
        adapter.setCallback(object : TvShowsGridAdapter.Callback {
            override fun onItemClick(item: TvShow) {
                showDetailActivity(item)
            }
        })

        viewModel.loadTvShows.observe(viewLifecycleOwner, { tvShows ->
            if (tvShows != null) {
                when (tvShows) {
                    is Resource.Loading -> showProgressBar()
                    is Resource.Success -> {
                        hideProgressBar()
                        adapter.setItems(tvShows.data)
                        adapter.notifyDataSetChanged()
                    }
                    is Resource.Error-> {
                        hideProgressBar()
                        Toast.makeText(context, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                    }
                }
            }
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