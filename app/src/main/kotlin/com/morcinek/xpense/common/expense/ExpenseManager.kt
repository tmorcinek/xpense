package com.morcinek.xpense.common.expense

import com.morcinek.xpense.common.expense.model.Expense
import java.util.*

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
class ExpenseManager {

    private val expenses: MutableList<Expense> = ArrayList()

    fun addExpense(expense: Expense) {
        expenses.add(expense)
    }

    fun getExpenses(): List<Expense> = expenses
}
