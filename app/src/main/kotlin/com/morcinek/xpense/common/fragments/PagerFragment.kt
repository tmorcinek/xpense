package com.morcinek.xpense.common.fragments

import android.content.Intent
import android.support.v4.app.FragmentPagerAdapter
import com.morcinek.xpense.R
import com.morcinek.xpense.common.BaseFragment
import kotlinx.android.synthetic.main.pager.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
open class PagerFragment : BaseFragment() {

    override fun getLayoutResourceId() = R.layout.pager

    protected fun setupTabs() {
        tabs.setupWithViewPager(viewPager);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        onActivityResult(viewPager.adapter as FragmentPagerAdapter, data, requestCode, resultCode)
    }

    private fun onActivityResult(adapter: FragmentPagerAdapter, data: Intent?, requestCode: Int, resultCode: Int) {
        (0..adapter.count).forEach {
            val item = adapter.getItem(it)
            if (item.isVisible) {
                item.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}
