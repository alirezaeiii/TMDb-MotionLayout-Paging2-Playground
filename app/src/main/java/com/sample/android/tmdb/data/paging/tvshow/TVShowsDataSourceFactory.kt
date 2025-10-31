package com.sample.android.tmdb.data.paging.tvshow

import android.content.Context
import com.sample.android.tmdb.data.network.TVShowService
import com.sample.android.tmdb.domain.model.SortType
import com.sample.android.tmdb.domain.paging.BaseDataSourceFactory
import com.sample.android.tmdb.domain.paging.BasePageKeyedDataSource
import java.util.concurrent.Executor

class TVShowsDataSourceFactory(
    private val api: TVShowService,
    private val sortType: SortType,
    private val retryExecutor: Executor,
    private val context: Context
) : BaseDataSourceFactory() {

    override val dataSource: BasePageKeyedDataSource
        get() = TVShowsPageKeyedDataSource(
            api = api,
            sortType = sortType,
            retryExecutor = retryExecutor,
            context = context
        )
}