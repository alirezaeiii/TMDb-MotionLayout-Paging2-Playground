package com.sample.android.tmdb.ui.paging.main.tvshow

import android.app.Application
import com.sample.android.tmdb.data.network.TVShowService
import com.sample.android.tmdb.data.paging.tvshow.TVShowsPageKeyRepository
import com.sample.android.tmdb.domain.model.SortType
import com.sample.android.tmdb.domain.paging.BasePageKeyRepository
import com.sample.android.tmdb.ui.paging.main.BaseMainPagingViewModel

class TVShowPagingViewModel(
    api: TVShowService,
    sortType: SortType,
    app: Application
) : BaseMainPagingViewModel(app = app) {

    override val mainRepoResult: BasePageKeyRepository = TVShowsPageKeyRepository(api = api,
            sortType = sortType,
            retryExecutor = networkIO,
            context = app.applicationContext)
}