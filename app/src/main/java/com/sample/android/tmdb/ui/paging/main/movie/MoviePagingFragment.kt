package com.sample.android.tmdb.ui.paging.main.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.data.network.MovieService
import com.sample.android.tmdb.domain.model.Movie
import com.sample.android.tmdb.ui.paging.main.BaseMainPagingFragment
import javax.inject.Inject

abstract class MoviePagingFragment : BaseMainPagingFragment<Movie>() {

    @Inject
    lateinit var api: MovieService

    override val viewModel by lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MoviePagingViewModel(api, sortType, requireNotNull(activity).application) as T
            }
        })[MoviePagingViewModel::class.java]
    }
}