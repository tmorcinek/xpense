package com.morcinek.xpense.home.navigation

import com.morcinek.xpense.common.formatters.NavigationTextFormatter
import com.morcinek.xpense.common.utils.isSameMonth
import com.morcinek.xpense.common.utils.monthName
import com.morcinek.xpense.data.expense.ExpenseManager
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class NavigationExpenseManager(private val expenseManager: ExpenseManager) {

    val title: String
        get() = formatter.formatTitle(expenseManager.currentProject)

    val subtitle: String
        get() = formatter.formatSubtitle(currentMonth(), totalAmount(), expenseManager.currentProject.currency)

    private val currentDate = LocalDate.now()

    private val formatter = NavigationTextFormatter()

    private fun currentMonth() = currentDate.monthName();

    private fun totalAmount() = expenseManager.getExpenses().filter { it.date.isSameMonth(currentDate) }.sumByDouble { it.value }
}