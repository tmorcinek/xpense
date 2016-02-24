package com.morcinek.xpense.home.statistics.charts

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.fragments.BaseFragment
import com.morcinek.xpense.common.pager.PagerAdapter
import com.morcinek.xpense.common.utils.*
import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.expense.Expense
import com.morcinek.xpense.data.expense.ExpenseManager
import com.morcinek.xpense.data.period.PeriodObjectFactory
import com.morcinek.xpense.home.category.CategoriesAdapter
import kotlinx.android.synthetic.main.days_charts.*
import lecho.lib.hellocharts.model.*
import java.util.*
import javax.inject.Inject

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class DaysChartFragment : BaseFragment(), PagerAdapter.Page {

    override val title = R.string.days_chart_label

    override fun getLayoutResourceId() = R.layout.days_charts

    private val range by lazy { periodObjectFactory.last2Weeks }

    private val selectedCategories: ArrayList<Category> by lazy {
        val selectedCategories = arrayListOf<Category>()
        selectedCategories.addAll(defaultCategories())
        selectedCategories
    }

    private fun defaultCategories(): List<Category> {
        val categoriesExpenses = expenses().groupBy { it.category!! }
        return categoriesExpenses.map { it.key to it.value.sumByDouble { it.value } }.sortedByDescending { it.second }.map { it.first }
    }

    @Inject
    lateinit var expenseManager: ExpenseManager

    @Inject
    lateinit var periodObjectFactory: PeriodObjectFactory

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity.application as Application).component.inject(this)

        val expenses = expenses()

        columnChart.columnChartData = generateColumnChartData(expenses)
        columnChart.isZoomEnabled = false

        lineChart.lineChartData = generateLineChartData(expenses)
        lineChart.isZoomEnabled = false
        setupRecyclerView()
        setupAdapter()
    }

    private fun expenses() = expenseManager.getExpenses().filter { it.date in range }

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
        iterateExpenses(expenses) { index, day, value ->
            columns.add(Column(listOf(SubcolumnValue(value, getColor(R.color.accent)))).setHasLabelsOnlyForSelected(true))
            axisValues.add(AxisValue(index.toFloat()).setLabel(day.toDayFormat()))
        }

        val columnData = ColumnChartData(columns)
        columnData.axisXBottom = Axis(axisValues).setHasLines(true)
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

    private fun setupRecyclerView() {
        recyclerView.adapter = CategoriesChartAdapter(context)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun setupAdapter() {
        val adapter = recyclerView.adapter as CategoriesAdapter
        adapter.setList(selectedCategories)
        recyclerView.setLayoutHeight(adapter.itemCount * context.dimenSum(R.dimen.chart_category_item_height))
        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = false
    }
}