package com.morcinek.xpense.dagger.modules

import android.content.Context
import com.morcinek.xpense.Application
import com.morcinek.xpense.hint.HintProvider
import com.morcinek.xpense.dagger.ForApplication
import com.morcinek.xpense.data.CategoryManager
import com.morcinek.xpense.expense.common.ExpenseManager
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

    @Provides
    @Singleton
    fun provideHintProvider(): HintProvider {
        return HintProvider()
    }

    @Provides
    @Singleton
    fun provideCategoryManager(): CategoryManager {
        return CategoryManager()
    }
}