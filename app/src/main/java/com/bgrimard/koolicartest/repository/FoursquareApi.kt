package com.bgrimard.koolicartest.repository

import com.bgrimard.koolicartest.model.VenuesDetail
import com.bgrimard.koolicartest.model.VenuesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoursquareApi {

    @GET("venues/search?v=20181101&limit=10&intent=browse&radius=10000")
    fun searchVenues(
        @Query("client_id") client_id: String,
        @Query("client_secret") client_secret: String,
        @Query("ll") ll: String
    ): Observable<VenuesResponse>

    @GET("venues/{venueId}?v=20181101")
    fun getVenueDetail(
        @Path("venueId") venueId: String ,
        @Query("client_id") client_id: String,
        @Query("client_secret") client_secret: String
    ): Observable<VenuesDetail>
}