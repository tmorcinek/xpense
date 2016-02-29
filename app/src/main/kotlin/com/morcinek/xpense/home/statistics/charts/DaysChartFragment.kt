package com.morcinek.xpense.home.statistics.charts

import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.*
import com.morcinek.xpense.data.expense.Expense
import com.morcinek.xpense.home.category.CategoriesAdapter
import com.morcinek.xpense.home.statistics.charts.common.AbstractChartFragment
import kotlinx.android.synthetic.main.days_charts.*
import lecho.lib.hellocharts.model.*
import org.jetbrains.anko.collections.forEachReversed
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class DaysChartFragment : AbstractChartFragment() {

    override val title = R.string.days_chart_label

    override val filter = { expense: Expense ->
        expense.date in range
    }

    private val range by lazy { periodObjectFactory.last2Weeks }

    override fun updateData(expenses: List<Expense>) {
        columnChart.columnChartData = generateColumnChartData(expenses)
        columnChart.isZoomEnabled = false

        lineChart.lineChartData = generateLineChartData(expenses)
        lineChart.isZoomEnabled = false

        categoriesChart.columnChartData = generateCategoriesChartData(expenses)
        categoriesChart.isZoomEnabled = false

        setupAdapter()
    }

    private fun generateLineChartData(expenses: List<Expense>): LineChartData {
        val data = LineChartData(createLinesForSelectedCategories(expenses))
        data.axisXBottom = Axis(lineXValues()).setHasLines(true)
        data.axisYLeft = Axis().setHasLines(true)
        return data
    }

    private fun createLinesForSelectedCategories(expenses: List<Expense>): ArrayList<Line> {
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
        line.setHasPoints(false)
        return line
    }

    private fun pointValues(expenses: List<Expense>): ArrayList<PointValue> {
        val values = arrayListOf<PointValue>()
        iterateExpenses(expenses) { index, day, value ->
            values.add(PointValue(day.dayOfYear.toFloat(), value))
        }
        return values
    }

    private fun lineXValues(): ArrayList<AxisValue> {
        val axisValues = arrayListOf<AxisValue>()
        for (day in range) {
            axisValues.add(AxisValue(day.dayOfYear.toFloat()).setLabel(day.toDayFormat()))
        }
        return axisValues
    }

    private fun generateColumnChartData(expenses: List<Expense>): ColumnChartData {
        val columns = arrayListOf<Column>()
        val axisValues = arrayListOf<AxisValue>()
        iterateExpenses(expenses.filter { selectedCategories.contains(it.category) }) { index, day, value ->
            columns.add(Column(listOf(SubcolumnValue(value, getColor(R.color.accent)))).setHasLabelsOnlyForSelected(true))
            axisValues.add(AxisValue(index.toFloat()).setLabel(day.toDayFormat()))
        }

        val columnData = ColumnChartData(columns)
        columnData.axisXBottom = Axis(axisValues).setHasLines(true)
        columnData.axisYLeft = Axis().setHasLines(true)
        return columnData
    }

    private fun generateCategoriesChartData(expenses: List<Expense>): ColumnChartData {
        val columns = arrayListOf<Column>()
        val groupsExpenses = expenses.groupBy { it.category }.mapValues { it.value.sumByDouble { it.value } }
        selectedCategories.forEachReversed {
            columns.add(Column(listOf(SubcolumnValue(groupsExpenses[it]!!.toFloat(), it.color!!))))
        }
        val columnData = ColumnChartData(columns)
        columnData.axisYLeft = Axis().setHasLines(true)
        return columnData
    }

    inline private fun iterateExpenses(expenses: List<Expense>, function: (Int, Calendar, Float) -> Unit) {
        val dayGroups = expenses.groupBy { it.date.dayOfYear }.mapValues { it.value.sumByDouble { it.value } }
        for ((index, day) in range.withIndex()) {
            val value = dayGroups[day.dayOfYear]?.toFloat() ?: 0f
            function(index, day, value)
        }
    }

    private fun setupAdapter() {
        val adapter = recyclerView.adapter as CategoriesAdapter
        adapter.setList(selectedCategories)
        recyclerView.setLayoutHeight(adapter.itemCount * context.dimenSum(R.dimen.chart_category_item_height))
        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = false
    }
}