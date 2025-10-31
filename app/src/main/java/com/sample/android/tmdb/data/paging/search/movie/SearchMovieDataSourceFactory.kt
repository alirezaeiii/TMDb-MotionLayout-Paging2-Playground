package com.sample.android.tmdb.data.paging.search.movie

import android.content.Context
import com.sample.android.tmdb.data.network.MovieService
import com.sample.android.tmdb.domain.paging.BaseDataSourceFactory
import com.sample.android.tmdb.domain.paging.BasePageKeyedDataSource
import java.util.concurrent.Executor

class SearchMovieDataSourceFactory(
    private val api: MovieService,
    private val query: String,
    private val retryExecutor: Executor,
    private val context: Context
) : BaseDataSourceFactory() {

    override val dataSource: BasePageKeyedDataSource
        get() = SearchMoviePageKeyedDataSource(
            api = api,
            query = query,
            retryExecutor = retryExecutor,
            context = context
        )
}