package com.sample.android.tmdb.data.paging.search.tvshow

import android.content.Context
import com.sample.android.tmdb.data.network.TVShowService
import com.sample.android.tmdb.domain.paging.BaseDataSourceFactory
import com.sample.android.tmdb.domain.paging.BasePageKeyRepository
import java.util.concurrent.Executor

class SearchTVShowPageKeyRepository(
    api: TVShowService,
    query: String,
    retryExecutor: Executor,
    context: Context
) : BasePageKeyRepository(retryExecutor) {

    override val sourceFactory: BaseDataSourceFactory =
        SearchTVShowDataSourceFactory(
            api = api,
            query = query,
            retryExecutor = retryExecutor,
            context = context
        )
}