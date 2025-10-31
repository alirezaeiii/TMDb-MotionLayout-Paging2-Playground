package com.sample.android.tmdb.data.paging.tvshow

import android.content.Context
import com.sample.android.tmdb.data.network.TVShowService
import com.sample.android.tmdb.data.response.asTVShowDomainModel
import com.sample.android.tmdb.domain.model.SortType
import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.domain.paging.BasePageKeyedDataSource
import io.reactivex.Observable
import java.util.concurrent.Executor

class TVShowsPageKeyedDataSource(
    private val api: TVShowService,
    private val sortType: SortType,
    retryExecutor: Executor,
    context: Context
) : BasePageKeyedDataSource(retryExecutor, context) {

    override fun fetchItems(page: Int): Observable<List<TmdbItem>> = when (sortType) {
        SortType.MOST_POPULAR -> api.popularTVSeries(page).map { it.items.asTVShowDomainModel() }
        SortType.HIGHEST_RATED -> api.topRatedTVSeries(page).map { it.items.asTVShowDomainModel() }
        SortType.UPCOMING -> api.onTheAirTVSeries(page).map { it.items.asTVShowDomainModel() }
        SortType.TRENDING -> api.trendingTVSeries(page).map { it.items.asTVShowDomainModel() }
        SortType.NOW_PLAYING -> api.airingTodayTVSeries(page).map { it.items.asTVShowDomainModel() }
        SortType.DISCOVER -> api.discoverTVSeries(page).map { it.items.asTVShowDomainModel() }
    }
}