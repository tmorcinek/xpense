package com.morcinek.xpense.home.history

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.BaseFragment
import com.morcinek.xpense.expense.common.ExpenseManager
import kotlinx.android.synthetic.main.default_list.*
import javax.inject.Inject

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class HistoryFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var expenseManager: ExpenseManager

    private lateinit var historyAdapter: HistoryAdapter

    override fun getLayoutResourceId() = R.layout.default_list

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity.application as Application).component.inject(this)

        setupAdapter()
        setupRecyclerView()
        setupSwipeRefreshLayout()
    }

    override fun onResume() {
        super.onResume()
        expenseManager.registerListener(historyAdapter)
    }

    override fun onPause() {
        super.onPause()
        expenseManager.unregisterListener(historyAdapter)
    }

    private fun setupAdapter() {
        historyAdapter = HistoryAdapter(activity)
        historyAdapter.setList(expenseManager.getExpenses())
    }

    private fun setupRecyclerView() {
        recyclerView.setLayoutManager(LinearLayoutManager(activity))
        recyclerView.setItemAnimator(DefaultItemAnimator())
        recyclerView.setAdapter(historyAdapter)
        recyclerView.setLayoutAnimation(LayoutAnimationController(createLayoutAnimation()))
    }

    private fun createLayoutAnimation(): Animation {
        val fadeInAnimation = AnimationUtils.loadAnimation(activity, android.R.anim.slide_in_left)
        fadeInAnimation.setDuration(resources.getInteger(android.R.integer.config_mediumAnimTime).toLong())
        return fadeInAnimation
    }

    private fun setupSwipeRefreshLayout() {
        swipeRefreshLayout.setColorSchemeColors(resources.getColor(R.color.accent))
        swipeRefreshLayout.setOnRefreshListener(this)
    }

    override fun onRefresh() {

    }
}