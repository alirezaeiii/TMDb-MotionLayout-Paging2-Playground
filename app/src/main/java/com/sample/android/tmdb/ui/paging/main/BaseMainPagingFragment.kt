package com.sample.android.tmdb.ui.paging.main

import com.sample.android.tmdb.domain.model.SortType
import com.sample.android.tmdb.ui.paging.BasePagingFragment

abstract class BaseMainPagingFragment : BasePagingFragment() {

    protected abstract val sortType: SortType
}