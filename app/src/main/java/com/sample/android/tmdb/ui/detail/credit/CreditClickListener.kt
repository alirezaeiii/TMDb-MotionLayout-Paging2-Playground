package com.sample.android.tmdb.ui.detail.credit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.sample.android.tmdb.domain.model.Credit
import com.sample.android.tmdb.ui.person.PersonActivity
import com.sample.android.tmdb.util.Constants.CREDIT

class CreditClickListener(
    private val context: Context
) : CreditClickCallback<Credit> {

    override fun onClick(credit: Credit) {
        val intent = Intent(context, PersonActivity::class.java).apply {
            putExtras(Bundle().apply {
                putParcelable(
                    CREDIT,
                    credit
                )
            })
        }
        context.startActivity(intent)
    }
}