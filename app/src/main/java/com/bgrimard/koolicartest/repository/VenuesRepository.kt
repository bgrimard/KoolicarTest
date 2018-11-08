package com.bgrimard.koolicartest.repository

import com.bgrimard.koolicartest.model.Venue
import io.reactivex.Observable

interface VenuesRepository {
    fun getVenues() : Observable<List<Venue>>
}