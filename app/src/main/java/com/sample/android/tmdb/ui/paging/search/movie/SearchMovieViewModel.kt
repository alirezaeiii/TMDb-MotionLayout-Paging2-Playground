package com.sample.android.tmdb.ui.paging.search.movie

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.data.network.MovieService
import com.sample.android.tmdb.data.paging.search.movie.SearchMoviePageKeyRepository
import com.sample.android.tmdb.domain.paging.BasePageKeyRepository
import com.sample.android.tmdb.ui.paging.search.BaseSearchViewModel
import javax.inject.Inject

class SearchMovieViewModel(
    private val api: MovieService,
    private val app: Application
) : BaseSearchViewModel(app = app) {

    override fun searchRepoResult(query: String): BasePageKeyRepository =
            SearchMoviePageKeyRepository(api, query, networkIO, app.applicationContext)

    class Factory @Inject constructor(
        private val api: MovieService,
        private val app: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchMovieViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SearchMovieViewModel(api, app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}