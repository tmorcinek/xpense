package com.morcinek.xpense.expense.common

import com.morcinek.xpense.expense.common.model.Expense

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
interface ExpenseManagerListener {

    fun expenseAdded(expense: Expense)
    fun expenseDeleted(expense: Expense)
    fun expenseChanged(expense: Expense)
}