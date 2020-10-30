package io.shelfy.architecture.presentation.view

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.shelfy.architecture.Dependencies
import io.shelfy.architecture.R
import io.shelfy.architecture.ui.adapters.MoviesAdapter
import io.shelfy.architecture.presentation.view.extensions.doOnQueryTextChange
import io.shelfy.architecture.presentation.viewmodel.MoviesViewModel
import io.shelfy.architecture.presentation.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_movies_list.*

class MoviesActivity : AppCompatActivity() {

    private lateinit var adapter: MoviesAdapter

    private val viewModelFactory: ViewModelFactory = Dependencies.moviesViewModelFactory

    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        adapter = MoviesAdapter { itemPosition ->
            showDetailsFragment(itemPosition)
        }
        recycler.adapter = adapter

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MoviesViewModel::class.java)

        showProgress()
        initViewModelObservers()
    }

    private fun initViewModelObservers() {
        viewModel.moviesLiveData.observe(this, Observer { movies ->
            hideProgress()
            adapter.setMovies(movies)
        })
        viewModel.errorLiveData.observe(this, Observer { message ->
            Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movies, menu)
        val searchItem = menu?.findItem(R.id.appSearchBar)
        (searchItem?.actionView as? SearchView)?.let { searchView ->
            searchView.queryHint = "Search"
            searchView.doOnQueryTextChange { newText ->
                showMoviesStartWith(newText.orEmpty())
                return@doOnQueryTextChange true
            }
        }

        return super.onCreateOptionsMenu(menu)
    }

    private fun showProgress() {
        progress.show()
        recycler.isVisible = false
    }

    private fun hideProgress() {
        progress.hide()
        recycler.isVisible = true
    }

    private fun showMoviesStartWith(query: String) {
        viewModel.search(query)
    }

    private fun showDetailsFragment(selectedItemPosition: Int) {
        val detailsFragment =
            DetailsGalleryFragment.newInstance(
                selectedItemPosition
            )
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.container, detailsFragment)
            .commit()
    }
}

