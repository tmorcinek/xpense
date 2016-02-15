package com.morcinek.xpense.home.history

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.morcinek.xpense.common.pager.PagerAdapter
import com.morcinek.xpense.common.utils.putSerializable
import com.morcinek.xpense.data.period.Period
import com.morcinek.xpense.home.history.period.PeriodFragment

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class HistoryPagerAdapter(context: Context, fragmentManager: FragmentManager) : PagerAdapter(context, fragmentManager) {

    override val fragments: List<Fragment> by lazy {
        createPeriodFragments()
    }

    val periods = listOf(
            Period.LAST_WEEK,
            Period.THIS_WEEK,
            Period.YESTERDAY,
            Period.TODAY,
            Period.LAST_7_DAYS,
            Period.LAST_30_DAYS,
            Period.THIS_MONTH,
            Period.LAST_MONTH
    )

    private fun createPeriodFragments() = periods.map {
        createPeriodFragment(it)
    }

    private fun createPeriodFragment(it: Period): PeriodFragment {
        val periodFragment = PeriodFragment()
        periodFragment.arguments = Bundle()
        periodFragment.arguments.putSerializable(it)
        return periodFragment
    }
}