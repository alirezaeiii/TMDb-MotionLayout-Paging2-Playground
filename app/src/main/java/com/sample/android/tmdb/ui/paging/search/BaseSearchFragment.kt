package com.sample.android.tmdb.ui.paging.search

import com.sample.android.tmdb.domain.paging.Status.RUNNING
import com.sample.android.tmdb.ui.paging.BasePagingFragment
import com.sample.android.tmdb.util.toVisibility

abstract class BaseSearchFragment : BasePagingFragment() {

    private val searchViewModel
        get() = viewModel as BaseSearchViewModel

    override fun refresh() {
        super.refresh()
        searchViewModel.refreshState.removeObservers(this)
    }

    fun search(query: String) {
        if (searchViewModel.showQuery(query)) {
            binding.recyclerView.scrollToPosition(0)
            tmdbAdapter.submitList(null)
        }
    }

    fun observeRefreshState() {
        searchViewModel.refreshState.observe(this) {
            binding.recyclerView.toVisibility(it.status != RUNNING)
        }
    }
}