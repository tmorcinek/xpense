package com.morcinek.xpense.home.history

import android.os.Bundle
import android.view.View
import com.morcinek.xpense.R
import com.morcinek.xpense.common.fragments.PagerFragment
import com.morcinek.xpense.common.utils.setTitle
import com.morcinek.xpense.data.period.Period
import kotlinx.android.synthetic.main.pager.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class HistoryHostFragment : PagerFragment() {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.history_label)

        setupViewPager()
        setupTabs()
    }

    private fun setupViewPager() {
        val adapter = HistoryPagerAdapter(context, childFragmentManager)
        viewPager.adapter = adapter;
        viewPager.currentItem = adapter.periods.indexOf(Period.LAST_7_DAYS)
    }
}
