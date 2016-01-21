package com.morcinek.xpense.data.expense

import com.morcinek.xpense.data.expense.Expense

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
interface ExpenseManagerListener {

    fun expenseAdded(expense: Expense)
    fun expenseDeleted(expense: Expense)
    fun expenseChanged(expense: Expense)
}