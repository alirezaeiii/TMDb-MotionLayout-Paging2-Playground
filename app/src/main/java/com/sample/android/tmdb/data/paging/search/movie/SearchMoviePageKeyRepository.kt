package com.sample.android.tmdb.data.paging.search.movie

import android.content.Context
import com.sample.android.tmdb.data.network.MovieService
import com.sample.android.tmdb.domain.paging.BaseDataSourceFactory
import com.sample.android.tmdb.domain.paging.BasePageKeyRepository
import java.util.concurrent.Executor

class SearchMoviePageKeyRepository(
    api: MovieService,
    query: String,
    retryExecutor: Executor,
    context: Context
) : BasePageKeyRepository(retryExecutor) {

    override val sourceFactory: BaseDataSourceFactory =
        SearchMovieDataSourceFactory(
            api = api,
            query = query,
            retryExecutor = retryExecutor,
            context = context
        )
}