package com.morcinek.xpense.home.statistics.charts

import com.morcinek.xpense.common.utils.dayOfYear
import com.morcinek.xpense.common.utils.weekOfYear
import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.expense.Expense
import lecho.lib.hellocharts.model.AbstractChartData
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
interface ChartDataGenerator {

    fun generateData(expenses: List<Expense>, selectedCategories: List<Category>): AbstractChartData
}

inline fun iterateDay(expenses: List<Expense>, range: Iterable<Calendar>, function: (Int, Calendar, Float) -> Unit) {
    val dayGroups = expenses.groupBy { it.date.dayOfYear }.mapValues { it.value.sumByDouble { it.value } }
    for ((index, day) in range.withIndex()) {
        val value = dayGroups[day.dayOfYear]?.toFloat() ?: 0f
        function(index, day, value)
    }
}

inline fun iterateWeek(expenses: List<Expense>, range: Iterable<Int>, function: (Int, Int, Float) -> Unit) {
    val dayGroups = expenses.groupBy { it.date.weekOfYear }.mapValues { it.value.sumByDouble { it.value } }
    for ((index, week) in range.withIndex()) {
        val value = dayGroups[week]?.toFloat() ?: 0f
        function(index, week, value)
    }
}

inline fun iteratePeriod(expenses: List<Expense>, range: Iterable<Int>, extractor: (Calendar) -> Int, function: (Int, Int, Float) -> Unit) {
    val dayGroups = expenses.groupBy { extractor(it.date) }.mapValues { it.value.sumByDouble { it.value } }
    for ((index, period) in range.withIndex()) {
        val value = dayGroups[period]?.toFloat() ?: 0f
        function(index, period, value)
    }
}
