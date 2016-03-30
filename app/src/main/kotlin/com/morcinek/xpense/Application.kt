package com.morcinek.xpense

import android.app.Application
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.Tracker
import com.morcinek.xpense.dagger.ApplicationComponent
import com.morcinek.xpense.dagger.DaggerApplicationComponent
import com.morcinek.xpense.dagger.modules.AndroidModule
import com.morcinek.xpense.dagger.modules.ManagersModule
import com.orm.SugarContext


/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
class Application : Application() {

    lateinit var component: ApplicationComponent

    val tracker: Tracker by lazy {
        GoogleAnalytics.getInstance(this).newTracker(R.xml.global_tracker);
    }

    override fun onCreate() {
        super.onCreate()
        component = createApplicationComponent()
        tracker.enableExceptionReporting(true)
        SugarContext.init(this);
    }

    private fun createApplicationComponent() = DaggerApplicationComponent.builder()
            .androidModule(AndroidModule(this))
            .managersModule(ManagersModule())
            .build()

    override fun onTerminate() {
        super.onTerminate()
        SugarContext.terminate();
    }
}