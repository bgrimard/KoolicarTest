package com.bgrimard.koolicartest

import com.bgrimard.koolicartest.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication




class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}