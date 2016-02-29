package com.morcinek.xpense.home.statistics

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.morcinek.xpense.common.pager.PagerAdapter
import com.morcinek.xpense.home.statistics.charts.WeekChartFragment
import com.morcinek.xpense.home.statistics.charts.day.DaysChartFragment

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class StatsPagerAdapter(context: Context, fragmentManager: FragmentManager) : PagerAdapter(context, fragmentManager) {

    override val fragments: List<Fragment> by lazy {
        listOf<Fragment>(DaysChartFragment(), WeekChartFragment())
    }
}