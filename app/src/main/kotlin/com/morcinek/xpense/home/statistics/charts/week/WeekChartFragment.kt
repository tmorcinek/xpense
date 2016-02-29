package com.morcinek.xpense.home.statistics.charts.week

import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.getColor
import com.morcinek.xpense.common.utils.weekOfYear
import com.morcinek.xpense.data.expense.Expense
import com.morcinek.xpense.home.statistics.charts.AbstractChartFragment
import com.morcinek.xpense.home.statistics.charts.ChartDataGenerator

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class WeekChartFragment : AbstractChartFragment() {

    override val title = R.string.stats_week_chart_label

    override val filter = { expense: Expense ->
        expense.date in periodObjectFactory.last5Weeks
    }

    private val range by lazy {
        val weekOfYear = periodObjectFactory.today.weekOfYear
        weekOfYear - 5..weekOfYear
    }

    override val chartDataGenerators: Map<Int, ChartDataGenerator> by lazy {
        mapOf(
                R.id.lineChart to LineDataGenerator(range),
                R.id.columnChart to ColumnDataGenerator(range, getColor(R.color.accent)),
                R.id.categoriesChart to CategoriesDataGenerator()
        )
    }
}
