package com.bgrimard.koolicartest.repository

import com.bgrimard.koolicartest.model.VenuesDetailVenue
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FoursquareVenueDetailRepository(private val foursquareApi: FoursquareApi,
                                      private val clientId : String,
                                      private val clientSecret : String) :VenueDetailRepository {
    override fun getVenueDetail(id: String): Observable<VenuesDetailVenue> {
        return foursquareApi
            .getVenueDetail(id, clientId, clientSecret)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { venueResponse -> venueResponse.response}
            .map { response -> response.venue}
    }
}