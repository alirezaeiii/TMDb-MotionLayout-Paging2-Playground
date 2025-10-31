package com.sample.android.tmdb.ui.paging.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.sample.android.tmdb.domain.paging.BasePageKeyRepository
import com.sample.android.tmdb.domain.paging.Listing
import com.sample.android.tmdb.ui.paging.BasePagingViewModel

abstract class BaseMainPagingViewModel(app: Application) : BasePagingViewModel(app) {

    protected abstract val mainRepoResult : BasePageKeyRepository

    override val repoResult: LiveData<Listing> = liveData {
        emit(mainRepoResult.getItems())
    }
}