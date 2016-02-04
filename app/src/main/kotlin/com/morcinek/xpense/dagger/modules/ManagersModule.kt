package com.morcinek.xpense.dagger.modules

import com.morcinek.xpense.data.expense.ExpenseManager
import com.morcinek.xpense.home.overview.OverviewManager
import dagger.Module
import dagger.Provides

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
@Module(includes = arrayOf(AndroidModule::class))
class ManagersModule {

    @Provides
    fun provideOverviewManager(expenseManager: ExpenseManager): OverviewManager {
        return OverviewManager(expenseManager)
    }
}