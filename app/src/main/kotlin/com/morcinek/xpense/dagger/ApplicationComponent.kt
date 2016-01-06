package com.morcinek.xpense.dagger

import com.morcinek.xpense.dagger.modules.AndroidModule
import com.morcinek.xpense.home.HomeActivity
import com.morcinek.xpense.home.history.HistoryFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
@Singleton
@Component(modules = arrayOf(AndroidModule::class))
interface ApplicationComponent {
    fun inject(homeActivity: HomeActivity)
    fun inject(historyFragment: HistoryFragment)
}
