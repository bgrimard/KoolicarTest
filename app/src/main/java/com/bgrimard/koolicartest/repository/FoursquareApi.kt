package com.bgrimard.koolicartest.repository

import com.bgrimard.koolicartest.model.VenuesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FoursquareApi {

    @GET("venues/search?v=20181101&limit=10&query=food&intent=browse&radius=10000")
    fun searchVenues(
        @Query("client_id") client_id: String,
        @Query("client_secret") client_secret: String,
        @Query("ll") ll: String
    ): Observable<VenuesResponse>

    //https://api.foursquare.com/v2/venues/search?client_id=0QB00MREY0KIHS21IVHCYSRDCQMQJGJHPO43K1MWEF2ISCAW&client_secret=2SLSEEIX2ZGZJQWYBFDJ3AOLU5NSEL0DLFGU4F40FDEMV1CJ&query=food&near=7248+St+Urbain+St,+Montreal,+QC+H2R+2Y6&v=20181108

    //https://api.foursquare.com/v2/venues/search?ll=45.5337892,-73.6223969&client_id=0QB00MREY0KIHS21IVHCYSRDCQMQJGJHPO43K1MWEF2ISCAW&client_secret=2SLSEEIX2ZGZJQWYBFDJ3AOLU5NSEL0DLFGU4F40FDEMV1CJ&v=20181101&limit=10

    //https://api.foursquare.com/v2/venues/search?ll=45.5337892,-73.6223969&client_id=0QB00MREY0KIHS21IVHCYSRDCQMQJGJHPO43K1MWEF2ISCAW&client_secret=2SLSEEIX2ZGZJQWYBFDJ3AOLU5NSEL0DLFGU4F40FDEMV1CJ&v=20181101&limit=10
}