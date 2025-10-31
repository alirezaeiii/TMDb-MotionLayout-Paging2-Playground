package com.sample.android.tmdb.domain.paging

import androidx.annotation.MainThread
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import java.util.concurrent.Executor

abstract class BasePageKeyRepository(
    private val networkExecutor: Executor
) : PageKeyRepository {

    protected abstract val sourceFactory: BaseDataSourceFactory

    @MainThread
    override fun getItems(): Listing {

        val livePagedList = LivePagedListBuilder(sourceFactory, PAGE_SIZE)
            // provide custom executor for network requests, otherwise it will default to
            // Arch Components' IO pool which is also used for disk access
            .setFetchExecutor(networkExecutor)
            .build()

        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.initialLoad
        }

        val networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.networkState
        }

        return Listing(
            pagedList = livePagedList,
            networkState = networkState,
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            },
            refreshState = refreshState,
            retry = {
                sourceFactory.sourceLiveData.value?.retryAllFailed()
            }
        )
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}