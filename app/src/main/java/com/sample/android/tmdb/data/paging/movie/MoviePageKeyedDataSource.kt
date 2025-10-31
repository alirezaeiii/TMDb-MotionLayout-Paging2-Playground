package com.sample.android.tmdb.data.paging.movie

import android.content.Context
import com.sample.android.tmdb.data.network.MovieService
import com.sample.android.tmdb.data.response.asMovieDomainModel
import com.sample.android.tmdb.domain.model.SortType
import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.domain.paging.BasePageKeyedDataSource
import io.reactivex.Observable
import java.util.concurrent.Executor

class MoviePageKeyedDataSource(
    private val api: MovieService,
    private val sortType: SortType,
    retryExecutor: Executor,
    context: Context
) : BasePageKeyedDataSource(retryExecutor, context) {

    override fun fetchItems(page: Int): Observable<List<TmdbItem>> = when (sortType) {
        SortType.MOST_POPULAR -> api.popularMovies(page).map { it.items.asMovieDomainModel() }
        SortType.HIGHEST_RATED -> api.topRatedMovies(page).map { it.items.asMovieDomainModel() }
        SortType.UPCOMING -> api.upcomingMovies(page).map { it.items.asMovieDomainModel() }
        SortType.TRENDING -> api.trendingMovies(page).map { it.items.asMovieDomainModel() }
        SortType.NOW_PLAYING -> api.nowPlayingMovies(page).map { it.items.asMovieDomainModel() }
        SortType.DISCOVER -> api.discoverMovies(page).map { it.items.asMovieDomainModel() }
    }
}