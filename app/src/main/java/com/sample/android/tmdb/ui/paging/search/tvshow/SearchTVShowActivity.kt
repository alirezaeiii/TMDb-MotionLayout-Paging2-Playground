package com.sample.android.tmdb.ui.paging.search.tvshow

import com.sample.android.tmdb.R
import com.sample.android.tmdb.ui.paging.search.SearchActivity
import javax.inject.Inject

class SearchTVShowActivity: SearchActivity() {

    @Inject
    lateinit var searchTVShowFragment: SearchTVShowFragment

    override val fragment: SearchTVShowFragment
        get() = searchTVShowFragment

    override val hintId: Int
        get() = R.string.menu_tv_series
}