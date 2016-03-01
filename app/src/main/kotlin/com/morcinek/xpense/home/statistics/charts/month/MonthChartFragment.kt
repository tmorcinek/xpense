package com.morcinek.xpense.home.statistics.charts.month

import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.getColor
import com.morcinek.xpense.common.utils.month
import com.morcinek.xpense.data.expense.Expense
import com.morcinek.xpense.home.statistics.charts.AbstractChartFragment
import com.morcinek.xpense.home.statistics.charts.ChartDataGenerator
import com.morcinek.xpense.home.statistics.charts.generators.CategoriesDataGenerator
import com.morcinek.xpense.home.statistics.charts.week.ColumnDataGenerator
import com.morcinek.xpense.home.statistics.charts.week.LineDataGenerator

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class MonthChartFragment : AbstractChartFragment() {

    override val title = R.string.stats_month_chart_label

    override val filter = { expense: Expense ->
        expense.date.month in range
    }

    private val range by lazy {
        val month = periodObjectFactory.today.month
        (month - 4)..month
    }

    override val chartDataGenerators: Map<Int, ChartDataGenerator> by lazy {
        mapOf(
                R.id.lineChart to LineDataGenerator(range),
                R.id.columnChart to ColumnDataGenerator(range, getColor(R.color.accent)),
                R.id.categoriesChart to CategoriesDataGenerator()
        )
    }
}