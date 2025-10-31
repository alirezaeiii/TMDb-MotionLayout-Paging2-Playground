package com.sample.android.tmdb.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.ui.base.BaseNavigationFragment
import com.sample.android.tmdb.ui.common.Content
import com.sample.android.tmdb.ui.common.TmdbTheme
import com.sample.android.tmdb.ui.common.composeView

abstract class FeedFragment<T : TmdbItem> : BaseNavigationFragment<Nothing>() {

    protected abstract val viewModel: FeedViewModel<T>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return composeView {
            TmdbTheme {
                Content(viewModel) { feedWrapper ->
                    FeedScreen(
                        feedWrapper
                    ) { tmdbItem ->
                        startDetailActivity(tmdbItem)
                    }
                }
            }
        }
    }
}