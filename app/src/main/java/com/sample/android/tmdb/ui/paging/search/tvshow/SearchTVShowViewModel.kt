package com.sample.android.tmdb.ui.paging.search.tvshow

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.data.network.TVShowService
import com.sample.android.tmdb.data.paging.search.tvshow.SearchTVShowPageKeyRepository
import com.sample.android.tmdb.domain.paging.BasePageKeyRepository
import com.sample.android.tmdb.ui.paging.search.BaseSearchViewModel
import javax.inject.Inject

class SearchTVShowViewModel(
    private val api: TVShowService,
    private val app: Application
) : BaseSearchViewModel(app = app) {

    override fun searchRepoResult(query: String): BasePageKeyRepository =
            SearchTVShowPageKeyRepository(api, query, networkIO, app.applicationContext)

    class Factory @Inject constructor(
        private val api: TVShowService,
        private val app: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchTVShowViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SearchTVShowViewModel(api, app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}