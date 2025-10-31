package com.sample.android.tmdb.data.paging.search.movie

import android.content.Context
import com.sample.android.tmdb.data.network.MovieService
import com.sample.android.tmdb.data.response.asMovieDomainModel
import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.domain.paging.BasePageKeyedDataSource
import io.reactivex.Observable
import java.util.concurrent.Executor

class SearchMoviePageKeyedDataSource(
    private val api: MovieService,
    private val query: String,
    retryExecutor: Executor,
    context: Context
) : BasePageKeyedDataSource(retryExecutor, context) {

    override fun fetchItems(page: Int): Observable<List<TmdbItem>> =
        api.searchItems(page, query).map { it.items.asMovieDomainModel() }
}