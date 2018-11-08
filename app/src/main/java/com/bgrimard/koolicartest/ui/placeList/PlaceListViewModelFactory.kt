package com.bgrimard.koolicartest.ui.placeList

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.bgrimard.koolicartest.repository.VenuesRepository
import javax.inject.Inject

class PlaceListViewModelFactory @Inject constructor(private val venuesRepository: VenuesRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlaceListViewModel(venuesRepository) as T
    }
}