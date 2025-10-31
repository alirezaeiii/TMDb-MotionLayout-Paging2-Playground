package com.sample.android.tmdb.data.paging.movie

import android.content.Context
import com.sample.android.tmdb.data.network.MovieService
import com.sample.android.tmdb.domain.model.SortType
import com.sample.android.tmdb.domain.paging.BaseDataSourceFactory
import com.sample.android.tmdb.domain.paging.BasePageKeyedDataSource
import java.util.concurrent.Executor

class MoviesDataSourceFactory(
    private val api: MovieService,
    private val sortType: SortType,
    private val retryExecutor: Executor,
    private val context: Context
) : BaseDataSourceFactory() {

    override val dataSource: BasePageKeyedDataSource
        get() = MoviePageKeyedDataSource(
            api = api,
            sortType = sortType,
            retryExecutor = retryExecutor,
            context = context
        )
}
