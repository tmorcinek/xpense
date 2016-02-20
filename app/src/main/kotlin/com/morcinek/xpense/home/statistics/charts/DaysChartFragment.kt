package com.morcinek.xpense.home.statistics.charts

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.fragments.BaseFragment
import com.morcinek.xpense.common.pager.PagerAdapter
import com.morcinek.xpense.common.utils.dayOfYear
import com.morcinek.xpense.common.utils.toDayFormat
import com.morcinek.xpense.data.expense.ExpenseManager
import com.morcinek.xpense.data.period.PeriodObjectFactory
import kotlinx.android.synthetic.main.days_charts.*
import lecho.lib.hellocharts.model.*
import lecho.lib.hellocharts.util.ChartUtils
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
        val values = ArrayList<PointValue>()
        val axisValues = ArrayList<AxisValue>()
        val dayGroups = expenses().groupBy { it.date.dayOfYear }.mapValues { it.value.sumByDouble { it.value } }
        for (day in dayGroups) {
            values.add(PointValue(day.key.toFloat(), day.value.toFloat()))
            axisValues.add(AxisValue(day.key.toFloat()).setLabel(day.toString()))
        }

        val line = Line(values)
        line.setColor(ChartUtils.COLOR_GREEN)

        val lines = ArrayList<Line>()
        lines.add(line)

        val data = LineChartData(lines)

        data.axisXBottom = Axis(axisValues).setHasLines(true)
        data.axisYLeft = Axis().setName("Expense ($)").setHasLines(true)
        return data

    }

    private fun generateColumnChartData(): ColumnChartData {
        val columns = ArrayList<Column>()
        val axisValues = ArrayList<AxisValue>()
        val dayGroups = expenses().groupBy { it.date.dayOfYear }.mapValues { it.value.sumByDouble { it.value } }
        for ((index, day) in range.withIndex()) {
            val value = dayGroups.get(day.dayOfYear)?.toFloat() ?: 0f
            columns.add(Column(listOf(SubcolumnValue(value, Color.GREEN))).setHasLabelsOnlyForSelected(true))
            axisValues.add(AxisValue(index.toFloat()).setLabel(day.toDayFormat()))
        }

        val columnData = ColumnChartData(columns)

        columnData.axisXBottom = Axis(axisValues).setHasLines(true)
        columnData.axisYLeft = Axis().setHasLines(true)

        return columnData
    }
}