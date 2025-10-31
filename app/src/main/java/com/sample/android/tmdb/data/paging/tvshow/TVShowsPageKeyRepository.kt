package com.sample.android.tmdb.data.paging.tvshow

import android.content.Context
import com.sample.android.tmdb.data.network.TVShowService
import com.sample.android.tmdb.domain.model.SortType
import com.sample.android.tmdb.domain.paging.BaseDataSourceFactory
import com.sample.android.tmdb.domain.paging.BasePageKeyRepository
import java.util.concurrent.Executor

class TVShowsPageKeyRepository(
    api: TVShowService,
    sortType: SortType,
    retryExecutor: Executor,
    context: Context
) : BasePageKeyRepository(retryExecutor) {

    override val sourceFactory: BaseDataSourceFactory =
        TVShowsDataSourceFactory(
            api = api,
            sortType = sortType,
            retryExecutor = retryExecutor,
            context = context
        )
}