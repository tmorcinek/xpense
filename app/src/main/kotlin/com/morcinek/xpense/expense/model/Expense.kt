package com.morcinek.xpense.expense.model

import java.util.*

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
data class Expense(val value: Float, val currency: String, val title: String,
                   val tags: Set<String>, val date: Date) : Comparable<Expense> {
    override fun compareTo(other: Expense) = (value - other.value + 0.5).toInt()

    operator fun plus(expense: Expense) = value + expense.value
}
