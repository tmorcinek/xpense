package com.morcinek.xpense.home.statistics.charts.day

import com.morcinek.xpense.common.utils.toDayFormat
import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.expense.Expense
import com.morcinek.xpense.home.statistics.charts.ChartDataGenerator
import com.morcinek.xpense.home.statistics.charts.iterateDay
import lecho.lib.hellocharts.model.*
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
internal class ColumnDataGenerator(val range: Iterable<Calendar>, val color: Int) : ChartDataGenerator {

    override fun generateData(expenses: List<Expense>, selectedCategories: List<Category>): AbstractChartData {
        val columns = arrayListOf<Column>()
        val axisValues = arrayListOf<AxisValue>()
        iterateDay(expenses.filter { selectedCategories.contains(it.category) }, range) { index, day, value ->
            columns.add(Column(listOf(SubcolumnValue(value, color))).setHasLabelsOnlyForSelected(true))
            axisValues.add(AxisValue(index.toFloat()).setLabel(day.toDayFormat()))
        }

        val columnData = ColumnChartData(columns)
        columnData.axisXBottom = Axis(axisValues).setHasLines(true)
        columnData.axisYLeft = Axis().setHasLines(true)
        return columnData
    }
}