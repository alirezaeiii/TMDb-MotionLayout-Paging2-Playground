package com.sample.android.tmdb.ui.paging.search.movie

import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.domain.model.Movie
import com.sample.android.tmdb.ui.paging.search.BaseSearchFragment
import javax.inject.Inject

class SearchMovieFragment @Inject
constructor() // Required empty public constructor
    : BaseSearchFragment() {

    @Inject
    lateinit var factory: SearchMovieViewModel.Factory

    override val viewModel
        get() = ViewModelProvider(this, factory)[SearchMovieViewModel::class.java]
}