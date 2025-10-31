package com.sample.android.tmdb.ui.feed.tvshow

import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.domain.model.TVShow
import com.sample.android.tmdb.ui.feed.FeedFragment
import javax.inject.Inject

class FeedTVShowFragment @Inject
constructor() // Required empty public constructor
    : FeedFragment<TVShow>() {

    @Inject
    lateinit var factory: FeedTVShowViewModel.Factory

    override val viewModel
        get() = ViewModelProvider(this, factory)[FeedTVShowViewModel::class.java]
}