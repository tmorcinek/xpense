package com.morcinek.xpense.home.statistics

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.morcinek.xpense.common.pager.PagerAdapter
import com.morcinek.xpense.common.utils.putSerializable
import com.morcinek.xpense.data.period.Period
import com.morcinek.xpense.home.history.period.PeriodFragment
import com.morcinek.xpense.home.statistics.charts.ChartFragment

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class StatsPagerAdapter(context: Context, fragmentManager: FragmentManager) : PagerAdapter(context, fragmentManager) {

    override val fragments: List<Fragment> by lazy {
        listOf<Fragment>(ChartFragment())
    }
}