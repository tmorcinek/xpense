package com.morcinek.xpense.dagger

import com.morcinek.xpense.dagger.modules.AndroidModule
import com.morcinek.xpense.dagger.modules.ManagersModule
import com.morcinek.xpense.expense.ExpenseActivity
import com.morcinek.xpense.expense.category.CategoryPickerDialogFragment
import com.morcinek.xpense.expense.note.NotePickerDialogFragment
import com.morcinek.xpense.home.HomeActivity
import com.morcinek.xpense.home.category.CategoryActivity
import com.morcinek.xpense.home.history.HistoryFragment
import com.morcinek.xpense.home.overview.OverviewFragment
import com.morcinek.xpense.project.ProjectActivity
import com.morcinek.xpense.splash.SplashActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
@Singleton
@Component(modules = arrayOf(AndroidModule::class, ManagersModule::class))
interface ApplicationComponent {

    fun inject(splashActivity: SplashActivity)
    fun inject(homeActivity: HomeActivity)
    fun inject(expenseActivity: ExpenseActivity)
    fun inject(categoryActivity: CategoryActivity)
    fun inject(projectActivity: ProjectActivity)

    fun inject(historyFragment: HistoryFragment)
    fun inject(overviewFragment: OverviewFragment)

    fun inject(notePickerDialogFragment: NotePickerDialogFragment)
    fun inject(categoryPickerDialogFragment: CategoryPickerDialogFragment)
}
