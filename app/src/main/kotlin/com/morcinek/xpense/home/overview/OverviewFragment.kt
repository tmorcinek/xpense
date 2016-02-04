package com.morcinek.xpense.home.overview

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.BaseFragment
import com.morcinek.xpense.common.formatters.CurrencyFormatter
import com.morcinek.xpense.common.utils.setTitle
import com.morcinek.xpense.home.overview.list.OverviewAdapter
import kotlinx.android.synthetic.main.default_list.*
import kotlinx.android.synthetic.main.overview.*
import javax.inject.Inject

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class OverviewFragment : BaseFragment() {

    @Inject
    lateinit var overviewManager: OverviewManager

    override fun getLayoutResourceId() = R.layout.overview

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.overview_label)
        (activity.application as Application).component.inject(this)

        setupTitle()
        setupChart()
        setupAmountText()
        setupRecyclerView()
    }

    private fun setupTitle() {
        title.text = getString(R.string.overview_title, "All")
    }

    private fun setupChart() {
        ringChart.values = overviewManager.getChartValues()
    }

    private fun setupAmountText() {
        amount.text = CurrencyFormatter().format(overviewManager.getExpensesSum(), overviewManager.getCurrency())
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = prepareAdapter()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutAnimation = LayoutAnimationController(createLayoutAnimation())
    }

    private fun prepareAdapter(): OverviewAdapter {
        val overviewAdapter = OverviewAdapter(activity)
        overviewAdapter.currency = overviewManager.getCurrency()
        overviewAdapter.setList(overviewManager.getOverviewItems())
        return overviewAdapter
    }

    private fun createLayoutAnimation() = AnimationUtils.loadAnimation(activity, android.R.anim.slide_in_left)
}
