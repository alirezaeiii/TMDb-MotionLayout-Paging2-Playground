package com.sample.android.tmdb.ui.paging.main.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.data.network.TVShowService
import com.sample.android.tmdb.domain.model.TVShow
import com.sample.android.tmdb.ui.paging.main.BaseMainPagingFragment
import javax.inject.Inject

abstract class TVShowPagingFragment : BaseMainPagingFragment<TVShow>() {

    @Inject
    lateinit var api: TVShowService

    override val viewModel by lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TVShowPagingViewModel(api, sortType, requireNotNull(activity).application) as T
            }
        })[TVShowPagingViewModel::class.java]
    }
}