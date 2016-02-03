package com.morcinek.xpense.home.overview

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.morcinek.xpense.R
import com.morcinek.xpense.common.BaseFragment
import kotlinx.android.synthetic.main.overview.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class OverviewFragment : BaseFragment() {

    override fun getLayoutResourceId() = R.layout.overview

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ringChart.values = listOf(Pair(Color.RED, 0.3f), Pair(Color.GREEN, 0.3f), Pair(Color.BLUE, 0.2f))
    }
}
