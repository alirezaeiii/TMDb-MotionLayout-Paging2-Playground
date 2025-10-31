package com.sample.android.tmdb.domain.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.sample.android.tmdb.domain.model.TmdbItem

abstract class BaseDataSourceFactory: DataSource.Factory<Int, TmdbItem>() {

    private val _sourceLiveData = MutableLiveData<BasePageKeyedDataSource>()
    val sourceLiveData: LiveData<BasePageKeyedDataSource>
        get() = _sourceLiveData

    protected abstract val dataSource: BasePageKeyedDataSource

    override fun create(): DataSource<Int, TmdbItem> {
        val dataSource = this.dataSource
        _sourceLiveData.postValue(dataSource)
        return dataSource
    }
}