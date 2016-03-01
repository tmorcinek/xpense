package com.morcinek.xpense.home.statistics

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.morcinek.xpense.common.pager.PagerAdapter
import com.morcinek.xpense.home.statistics.charts.week.WeekChartFragment
import com.morcinek.xpense.home.statistics.charts.day.DayChartFragment
import com.morcinek.xpense.home.statistics.charts.month.MonthChartFragment

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class StatsPagerAdapter(context: Context, fragmentManager: FragmentManager) : PagerAdapter(context, fragmentManager) {

    override val fragments: List<Fragment> by lazy {
        listOf<Fragment>(DayChartFragment(), WeekChartFragment(), MonthChartFragment())
    }
}