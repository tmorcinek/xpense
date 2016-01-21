package com.morcinek.xpense.expense

import com.morcinek.xpense.R
import com.morcinek.xpense.data.expense.Expense

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ExpenseValidator {

    fun validate(expense: Expense): Set<Int> {
        val validation: MutableSet<Int> = hashSetOf()
        if (expense.category == null) {
            validation.add(R.string.title_category)
        }
        if (expense.value == 0.0) {
            validation.add(R.string.title_amount)
        }
        return validation
    }
}