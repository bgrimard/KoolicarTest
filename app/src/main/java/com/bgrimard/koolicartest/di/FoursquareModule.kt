package com.bgrimard.koolicartest.di

import android.content.Context
import com.bgrimard.koolicartest.R
import com.bgrimard.koolicartest.repository.*
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class FoursquareModule {
    @Provides
    internal fun provideFoursquareRetrofit() : Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://api.foursquare.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    internal fun providePlaceService(retrofit: Retrofit) : FoursquareApi {
        return retrofit.create(FoursquareApi::class.java)
    }

    @Provides
    internal fun provideVenueRepository(foursquareApi: FoursquareApi,
                                        @Named("ClientId") clientId:String,
                                        @Named("ClientSecret") clientSecret:String) : VenuesRepository {
        return FoursquareVenuesRepository(foursquareApi, clientId, clientSecret)
    }

    @Provides
    internal fun provideVenueDetailRepository(foursquareApi: FoursquareApi,
                                        @Named("ClientId") clientId:String,
                                        @Named("ClientSecret") clientSecret:String) : VenueDetailRepository {
        return FoursquareVenueDetailRepository(foursquareApi, clientId, clientSecret)
    }

    @Provides
    @Named("ClientId")
    internal fun provideClientId(context: Context): String {
        return context.getString(R.string.clientId)
    }

    @Provides
    @Named("ClientSecret")
    internal fun provideClientSecret(context: Context): String {
        return context.getString(R.string.clientSecret)
    }
}