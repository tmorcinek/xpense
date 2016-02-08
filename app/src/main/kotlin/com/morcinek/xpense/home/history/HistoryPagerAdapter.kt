package com.morcinek.xpense.home.history

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.morcinek.xpense.home.history.period.PeriodFilterFactory
import com.morcinek.xpense.home.history.period.PeriodFragment

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
            PeriodFragment(PeriodFilterFactory.Period.YESTERDAY),
            PeriodFragment(PeriodFilterFactory.Period.TODAY)
    )

    override fun getItem(position: Int): PeriodFragment? {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(getItem(position)!!.getTitle())
    }

    override fun getCount() = fragments.size
}