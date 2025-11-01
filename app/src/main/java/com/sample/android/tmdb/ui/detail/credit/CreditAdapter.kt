package com.sample.android.tmdb.ui.detail.credit

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.CreditItemBinding
import com.sample.android.tmdb.domain.model.Credit
import com.sample.android.tmdb.util.layoutInflater

class CreditAdapter(
        credits: List<Credit>,
        private val creditClickCallback: CreditClickCallback<Credit>)
    : RecyclerView.Adapter<CreditAdapter.CreditViewHolder>() {

    private var credits: List<Credit> = credits
        set(credit) {
            field = credit
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditViewHolder {
        val binding: CreditItemBinding = DataBindingUtil
                .inflate(parent.context.layoutInflater,
                        R.layout.credit_item,
                        parent, false)
        binding.callback = creditClickCallback
        return CreditViewHolder(binding)
    }

    override fun getItemCount() = credits.size

    override fun onBindViewHolder(holder: CreditViewHolder, position: Int) {
        with(holder.binding) {
            creditItem = credits[position]
            executePendingBindings()
        }
    }

    class CreditViewHolder(val binding: CreditItemBinding) :
            RecyclerView.ViewHolder(binding.root)
}