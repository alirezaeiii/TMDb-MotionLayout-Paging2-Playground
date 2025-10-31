package com.sample.android.tmdb.ui.paging.search.tvshow

import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.ui.paging.search.BaseSearchFragment
import javax.inject.Inject

class SearchTVShowFragment @Inject
constructor() // Required empty public constructor
    : BaseSearchFragment() {

    @Inject
    lateinit var factory: SearchTVShowViewModel.Factory

    override val viewModel
        get() = ViewModelProvider(this, factory)[SearchTVShowViewModel::class.java]
}