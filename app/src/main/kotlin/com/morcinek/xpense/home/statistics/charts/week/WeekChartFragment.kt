package com.morcinek.xpense.home.statistics.charts.week

import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.getColor
import com.morcinek.xpense.common.utils.weekOfYear
import com.morcinek.xpense.data.expense.Expense
import com.morcinek.xpense.home.statistics.charts.AbstractChartFragment
import com.morcinek.xpense.home.statistics.charts.ChartDataGenerator
import com.morcinek.xpense.home.statistics.charts.generators.CategoriesDataGenerator
import com.morcinek.xpense.home.statistics.charts.generators.ColumnDataGenerator
import com.morcinek.xpense.home.statistics.charts.generators.LineDataGenerator
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class WeekChartFragment : AbstractChartFragment() {

    override val title = R.string.stats_week_chart_label

    override val filter = { expense: Expense ->
        expense.date >= periodObjectFactory.firstDayOf5WeeksAgo
    }

    private val range by lazy {
        periodObjectFactory.last5Weeks
    }

    private val extractor: (Calendar) -> Int = { it.weekOfYear }

    private val printer: (Int) -> String = { getString(R.string.statistics_week, it) }

    override val chartDataGenerators: Map<Int, ChartDataGenerator> by lazy {
        mapOf(
                R.id.lineChart to LineDataGenerator(range, extractor, printer),
                R.id.columnChart to ColumnDataGenerator(range, extractor, printer, getColor(R.color.accent)),
                R.id.categoriesChart to CategoriesDataGenerator()
        )
    }
}
