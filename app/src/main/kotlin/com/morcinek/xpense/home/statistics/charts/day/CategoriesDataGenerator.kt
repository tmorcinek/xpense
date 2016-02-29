package com.morcinek.xpense.home.statistics.charts.day

import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.expense.Expense
import com.morcinek.xpense.home.statistics.charts.ChartDataGenerator
import lecho.lib.hellocharts.model.*
import org.jetbrains.anko.collections.forEachReversed

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoriesDataGenerator : ChartDataGenerator {

    override fun generateData(expenses: List<Expense>, selectedCategories: List<Category>): AbstractChartData {
        val columns = arrayListOf<Column>()
        val groupsExpenses = expenses.groupBy { it.category }.mapValues { it.value.sumByDouble { it.value } }
        selectedCategories.forEachReversed {
            columns.add(Column(listOf(SubcolumnValue(groupsExpenses[it]!!.toFloat(), it.color!!))))
        }
        val columnData = ColumnChartData(columns)
        columnData.axisYLeft = Axis().setHasLines(true)
        return columnData
    }
}