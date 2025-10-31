package com.sample.android.tmdb.ui.paging.main.movie

import com.sample.android.tmdb.R
import com.sample.android.tmdb.ui.feed.NavType
import com.sample.android.tmdb.ui.paging.main.MainPagingActivity

abstract class MoviePagingActivity: MainPagingActivity() {

    protected abstract val titleId: Int

    override val screenTitle: String
        get() = getString(titleId, getString(R.string.menu_movies))

    override val navType: NavType
        get() = NavType.MOVIES
}