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
import com.morcinek.xpense.data.period.PeriodObjectFactory
import com.morcinek.xpense.data.project.ProjectManager
import com.morcinek.xpense.home.navigation.NavigationExpenseManager
import com.morcinek.xpense.splash.data.CategoryInitializer
import com.morcinek.xpense.splash.data.Initializer
import com.morcinek.xpense.splash.data.MockExpenseInitializer
import com.morcinek.xpense.splash.data.NoteInitializer
import dagger.Module
import dagger.Provides
import javax.inject.Named
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
    fun provideExpenseManager(projectManager: ProjectManager): ExpenseManager {
        return ExpenseManager(projectManager)
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

    @Provides
    @Named("initializers")
    fun provideInitializers(projectManager: ProjectManager, categoryManager: CategoryManager): Array<Initializer> {
        return arrayOf(CategoryInitializer(), NoteInitializer(), MockExpenseInitializer(projectManager, categoryManager))
    }

    @Provides
    @Singleton
    fun provideNavigationExpenseManager(expenseManager: ExpenseManager): NavigationExpenseManager {
        return NavigationExpenseManager(expenseManager)
    }

    @Provides
    fun providePeriodObjectFactory(): PeriodObjectFactory {
        return PeriodObjectFactory()
    }
}