package com.sample.android.tmdb.data.paging.search.movie

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagedList
import com.google.common.collect.Lists
import com.sample.android.tmdb.LoggingObserver
import com.sample.android.tmdb.data.network.MovieService
import com.sample.android.tmdb.data.response.NetworkMovie
import com.sample.android.tmdb.data.response.TMDbWrapper
import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.util.isNetworkAvailable
import io.mockk.every
import io.mockk.mockkStatic
import io.reactivex.Observable
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executor

@RunWith(MockitoJUnitRunner::class)
class SearchMoviePageKeyRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var api: MovieService

    @Mock
    private lateinit var context: Context

    private val networkExecutor = Executor { command -> command.run() }

    private lateinit var movie: NetworkMovie

    @Before
    fun setup() {
        mockkStatic("com.sample.android.tmdb.util.ContextExtKt")
        every {
            context.isNetworkAvailable()
        } returns true
        movie = NetworkMovie(1, "overview", "date",
                null, null, "title", 6.5)
    }

    @Test
    fun searchMovies() {
        val repository = SearchMoviePageKeyRepository(api, "", networkExecutor, context)
        `when`(api.searchItems(anyInt(), anyString())).thenReturn(Observable.just(TMDbWrapper(Lists.newArrayList(movie))))

        val listing = repository.getItems()
        val observer = LoggingObserver<PagedList<TmdbItem>>()
        listing.pagedList.observeForever(observer)

        with(observer.value) {
            assertThat(this, `is`(notNullValue()))
            assertThat(this?.size, `is`(1))
        }
    }
}