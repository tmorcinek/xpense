package com.morcinek.xpense.home.history

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.morcinek.xpense.common.utils.putSerializable
import com.morcinek.xpense.data.period.Period
import com.morcinek.xpense.home.history.period.PeriodFragment

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class HistoryPagerAdapter(val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

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

    val fragments: List<PeriodFragment>

    init {
        fragmentManager.fragments?.clear()
        fragments = createPeriodFragments()
    }

    private fun createPeriodFragments() = periods.map {
        createPeriodFragment(it)
    }

    private fun createPeriodFragment(it: Period): PeriodFragment {
        val periodFragment = PeriodFragment()
        periodFragment.arguments = Bundle()
        periodFragment.arguments.putSerializable(it)
        return periodFragment
    }

    override fun getItem(position: Int): PeriodFragment? {
        return fragments[position]
    }

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(getItem(position)!!.getTitle())
    }
}