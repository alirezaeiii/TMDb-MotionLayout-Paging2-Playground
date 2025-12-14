package com.sample.android.tmdb.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.util.NetworkUtils
import javax.inject.Inject

class NetworkViewModel(private val networkUtils: NetworkUtils) : ViewModel() {

    val networkLiveData = networkUtils.networkLiveData

    init {
        networkUtils.observeNetwork()
    }

    override fun onCleared() {
        super.onCleared()
        networkUtils.unregister()
    }

    class Factory @Inject constructor(
        private val networkUtils: NetworkUtils
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NetworkViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NetworkViewModel(networkUtils) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}