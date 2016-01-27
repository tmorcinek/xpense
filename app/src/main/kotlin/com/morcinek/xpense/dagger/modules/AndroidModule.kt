package com.morcinek.xpense.dagger.modules

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.morcinek.xpense.Application
import com.morcinek.xpense.common.helpers.PreferencesHelper
import com.morcinek.xpense.dagger.ForApplication
import com.morcinek.xpense.data.category.CategoryManager
import com.morcinek.xpense.data.category.ColorManager
import com.morcinek.xpense.data.expense.ExpenseManager
import com.morcinek.xpense.data.note.NoteManager
import com.morcinek.xpense.data.project.ProjectManager
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

    @Singleton
    @Provides
    fun provideSharedPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Provides
    @Singleton
    fun providePreferencesHelper(sharedPreferences: SharedPreferences): PreferencesHelper {
        return PreferencesHelper(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideProjectManager(preferencesHelper: PreferencesHelper): ProjectManager {
        return ProjectManager(preferencesHelper)
    }
}