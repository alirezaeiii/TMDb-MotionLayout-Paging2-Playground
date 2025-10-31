package com.sample.android.tmdb.ui.paging

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.android.tmdb.R
import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.domain.paging.NetworkState
import java.util.Objects

class TmdbAdapter(private val retryCallback: () -> Unit,
                                private val tmdbClickCallback: TmdbClickCallback<TmdbItem>
)
    : PagedListAdapter<TmdbItem, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<TmdbItem>() {
    override fun areContentsTheSame(oldItem: TmdbItem, newItem: TmdbItem): Boolean =
            Objects.equals(oldItem, newItem)

    override fun areItemsTheSame(oldItem: TmdbItem, newItem: TmdbItem): Boolean =
            oldItem.id == newItem.id
}) {

    private var networkState: NetworkState? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.tmdb_item -> {
                with((holder as TmdbItemViewHolder).binding) {
                    tmdbItem = getItem(position)
                    executePendingBindings()
                }
            }
            R.layout.network_state_item ->
                (holder as NetworkStateViewHolder).bindTo(position, networkState)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.tmdb_item -> TmdbItemViewHolder.create(parent, tmdbClickCallback)
            R.layout.network_state_item -> NetworkStateViewHolder.create(parent, retryCallback)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.network_state_item
        } else {
            R.layout.tmdb_item
        }
    }

    override fun getItemCount(): Int = super.getItemCount() + if (hasExtraRow()) 1 else 0

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }
}