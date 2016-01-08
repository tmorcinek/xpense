package com.morcinek.xpense.expense.common

import com.morcinek.xpense.expense.common.model.Expense
import java.util.*

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
class ExpenseManager {

    private val expenses: MutableList<Expense> = ArrayList()

    fun addExpense(expense: Expense) {
        expenses.add(expense)
        listeners.map { it.expenseAdded(expense) }
    }

    fun getExpenses(): List<Expense> = expenses

    private val listeners: MutableList<ExpenseManagerListener> = ArrayList()

    fun registerListener(expenseManagerListener: ExpenseManagerListener) {
        listeners.add(expenseManagerListener)
    }

    fun unregisterListener(expenseManagerListener: ExpenseManagerListener) {
        listeners.remove(expenseManagerListener)
    }
}
