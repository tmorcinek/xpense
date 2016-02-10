package com.morcinek.xpense.home.history

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.morcinek.xpense.R
import com.morcinek.xpense.common.BaseFragment
import com.morcinek.xpense.common.utils.setTitle
import com.morcinek.xpense.data.period.Period
import kotlinx.android.synthetic.main.history_host.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class HistoryHostFragment : BaseFragment() {

    override fun getLayoutResourceId() = R.layout.history_host

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.history_label)

        setupViewPager()
        setupTabs()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val adapter = viewPager.adapter as HistoryPagerAdapter
        adapter.fragments.filter { it.isVisible }.forEach {
            it.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun setupViewPager() {
        val adapter = HistoryPagerAdapter(context, childFragmentManager)
        viewPager.adapter = adapter;
        viewPager.currentItem = adapter.periods.indexOf(Period.LAST_7_DAYS)
    }

    private fun setupTabs() {
        tabs.setupWithViewPager(viewPager);
    }
}
