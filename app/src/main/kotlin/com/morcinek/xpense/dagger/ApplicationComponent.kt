package com.morcinek.xpense.dagger

import com.morcinek.xpense.dagger.modules.AndroidModule
import com.morcinek.xpense.expense.ExpenseActivity
import com.morcinek.xpense.expense.category.CategoryPickerDialogFragment
import com.morcinek.xpense.expense.note.NotePickerDialogFragment
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
    fun inject(expenseActivity: ExpenseActivity)

    fun inject(historyFragment: HistoryFragment)

    fun inject(notePickerDialogFragment: NotePickerDialogFragment)
    fun inject(categoryPickerDialogFragment: CategoryPickerDialogFragment)
}
