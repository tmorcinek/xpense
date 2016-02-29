package com.morcinek.xpense.home.statistics.charts.day

import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.getColor
import com.morcinek.xpense.data.expense.Expense
import com.morcinek.xpense.home.statistics.charts.AbstractChartFragment
import com.morcinek.xpense.home.statistics.charts.ChartDataGenerator

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class DayChartFragment : AbstractChartFragment() {

    override val title = R.string.stats_days_chart_label

    override val filter = { expense: Expense ->
        expense.date in range
    }

    override val chartDataGenerators: Map<Int, ChartDataGenerator> by lazy {
        mapOf(
                R.id.lineChart to LineDataGenerator(range),
                R.id.columnChart to ColumnDataGenerator(range, getColor(R.color.accent)),
                R.id.categoriesChart to CategoriesDataGenerator()
        )
    }

    private val range by lazy { periodObjectFactory.last2Weeks }
}