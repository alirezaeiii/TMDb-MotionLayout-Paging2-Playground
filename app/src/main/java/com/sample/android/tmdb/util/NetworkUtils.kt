package com.sample.android.tmdb.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

class NetworkUtils(application: Application) : ConnectivityManager.NetworkCallback() {

    private val _networkLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val networkLiveData: LiveData<Boolean>
        get() = Transformations.distinctUntilChanged(_networkLiveData)

    private val connectivityManager =
        application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun observeNetwork() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(this)
        } else {
            val builder = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(builder.build(), this)
        }

        var isConnected = false

        connectivityManager.allNetworks.forEach { network ->
            val networkCapability = connectivityManager.getNetworkCapabilities(network)

            networkCapability?.let {
                if (it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    isConnected = true
                }
            }
        }
        _networkLiveData.postValue(isConnected)
    }

    fun unregister() {
        connectivityManager.unregisterNetworkCallback(this)
    }

    override fun onAvailable(network: Network) {
        _networkLiveData.postValue(true)
    }

    override fun onLost(network: Network) {
        _networkLiveData.postValue(false)
    }
}