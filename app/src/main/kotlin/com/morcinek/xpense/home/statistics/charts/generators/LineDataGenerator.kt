package com.morcinek.xpense.home.statistics.charts.generators

import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.expense.Expense
import com.morcinek.xpense.home.statistics.charts.ChartDataGenerator
import com.morcinek.xpense.home.statistics.charts.iteratePeriod
import lecho.lib.hellocharts.model.*
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
internal class LineDataGenerator(val range: Iterable<Int>, val extractor: (Calendar) -> Int, val printer: (Int) -> String) : ChartDataGenerator {

    override fun generateData(expenses: List<Expense>, selectedCategories: List<Category>): AbstractChartData {
        val data = LineChartData(createLines(expenses, selectedCategories))
        data.axisXBottom = Axis(lineXValues()).setHasLines(true)
        data.axisYLeft = Axis().setHasLines(true)
        return data

    }

    private fun createLines(expenses: List<Expense>, selectedCategories: List<Category>): ArrayList<Line> {
        val lines = arrayListOf<Line>()
        val categoriesExpenses = expenses.groupBy { it.category!! }
        for (category in selectedCategories) {
            lines.add(createLine(category.color!!, categoriesExpenses[category]!!))
        }
        return lines
    }

    private fun createLine(color: Int, expenses: List<Expense>): Line {
        val line = Line(pointValues(expenses))
        line.setColor(color)
        return line
    }

    private fun pointValues(expenses: List<Expense>): ArrayList<PointValue> {
        val values = arrayListOf<PointValue>()
        iteratePeriod(expenses, range, extractor) { index, day, value ->
            values.add(PointValue(day.toFloat(), value))
        }
        return values
    }

    private fun lineXValues(): ArrayList<AxisValue> {
        val axisValues = arrayListOf<AxisValue>()
        for (period in range) {
            axisValues.add(AxisValue(period.toFloat()).setLabel(printer(period)))
        }
        return axisValues
    }
}
