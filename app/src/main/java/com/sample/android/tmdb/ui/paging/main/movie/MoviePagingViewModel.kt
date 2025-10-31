package com.sample.android.tmdb.ui.paging.main.movie

import android.app.Application
import com.sample.android.tmdb.data.network.MovieService
import com.sample.android.tmdb.data.paging.movie.MoviePageKeyRepository
import com.sample.android.tmdb.domain.model.SortType
import com.sample.android.tmdb.domain.paging.BasePageKeyRepository
import com.sample.android.tmdb.ui.paging.main.BaseMainPagingViewModel

class MoviePagingViewModel(
    api: MovieService,
    sortType: SortType,
    app: Application
) : BaseMainPagingViewModel(app = app) {

    override val mainRepoResult: BasePageKeyRepository = MoviePageKeyRepository(api = api,
            sortType = sortType,
            retryExecutor = networkIO,
            context = app.applicationContext)
}