package com.morcinek.xpense.expense.common.model

import java.util.*

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
data class Expense(var value: Double = 0.0, var category: Category? = null, var note: String = "",
                   val date: Calendar = Calendar.getInstance()) : Comparable<Expense> {

    override fun compareTo(other: Expense) = (value - other.value + 0.5).toInt()

    operator fun plus(expense: Expense) = value + expense.value
}
