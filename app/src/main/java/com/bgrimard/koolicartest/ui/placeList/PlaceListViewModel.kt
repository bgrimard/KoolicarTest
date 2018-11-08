package com.bgrimard.koolicartest.ui.placeList

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.bgrimard.koolicartest.model.Venue
import com.bgrimard.koolicartest.repository.VenuesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlaceListViewModel(private val venuesRepository: VenuesRepository) : ViewModel() {
    private val disposable = CompositeDisposable()

    private var venuesList = MutableLiveData<List<Venue>>()
    private var loading = MutableLiveData<Boolean>()
    private var error = MutableLiveData<String>()

    fun loadData() {
        disposable.add(
            venuesRepository
                .getVenues()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading.value = true }
                .subscribe({ venues ->
                    venuesList.value = venues
                    loading.value = false
                },
                    { e ->
                        venuesList.value = listOf()
                        loading.value = false
                        error.value = "Fail to load data: ${e.message}"
                    })
        )
    }

    fun getData(): MutableLiveData<List<Venue>> {
        return venuesList
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