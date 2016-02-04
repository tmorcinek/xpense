package com.morcinek.xpense

import android.app.Application
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

    override fun onCreate() {
        super.onCreate()
        this.component = DaggerApplicationComponent.builder()
                .androidModule(AndroidModule(this))
                .managersModule(ManagersModule())
                .build()
        SugarContext.init(this);
    }

    override fun onTerminate() {
        super.onTerminate()
        SugarContext.terminate();
    }
}