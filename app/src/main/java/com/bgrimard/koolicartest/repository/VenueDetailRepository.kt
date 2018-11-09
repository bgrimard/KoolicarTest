package com.bgrimard.koolicartest.repository

import com.bgrimard.koolicartest.model.VenuesDetailVenue
import io.reactivex.Observable

interface VenueDetailRepository {
    fun getVenueDetail(id: String): Observable<VenuesDetailVenue>
}