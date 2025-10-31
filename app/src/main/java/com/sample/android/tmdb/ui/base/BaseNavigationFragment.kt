package com.sample.android.tmdb.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.sample.android.tmdb.domain.model.Movie
import com.sample.android.tmdb.domain.model.TVShow
import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.ui.detail.movie.DetailMovieActivity
import com.sample.android.tmdb.ui.detail.tvshow.DetailTVShowActivity
import com.sample.android.tmdb.util.Constants

abstract class BaseNavigationFragment<VB: ViewDataBinding> : BaseFragment<VB>()  {

    protected fun startDetailActivity(tmdbItem: TmdbItem) {
        val activityClass = when (tmdbItem) {
            is Movie -> DetailMovieActivity::class.java
            is TVShow -> DetailTVShowActivity::class.java
            else -> throw RuntimeException("Unknown item to start detail Activity")
        }
        val intent = Intent(requireActivity(), activityClass).apply {
            putExtras(Bundle().apply {
                putParcelable(Constants.EXTRA_TMDB_ITEM, tmdbItem)
            })
        }
        startActivity(intent)
    }
}