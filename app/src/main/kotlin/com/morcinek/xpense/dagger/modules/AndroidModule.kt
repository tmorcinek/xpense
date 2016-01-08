package com.morcinek.xpense.dagger.modules

import android.content.Context
import com.morcinek.xpense.Application
import com.morcinek.xpense.expense.common.ExpenseManager
import com.morcinek.xpense.dagger.ForApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
@Module
class AndroidModule(private val application: Application) {

    @Provides
    @Singleton
    @ForApplication
    fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideExpenseManager(): ExpenseManager {
        return ExpenseManager()
    }
}