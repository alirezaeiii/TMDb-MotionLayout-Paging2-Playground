package com.sample.android.tmdb.ui.feed.movie

import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.domain.model.Movie
import com.sample.android.tmdb.ui.feed.FeedFragment
import javax.inject.Inject

class FeedMovieFragment @Inject
constructor() // Required empty public constructor
    : FeedFragment<Movie>() {

    @Inject
    lateinit var factory: FeedMovieViewModel.Factory

    override val viewModel
        get() = ViewModelProvider(this, factory)[FeedMovieViewModel::class.java]
}