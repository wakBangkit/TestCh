package com.dicoding.chillyshow.home.movies

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.chillyshow.R
import com.dicoding.chillyshow.core.data.Resource
import com.dicoding.chillyshow.core.domain.model.Movie
import com.dicoding.chillyshow.core.ui.MoviesGridAdapter
import com.dicoding.chillyshow.databinding.FragmentMoviesBinding
import com.dicoding.chillyshow.detail.movie.MovieDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var adapter: MoviesGridAdapter
    private val viewModel: MoviesViewModel by viewModel()

    private var columnsOfRV: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)

        columnsOfRV = if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            3
        } else {
            5
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MoviesGridAdapter()
        adapter.setCallback(object : MoviesGridAdapter.Callback {
            override fun onItemClick(item: Movie) {
                showDetailActivity(item)
            }
        })

        viewModel.loadMovies.observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> showProgressBar()
                    is Resource.Success -> {
                        hideProgressBar()
                        adapter.setItems(movies.data)
                        adapter.notifyDataSetChanged()
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        Toast.makeText(context, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                    }
                }
            }
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