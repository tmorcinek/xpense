package com.morcinek.xpense.home.statistics.charts.week

import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.expense.Expense
import com.morcinek.xpense.home.statistics.charts.ChartDataGenerator
import com.morcinek.xpense.home.statistics.charts.iterateWeek
import lecho.lib.hellocharts.model.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
internal class ColumnDataGenerator(val range: Iterable<Int>, val color: Int) : ChartDataGenerator {

    override fun generateData(expenses: List<Expense>, selectedCategories: List<Category>): AbstractChartData {
        val columns = arrayListOf<Column>()
        val axisValues = arrayListOf<AxisValue>()
        iterateWeek(expenses.filter { selectedCategories.contains(it.category) }, range) { index, day, value ->
            columns.add(Column(listOf(SubcolumnValue(value, color))).setHasLabelsOnlyForSelected(true))
            axisValues.add(AxisValue(index.toFloat()).setLabel(day.toString()))
        }

        val columnData = ColumnChartData(columns)
        columnData.axisXBottom = Axis(axisValues).setHasLines(true)
        columnData.axisYLeft = Axis().setHasLines(true)
        return columnData
    }
}