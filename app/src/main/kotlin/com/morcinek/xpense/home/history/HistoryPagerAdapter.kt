package com.morcinek.xpense.home.history

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.morcinek.xpense.home.history.period.PeriodFragment
import com.morcinek.xpense.home.history.period.model.Period

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class HistoryPagerAdapter(val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val fragments: List<PeriodFragment>

    init {
        fragmentManager.fragments?.clear()
        fragments = createPeriodFragments()
    }

    private fun createPeriodFragments() = listOf(
            PeriodFragment(Period.LAST_WEEK),
            PeriodFragment(Period.THIS_WEEK),
            PeriodFragment(Period.YESTERDAY),
            PeriodFragment(Period.TODAY),
            PeriodFragment(Period.LAST_30_DAYS),
            PeriodFragment(Period.LAST_MONTH)
    )

    override fun getItem(position: Int): PeriodFragment? {
        return fragments[position]
    }

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(getItem(position)!!.getTitle())
    }
}