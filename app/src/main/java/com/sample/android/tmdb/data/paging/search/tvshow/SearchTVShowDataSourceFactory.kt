package com.sample.android.tmdb.data.paging.search.tvshow

import android.content.Context
import com.sample.android.tmdb.data.network.TVShowService
import com.sample.android.tmdb.domain.paging.BaseDataSourceFactory
import com.sample.android.tmdb.domain.paging.BasePageKeyedDataSource
import java.util.concurrent.Executor

class SearchTVShowDataSourceFactory(
    private val api: TVShowService,
    private val query: String,
    private val retryExecutor: Executor,
    private val context: Context
) : BaseDataSourceFactory() {

    override val dataSource: BasePageKeyedDataSource
        get() = SearchTVShowPageKeyedDataSource(
            api = api,
            query = query,
            retryExecutor = retryExecutor,
            context = context
        )
}