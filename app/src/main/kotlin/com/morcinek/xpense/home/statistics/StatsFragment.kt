package com.morcinek.xpense.home.statistics

import android.os.Bundle
import android.view.View
import com.morcinek.xpense.R
import com.morcinek.xpense.common.fragments.PagerFragment
import com.morcinek.xpense.common.utils.setTitle
import kotlinx.android.synthetic.main.pager.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class StatsFragment : PagerFragment() {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.statistics_label)

        setupViewPager()
        setupTabs()
    }

    private fun setupViewPager() {
        viewPager.adapter = StatsPagerAdapter(context, childFragmentManager);
    }
}