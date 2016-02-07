package com.morcinek.xpense.home.navigation

import com.morcinek.xpense.common.formatters.NavigationTextFormatter
import com.morcinek.xpense.data.expense.ExpenseManager
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class NavigationExpenseManager(private val expenseManager: ExpenseManager) {

    val title: String
        get() = formatter.formatTitle(expenseManager.currentProject)

    val subtitle: String
        get() = formatter.formatSubtitle(currentMonth(), totalAmount(), expenseManager.currentProject.currency)

    private val currentDate = Calendar.getInstance()

    private val formatter = NavigationTextFormatter()

    private fun currentMonth() = currentDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())

    private fun totalAmount() = expenseManager.getExpenses().filter { it.date.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH) }.sumByDouble { it.value }
}