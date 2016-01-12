package com.morcinek.xpense.expense.common.model

import java.util.*

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
data class Expense(var value: Double = 0.0, var category: String? = null, var note: String = "",
                   val tags: Set<String> = setOf(), val date: Calendar = Calendar.getInstance()) : Comparable<com.morcinek.xpense.expense.common.model.Expense> {
    override fun compareTo(other: com.morcinek.xpense.expense.common.model.Expense) = (value - other.value + 0.5).toInt()

    operator fun plus(expense: com.morcinek.xpense.expense.common.model.Expense) = value + expense.value
}
