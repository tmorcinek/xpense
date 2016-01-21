package com.morcinek.xpense.home.history

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.BaseFragment
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter
import com.morcinek.xpense.expense.ExpenseActivity
import com.morcinek.xpense.expense.common.ExpenseManager
import com.morcinek.xpense.expense.common.model.Expense
import kotlinx.android.synthetic.main.default_list.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class HistoryFragment : BaseFragment(), AbstractRecyclerViewAdapter.OnItemClickListener<Expense> {
    @Inject
    lateinit var expenseManager: ExpenseManager

    private lateinit var historyAdapter: HistoryAdapter

    override fun getLayoutResourceId() = R.layout.default_list

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity.application as Application).component.inject(this)

        setupAdapter()
        setupRecyclerView()

        expenseManager.registerListener(historyAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        expenseManager.unregisterListener(historyAdapter)
    }

    private fun setupAdapter() {
        historyAdapter = HistoryAdapter(activity)
        historyAdapter.setList(expenseManager.getExpenses())
        historyAdapter.itemClickListener = this
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = historyAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutAnimation = LayoutAnimationController(createLayoutAnimation())
    }

    private fun createLayoutAnimation() = AnimationUtils.loadAnimation(activity, android.R.anim.slide_in_left)

    override fun onItemClicked(item: Expense) {
        activity.startActivity<ExpenseActivity>("expense" to item)
    }
}
