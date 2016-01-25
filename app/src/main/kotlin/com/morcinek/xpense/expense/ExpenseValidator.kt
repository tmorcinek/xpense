package com.morcinek.xpense.expense

import com.morcinek.xpense.create.Validator
import com.morcinek.xpense.data.expense.Expense

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ExpenseValidator : Validator<Expense> {

    override fun isValid(item: Expense) = item.category != null && item.value > 0
}