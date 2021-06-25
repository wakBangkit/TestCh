package com.dicoding.chillyshow.favorite.movies

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.chillyshow.core.domain.model.Movie
import com.dicoding.chillyshow.core.ui.MoviesGridAdapter
import com.dicoding.chillyshow.databinding.FragmentMoviesBinding
import com.dicoding.chillyshow.detail.movie.MovieDetailActivity
import com.dicoding.chillyshow.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteMoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var adapter: MoviesGridAdapter
    private val viewModel: FavoriteMoviesViewModel by viewModel()

    private var columnsOfRV: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)

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

        adapter = MoviesGridAdapter()
        adapter.setCallback(object : MoviesGridAdapter.Callback {
            override fun onItemClick(item: Movie) {
                showDetailActivity(item)
            }
        })

        viewModel.loadFavoriteMovies.observe(viewLifecycleOwner, { movies ->
            hideProgressBar()
            adapter.setItems(movies)
            adapter.notifyDataSetChanged()
        })

        binding.rvMovies.layoutManager = GridLayoutManager(activity, columnsOfRV)
        binding.rvMovies.setHasFixedSize(true)
        binding.rvMovies.adapter = adapter
    }

    private fun showDetailActivity(item: Movie) {
        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.EXTRA_ITEM, item)
        startActivity(intent)
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

}