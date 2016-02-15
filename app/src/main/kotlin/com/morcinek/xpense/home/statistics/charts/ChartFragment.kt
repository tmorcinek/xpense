package com.morcinek.xpense.home.statistics.charts

import com.morcinek.xpense.R
import com.morcinek.xpense.common.fragments.BaseFragment
import com.morcinek.xpense.common.pager.PagerAdapter

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ChartFragment : BaseFragment(), PagerAdapter.Page {

    override val title = R.string.title_amount

    override fun getLayoutResourceId() = R.layout.default_list
}