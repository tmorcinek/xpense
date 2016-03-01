package com.morcinek.xpense.home.history.period

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.fragments.BaseFragment
import com.morcinek.xpense.common.formatters.CurrencyFormatter
import com.morcinek.xpense.common.pager.PagerAdapter
import com.morcinek.xpense.common.recyclerview.DividerItemDecoration
import com.morcinek.xpense.common.utils.getParcelableExtra
import com.morcinek.xpense.common.utils.getSerializable
import com.morcinek.xpense.common.utils.getSerializableExtra
import com.morcinek.xpense.common.utils.startActivityFromFragment
import com.morcinek.xpense.data.expense.Expense
import com.morcinek.xpense.data.expense.ExpenseManager
import com.morcinek.xpense.data.CollectionAction
import com.morcinek.xpense.data.period.Period
import com.morcinek.xpense.data.period.PeriodObject
import com.morcinek.xpense.data.period.PeriodObjectFactory
import com.morcinek.xpense.expense.ExpenseActivity
import kotlinx.android.synthetic.main.period.*
import javax.inject.Inject

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class PeriodFragment : BaseFragment() , PagerAdapter.Page {

    override fun getLayoutResourceId() = R.layout.period

    @Inject
    lateinit var expenseManager: ExpenseManager

    private val periodObject: PeriodObject by lazy {
        PeriodObjectFactory().getPeriodFilter(arguments.getSerializable<Period>()!!)
    }

    override val title: Int
        get() = periodObject.titleResource

    private lateinit var periodAdapter: PeriodAdapter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity.application as Application).component.inject(this)

        val expenses = expenses()
        setupBalance(expenses)
        setupAdapter(expenses)
        setupRecyclerView()
    }

    private fun setupBalance(expenses: List<Expense>) {
        val sumByDouble = expenses.sumByDouble { it.value }
        amount.text = CurrencyFormatter().format(sumByDouble, expenseManager.currency)
    }

    private fun setupAdapter(expenses: List<Expense>) {
        periodAdapter = PeriodAdapter(activity)
        periodAdapter.setList(expenses)
        periodAdapter.setItemClickListener {
            activity.startActivityFromFragment<ExpenseActivity>(this, it)
        }
    }

    private fun expenses() = expenseManager.getExpenses().filter(periodObject.filter)

    private fun setupRecyclerView() {
        recyclerView.adapter = periodAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutAnimation = LayoutAnimationController(createLayoutAnimation())
    }

    private fun createLayoutAnimation() = AnimationUtils.loadAnimation(activity, android.R.anim.slide_in_left)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            periodAdapter.updateList(expenses(), data!!.getParcelableExtra())
        }
    }
}
