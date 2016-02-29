package com.morcinek.xpense.home.statistics.charts

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.fragments.BaseFragment
import com.morcinek.xpense.common.pager.PagerAdapter
import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.expense.Expense
import com.morcinek.xpense.data.expense.ExpenseManager
import com.morcinek.xpense.data.period.PeriodObjectFactory
import kotlinx.android.synthetic.main.days_charts.*
import org.jetbrains.anko.alert
import java.util.*
import javax.inject.Inject

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
abstract class AbstractChartFragment : BaseFragment(), PagerAdapter.Page {

    abstract protected fun updateData(expenses: List<Expense>)

    abstract protected val filter: (Expense) -> Boolean

    @Inject
    lateinit var expenseManager: ExpenseManager

    @Inject
    lateinit var periodObjectFactory: PeriodObjectFactory

    override fun getLayoutResourceId() = R.layout.days_charts

    protected val selectedCategories: ArrayList<Category> by lazy {
        val selectedCategories = arrayListOf<Category>()
        selectedCategories.addAll(defaultCategories())
        selectedCategories
    }

    private fun defaultCategories(): List<Category> {
        val categoriesExpenses = expenses().groupBy { it.category!! }
        return categoriesExpenses.map { it.key to it.value.sumByDouble { it.value } }.sortedByDescending { it.second }.map { it.first }
    }

    private fun expenses() = expenseManager.getExpenses().filter(filter)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity.application as Application).component.inject(this)

        setHasOptionsMenu(true)
        setupRecyclerView()
        updateData(expenses())
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = CategoriesChartAdapter(context)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        updateData(expenses())
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.chart, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_filter -> {
                showCategoriesDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showCategoriesDialog() {
        val listView = ListView(context)
        listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        val itemsAdapter = CategoriesFilterAdapter(context, R.layout.filter_category_item)
        val allCategories = defaultCategories()
        itemsAdapter.list = allCategories
        listView.adapter = itemsAdapter
        allCategories.forEachIndexed { index, category ->
            val contains = selectedCategories.contains(category)
            listView.setItemChecked(index, contains);
        }
        context.alert("Select categories") {
            customView(listView)
            positiveButton("Yes") {
                prepareSelectedCategories(listView)
                updateData(expenses())
            }
            negativeButton("No") {}
        }.show()
    }

    private fun prepareSelectedCategories(listView: ListView) {
        selectedCategories.clear()
        for (index in 0..listView.count - 1) {
            if (listView.checkedItemPositions.get(index)) {
                selectedCategories.add(listView.getItemAtPosition(index) as Category)
            }
        }
    }
}
