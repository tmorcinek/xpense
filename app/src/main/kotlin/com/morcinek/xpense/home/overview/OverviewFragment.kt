package com.morcinek.xpense.home.overview

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.BaseFragment
import com.morcinek.xpense.common.formatters.CurrencyFormatter
import com.morcinek.xpense.common.recyclerview.DividerItemDecoration
import com.morcinek.xpense.common.utils.dimenSum
import com.morcinek.xpense.common.utils.setLayoutHeight
import com.morcinek.xpense.common.utils.setTitle
import com.morcinek.xpense.common.utils.startActivity
import com.morcinek.xpense.data.period.Period
import com.morcinek.xpense.data.period.PeriodObject
import com.morcinek.xpense.data.period.PeriodObjectFactory
import com.morcinek.xpense.home.overview.category.OverviewCategoryActivity
import com.morcinek.xpense.home.overview.list.OverviewAdapter
import kotlinx.android.synthetic.main.overview.*
import org.jetbrains.anko.selector
import javax.inject.Inject

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class OverviewFragment : BaseFragment() {

    @Inject
    lateinit var overviewManager: OverviewManager

    @Inject
    lateinit var periodObjectFactory: PeriodObjectFactory

    override fun getLayoutResourceId() = R.layout.overview

    lateinit var periodObject: PeriodObject

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity.application as Application).component.inject(this)

        setTitle(R.string.overview_label)
        setHasOptionsMenu(true)

        setupPeriodObject()
        setupRecyclerView()

        updateUI()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        updateUI()
    }

    private fun updateUI() {
        updateHeader()
        updateOverviewManager()
        updateViews()
    }

    private fun updateOverviewManager() {
        overviewManager.updateManager(periodObject.filter)
    }

    private fun setupPeriodObject() {
        periodObject = periodObjectFactory.getPeriodFilter(Period.LAST_7_DAYS)
    }

    private fun updateHeader() {
        header.text = getString(R.string.overview_title, getString(periodObject.titleResource))
    }

    private fun updateViews() {
        setupChart()
        setupAdapter()
    }

    private fun setupChart() {
        ringChart.values = overviewManager.getChartValues()
        ringChart.text = CurrencyFormatter().format(overviewManager.getExpensesSum(), overviewManager.getCurrency())
        ringChart.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in))
    }

    private fun setupAdapter() {
        val overviewAdapter = recyclerView.adapter as OverviewAdapter
        overviewAdapter.currency = overviewManager.getCurrency()
        overviewAdapter.setList(overviewManager.getOverviewItems())
        recyclerView.setLayoutHeight(overviewAdapter.itemCount * context.dimenSum(R.dimen.overview_item_height, R.dimen.default_divider_size))
        recyclerView.startLayoutAnimation()
    }

    private fun setupRecyclerView() {
        val overviewAdapter = OverviewAdapter(activity)
        recyclerView.adapter = overviewAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutAnimation = LayoutAnimationController(createLayoutAnimation())
        recyclerView.addItemDecoration(DividerItemDecoration(context, showFirst = true))
        overviewAdapter.setItemClickListener {
            activity.startActivity<OverviewCategoryActivity>(it.category, periodObject.period)
        }
    }

    private fun createLayoutAnimation() = AnimationUtils.loadAnimation(activity, android.R.anim.slide_in_left)

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.overview, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_period -> {
                val periodObjects = Period.values().map { periodObjectFactory.getPeriodFilter(it) }
                activity.selector(items = periodObjects.map { getString(it.titleResource) }) { i ->
                    periodObject = periodObjects[i]
                    updateUI()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
