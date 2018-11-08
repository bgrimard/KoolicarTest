package com.bgrimard.koolicartest.repository

import com.bgrimard.koolicartest.model.Venue
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FoursquareVenuesRepository(private val foursquareApi: FoursquareApi,
                                 private val clientId : String,
                                 private val clientSecret : String) : VenuesRepository {

    override fun getVenues(): Observable<List<Venue>> {
        return foursquareApi
            .searchVenues(clientId, clientSecret, "45.5337892,-73.6223969")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { venueResponse -> venueResponse.response}
            .map { response -> response.venues}
    }
}