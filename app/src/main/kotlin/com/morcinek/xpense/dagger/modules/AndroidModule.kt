package com.morcinek.xpense.dagger.modules

import android.content.Context
import com.morcinek.xpense.Application
import com.morcinek.xpense.dagger.ForApplication
import com.morcinek.xpense.data.category.CategoryManager
import com.morcinek.xpense.data.category.ColorManager
import com.morcinek.xpense.data.expense.ExpenseManager
import com.morcinek.xpense.data.note.NoteManager
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
    fun provideNoteManager(): NoteManager {
        return NoteManager()
    }

    @Provides
    @Singleton
    fun provideCategoryManager(): CategoryManager {
        return CategoryManager()
    }

    @Provides
    @Singleton
    fun provideColorManager(): ColorManager {
        return ColorManager()
    }
}