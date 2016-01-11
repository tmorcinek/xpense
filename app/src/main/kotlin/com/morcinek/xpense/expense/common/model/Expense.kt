package com.morcinek.xpense.expense.common.model

import java.util.*

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
data class Expense(val value: Float = 0F, val category: String? = null, val note: String = "",
                   val tags: Set<String> = setOf(), val date: Date = Date()) : Comparable<com.morcinek.xpense.expense.common.model.Expense> {
    override fun compareTo(other: com.morcinek.xpense.expense.common.model.Expense) = (value - other.value + 0.5).toInt()

    operator fun plus(expense: com.morcinek.xpense.expense.common.model.Expense) = value + expense.value
}
