package com.morcinek.xpense.home.history

import android.os.Bundle
import android.view.View
import com.morcinek.xpense.R
import com.morcinek.xpense.common.BaseFragment
import com.morcinek.xpense.common.utils.setTitle
import kotlinx.android.synthetic.main.history_host.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class HistoryHostFragment : BaseFragment() {

    override fun getLayoutResourceId() = R.layout.history_host

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.history_label)

        viewPager.adapter = HistoryPagerAdapter(context, childFragmentManager);
        tabs.setupWithViewPager(viewPager);
    }
}