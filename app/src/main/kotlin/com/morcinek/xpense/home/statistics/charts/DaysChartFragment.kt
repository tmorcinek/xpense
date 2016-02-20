package com.morcinek.xpense.home.statistics.charts

import android.os.Bundle
import android.view.View
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.fragments.BaseFragment
import com.morcinek.xpense.common.pager.PagerAdapter
import com.morcinek.xpense.common.utils.dayOfYear
import com.morcinek.xpense.common.utils.getColor
import com.morcinek.xpense.common.utils.toDayFormat
import com.morcinek.xpense.data.expense.ExpenseManager
import com.morcinek.xpense.data.period.PeriodObjectFactory
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

    @Inject
    lateinit var expenseManager: ExpenseManager

    @Inject
    lateinit var periodObjectFactory: PeriodObjectFactory

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity.application as Application).component.inject(this)

        lineChart.lineChartData = generateLineChartData()
        lineChart.isZoomEnabled = false

        columnChart.columnChartData = generateColumnChartData()
        columnChart.isZoomEnabled = false
    }

    private fun expenses() = expenseManager.getExpenses().filter { it.date in range }

    private fun generateLineChartData(): LineChartData {
        val values = arrayListOf<PointValue>()
        val axisValues = arrayListOf<AxisValue>()
        iterateOverRange { index, day, value ->
            values.add(PointValue(day.dayOfYear.toFloat(), value))
            axisValues.add(AxisValue(day.dayOfYear.toFloat()).setLabel(day.toDayFormat()))
        }

        val data = LineChartData(listOf(createLine(values)))
        data.axisXBottom = Axis(axisValues).setHasLines(true)
        data.axisYLeft = Axis().setHasLines(true)
        return data
    }

    private fun createLine(values: ArrayList<PointValue>): Line {
        val line = Line(values)
        line.setColor(getColor(R.color.accent))
        return line
    }

    private fun generateColumnChartData(): ColumnChartData {
        val columns = arrayListOf<Column>()
        val axisValues = arrayListOf<AxisValue>()
        iterateOverRange { index, day, value ->
            columns.add(Column(listOf(SubcolumnValue(value, getColor(R.color.accent)))).setHasLabelsOnlyForSelected(true))
            axisValues.add(AxisValue(index.toFloat()).setLabel(day.toDayFormat()))
        }

        val columnData = ColumnChartData(columns)
        columnData.axisXBottom = Axis(axisValues).setHasLines(true)
        columnData.axisYLeft = Axis().setHasLines(true)
        return columnData
    }

    inline private fun iterateOverRange(function: (Int, Calendar, Float) -> Unit) {
        val dayGroups = expenses().groupBy { it.date.dayOfYear }.mapValues { it.value.sumByDouble { it.value } }
        for ((index, day) in range.withIndex()) {
            val value = dayGroups[day.dayOfYear]?.toFloat() ?: 0f
            function(index, day, value)
        }
    }
}