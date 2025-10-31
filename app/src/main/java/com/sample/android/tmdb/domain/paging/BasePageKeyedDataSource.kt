package com.sample.android.tmdb.domain.paging

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.sample.android.tmdb.R
import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.util.DisposableManager
import com.sample.android.tmdb.util.NetworkException
import com.sample.android.tmdb.util.isNetworkAvailable
import io.reactivex.Observable
import java.util.concurrent.Executor

abstract class BasePageKeyedDataSource(
    private val retryExecutor: Executor,
    private val context: Context
) : PageKeyedDataSource<Int, TmdbItem>() {

    // keep a function reference for the retry event
    private var retry: (() -> Any)? = null

    /**
     * There is no sync on the state because paging will always call loadInitial first then wait
     * for it to return some success value before calling loadAfter.
     */
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _initialLoad = MutableLiveData<NetworkState>()
    val initialLoad: LiveData<NetworkState>
        get() = _initialLoad

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            retryExecutor.execute {
                it.invoke()
            }
        }
    }

    protected abstract fun fetchItems(page: Int): Observable<List<TmdbItem>>

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TmdbItem>) {
        // ignored, since we only ever append to our initial load
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TmdbItem>) {
        _networkState.postValue(NetworkState.LOADING)
        loadItems(params.key).subscribe({
            _networkState.postValue(NetworkState.LOADED)
            retry = null
            callback.onResult(it, if (it.isEmpty()) null else params.key + 1)
        }) {
            retry = {
                loadAfter(params, callback)
            }
            setErrorMsg(_networkState, it)
        }.also { DisposableManager.add(it) }
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TmdbItem>
    ) {
        _networkState.postValue(NetworkState.LOADING)
        _initialLoad.postValue(NetworkState.LOADING)
        loadItems(STARTING_PAGE_INDEX).subscribe({
            _networkState.postValue(NetworkState.LOADED)
            _initialLoad.postValue(NetworkState.LOADED)
            callback.onResult(it, null, STARTING_PAGE_INDEX + 1)
        }) {
            retry = {
                loadInitial(params, callback)
            }
            setErrorMsg(_networkState, it)
            setErrorMsg(_initialLoad, it)
        }.also { DisposableManager.add(it) }
    }

    private fun loadItems(page: Int): Observable<List<TmdbItem>> =
        Observable.fromCallable { context.isNetworkAvailable() }.switchMap {
            return@switchMap if (it) this.fetchItems(page)
            else Observable.error(NetworkException())
        }

    private fun setErrorMsg(networkState: MutableLiveData<NetworkState>, throwable: Throwable) {
        networkState.postValue(
            NetworkState.error(
                context.getString(
                    if (throwable is NetworkException) R.string.failed_network_msg
                    else R.string.failed_loading_msg
                )
            )
        )
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}