package com.bgrimard.koolicartest.di

import com.bgrimard.koolicartest.ui.placeDetail.PlaceDetailActivity
import com.bgrimard.koolicartest.ui.placeDetail.PlaceDetailFragment
import com.bgrimard.koolicartest.ui.placeList.PlaceListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [AppModule::class, FoursquareModule::class])
    internal abstract fun bindPlaceListActivity(): PlaceListActivity

    @ContributesAndroidInjector(modules = [AppModule::class, FoursquareModule::class])
    internal abstract fun bindPlaceDetailFragment(): PlaceDetailFragment

    @ContributesAndroidInjector(modules = [AppModule::class, FoursquareModule::class])
    internal abstract fun bindPlaceDetailActivity(): PlaceDetailActivity
}