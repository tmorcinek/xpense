package com.morcinek.xpense.dagger

import com.morcinek.xpense.dagger.modules.AndroidModule
import com.morcinek.xpense.dagger.modules.ManagersModule
import com.morcinek.xpense.expense.ExpenseActivity
import com.morcinek.xpense.expense.category.CategoriesPickerDialogFragment
import com.morcinek.xpense.expense.note.NotePickerDialogFragment
import com.morcinek.xpense.home.HomeActivity
import com.morcinek.xpense.home.category.CategoriesFragment
import com.morcinek.xpense.home.category.create.CreateCategoryActivity
import com.morcinek.xpense.home.history.period.PeriodFragment
import com.morcinek.xpense.home.overview.category.OverviewCategoryActivity
import com.morcinek.xpense.home.overview.OverviewFragment
import com.morcinek.xpense.home.statistics.charts.AbstractChartFragment
import com.morcinek.xpense.home.statistics.charts.DaysChartFragment
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
    fun inject(createCategoryActivity: CreateCategoryActivity)
    fun inject(projectActivity: ProjectActivity)
    fun inject(overviewCategoryActivity: OverviewCategoryActivity)

    fun inject(periodFragment: PeriodFragment)
    fun inject(overviewFragment: OverviewFragment)
    fun inject(categoriesFragment: CategoriesFragment)
    fun inject(abstractChartFragment: AbstractChartFragment)

    fun inject(notePickerDialogFragment: NotePickerDialogFragment)
    fun inject(categoriesPickerDialogFragment: CategoriesPickerDialogFragment)
}
