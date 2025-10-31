package com.sample.android.tmdb.ui.paging.main.tvshow

import com.sample.android.tmdb.R
import com.sample.android.tmdb.ui.feed.NavType
import com.sample.android.tmdb.ui.paging.main.MainPagingActivity

abstract class TVShowPagingActivity: MainPagingActivity() {

    protected abstract val titleId: Int

    override val screenTitle: String
        get() = getString(titleId, getString(R.string.menu_tv_series))

    override val navType: NavType
        get() = NavType.TV_SERIES
}