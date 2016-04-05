package com.morcinek.xpense.home.navigation

import com.morcinek.xpense.common.formatters.NavigationTextFormatter
import com.morcinek.xpense.common.utils.isSameMonth
import com.morcinek.xpense.common.utils.toTodayFormat
import com.morcinek.xpense.common.utils.plusDays
import com.morcinek.xpense.data.expense.ExpenseManager
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class NavigationExpenseManager(private val expenseManager: ExpenseManager) {

    val title: String
        get() = formatter.formatTitle(expenseManager.currentProject)

    val subtitle: String
        get() = currentDate.toTodayFormat()

    val amount: String
        get() = formatter.formatAmount(totalAmount(), expenseManager.currency)

    private val currentDate = Calendar.getInstance()

    private val formatter = NavigationTextFormatter()

    private fun totalAmount() = expenseManager.getExpenses().filter { it.date.isSameMonth(currentDate) }.sumByDouble { it.value }
}