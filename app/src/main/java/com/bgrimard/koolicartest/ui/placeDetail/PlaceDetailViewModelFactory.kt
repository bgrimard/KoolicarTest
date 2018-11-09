package com.bgrimard.koolicartest.ui.placeDetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.bgrimard.koolicartest.repository.VenueDetailRepository
import javax.inject.Inject

class PlaceDetailViewModelFactory @Inject constructor(private val venuesDetailRepository: VenueDetailRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlaceDetailViewModel(venuesDetailRepository) as T
    }
}