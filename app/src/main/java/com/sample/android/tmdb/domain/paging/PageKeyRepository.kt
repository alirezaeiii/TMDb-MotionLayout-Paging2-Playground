package com.sample.android.tmdb.domain.paging

interface PageKeyRepository {
    fun getItems(): Listing
}