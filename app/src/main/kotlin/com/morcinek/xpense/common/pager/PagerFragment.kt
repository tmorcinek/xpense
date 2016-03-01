package com.morcinek.xpense.common.pager

import android.content.Intent
import android.support.v4.app.FragmentPagerAdapter
import com.morcinek.xpense.R
import com.morcinek.xpense.common.fragments.BaseFragment
import kotlinx.android.synthetic.main.pager.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
open class PagerFragment : BaseFragment() {

    override fun getLayoutResourceId() = R.layout.pager

    open protected fun setupTabs() {
        tabs.setupWithViewPager(viewPager);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        onActivityResult(viewPager.adapter as PagerAdapter, data, requestCode, resultCode)
    }

    private fun onActivityResult(adapter: PagerAdapter, data: Intent?, requestCode: Int, resultCode: Int) {
        adapter.fragments.filter { it.isVisible }.forEach {
            it.onActivityResult(requestCode, resultCode, data)
        }
    }
}
