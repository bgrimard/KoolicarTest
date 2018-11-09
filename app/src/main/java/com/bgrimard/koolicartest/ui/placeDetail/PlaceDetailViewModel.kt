package com.bgrimard.koolicartest.ui.placeDetail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.bgrimard.koolicartest.model.VenuesDetailVenue
import com.bgrimard.koolicartest.repository.VenueDetailRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlaceDetailViewModel(private val venuesDetailRepository: VenueDetailRepository) : ViewModel() {

    private val disposable = CompositeDisposable()

    private var venue = MutableLiveData<VenuesDetailVenue>()
    private var loading = MutableLiveData<Boolean>()
    private var error = MutableLiveData<String>()

    fun loadData(venueId: String) {
        disposable.add(
            venuesDetailRepository
                .getVenueDetail(venueId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading.value = true }
                .subscribe({ venues ->
                    venue.value = venues
                    loading.value = false
                },
                    { e ->
                        loading.value = false
                        error.value = "Fail to load data: ${e.message}"
                    })
        )
    }

    fun getData(): MutableLiveData<VenuesDetailVenue> {
        return venue
    }

    fun getLoading(): MutableLiveData<Boolean> {
        return loading
    }

    fun getError(): MutableLiveData<String> {
        return error
    }

    override fun onCleared() {
        disposable.clear()
    }
}