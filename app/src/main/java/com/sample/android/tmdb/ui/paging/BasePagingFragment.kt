package com.sample.android.tmdb.ui.paging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.FragmentMainBinding
import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.domain.paging.Status.RUNNING
import com.sample.android.tmdb.ui.base.BaseNavigationFragment
import com.sample.android.tmdb.widget.MarginDecoration

abstract class BasePagingFragment : BaseNavigationFragment<FragmentMainBinding>() {

    protected abstract val viewModel: BasePagingViewModel

    protected lateinit var tmdbAdapter: TmdbAdapter

    protected open fun refresh() {
        viewModel.refresh()
    }

    override fun setBinding() = FragmentMainBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        tmdbAdapter = TmdbAdapter(viewModel::retry, object : TmdbClickCallback<TmdbItem> {
            override fun onClick(t: TmdbItem) {
                startDetailActivity(t)
            }
        })

        with(binding) {

            swipeRefresh.apply {
                setColorSchemeColors(
                    ContextCompat.getColor(context, R.color.colorPrimary),
                    ContextCompat.getColor(context, R.color.colorAccent),
                    ContextCompat.getColor(context, R.color.colorPrimaryDark)
                )

                viewModel.refreshState.observe(viewLifecycleOwner) {
                    isRefreshing = it.status == RUNNING
                }

                setOnRefreshListener { refresh() }
            }

            recyclerView.apply {
                addItemDecoration(MarginDecoration(context))
                setHasFixedSize(true)
                adapter = tmdbAdapter
                manager.spanSizeLookup = object : SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (tmdbAdapter.getItemViewType(position) == R.layout.tmdb_item)
                            1 else manager.spanCount
                    }
                }
            }
        }

        viewModel.items.observe(viewLifecycleOwner) {
            tmdbAdapter.submitList(it)
        }

        viewModel.networkState.observe(viewLifecycleOwner) {
            tmdbAdapter.setNetworkState(it)
        }

        return binding.root
    }
}