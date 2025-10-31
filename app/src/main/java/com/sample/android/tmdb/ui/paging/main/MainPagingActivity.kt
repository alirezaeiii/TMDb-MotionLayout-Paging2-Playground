package com.sample.android.tmdb.ui.paging.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.ActivityMainBinding
import com.sample.android.tmdb.ui.base.BaseNavigationActivity
import com.sample.android.tmdb.util.addFragmentToActivity
import com.sample.android.tmdb.util.setupActionBar

abstract class MainPagingActivity : BaseNavigationActivity() {

    private lateinit var binding: ActivityMainBinding

    protected abstract val fragment: BaseMainPagingFragment

    protected abstract val screenTitle: String

    override val toolbar: Toolbar
        get() = binding.toolbar

    override val networkStatusLayout: View by lazy { binding.itemContainer.networkStatusLayout }

    override val textViewNetworkStatus: TextView by lazy { binding.itemContainer.textViewNetworkStatus }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar(binding.toolbar) {
            setDisplayHomeAsUpEnabled(true)
        }
        title = screenTitle
        if (savedInstanceState == null) {
            addFragmentToActivity(fragment, R.id.fragment_container)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}