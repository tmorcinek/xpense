package com.morcinek.xpense.home.overview.category

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.recyclerview.DividerItemDecoration
import com.morcinek.xpense.common.utils.getParcelableExtra
import com.morcinek.xpense.common.utils.getSerializableExtra
import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.expense.ExpenseManager
import com.morcinek.xpense.data.period.Period
import com.morcinek.xpense.data.period.PeriodObject
import com.morcinek.xpense.data.period.PeriodObjectFactory
import kotlinx.android.synthetic.main.overview_category.*
import javax.inject.Inject

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class OverviewCategoryActivity : AppCompatActivity() {

    @Inject
    lateinit var expenseManager: ExpenseManager

    @Inject
    lateinit var periodObjectFactory: PeriodObjectFactory

    lateinit var category: Category
    lateinit var periodObject: PeriodObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.overview_category)
        (application as Application).component.inject(this)

        setupIntentObjects()
        setupToolbar()
        setupTitle()
        setupRecyclerView()
        updateAdapter()
    }

    private fun setupIntentObjects() {
        category = intent.getParcelableExtra<Category>()!!
        periodObject = periodObjectFactory.getPeriodFilter(intent.getSerializableExtra<Period>()!!)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupTitle() {
        title = "${category.name} (${getString(periodObject.titleResource)})"
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = OverviewCategoryAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutAnimation = LayoutAnimationController(createLayoutAnimation())
        recyclerView.addItemDecoration(DividerItemDecoration(context = this, showFirst = true, showLast = true))
    }

    private fun updateAdapter() {
        val overviewAdapter = recyclerView.adapter as OverviewCategoryAdapter
        overviewAdapter.currency = expenseManager.currency
        overviewAdapter.setList(prepareExpenses())
        recyclerView.startLayoutAnimation()
    }

    private fun prepareExpenses() = expenseManager.getExpenses().filter { it.category == category && periodObject.filter.invoke(it) }

    private fun createLayoutAnimation() = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
}
