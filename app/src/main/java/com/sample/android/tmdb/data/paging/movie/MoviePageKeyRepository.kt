package com.sample.android.tmdb.data.paging.movie

import android.content.Context
import com.sample.android.tmdb.domain.model.Movie
import com.sample.android.tmdb.data.network.MovieService
import com.sample.android.tmdb.domain.paging.BaseDataSourceFactory
import com.sample.android.tmdb.domain.paging.BasePageKeyRepository
import com.sample.android.tmdb.domain.model.SortType
import java.util.concurrent.Executor

class MoviePageKeyRepository(
    api: MovieService,
    sortType: SortType,
    retryExecutor: Executor,
    context: Context
) : BasePageKeyRepository(retryExecutor) {

    override val sourceFactory: BaseDataSourceFactory =
        MoviesDataSourceFactory(
            api = api,
            sortType = sortType,
            retryExecutor = retryExecutor,
            context = context
        )
}