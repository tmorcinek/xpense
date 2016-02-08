package com.morcinek.xpense.home.history

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class HistoryPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    var tabTitles = arrayOf("Tab1", "Tab2")

    override fun getCount() = 2

    override fun getItem(position: Int): Fragment? {
        return HistoryFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}