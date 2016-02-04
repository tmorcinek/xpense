package com.morcinek.xpense.home.overview

import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.expense.ExpenseManager
import com.morcinek.xpense.home.overview.list.OverviewItem

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class OverviewManager(private val expenseManager: ExpenseManager) {

    private var categoriesExpenses: Map<Category, Pair<Double, Double>> = mapOf()

    init {
        updateManager()
    }

    fun updateManager() {
        categoriesExpenses = prepareCategoriesExpenses()
    }

    private fun prepareCategoriesExpenses(): Map<Category, Pair<Double, Double>> {
        val sum = getExpensesSum()
        return expenseManager.getExpenses().groupBy { it.category!! }.mapValues {
            val categorySum = it.value.sumByDouble { it.value }
            categorySum to categorySum / sum
        }
    }

    private fun getExpensesSum() = expenseManager.getExpenses().sumByDouble { it.value }

    fun getCategoriesExpenses() = categoriesExpenses

    fun getChartValues() = categoriesExpenses.map { it.key.color!! to it.value.second.toFloat() }

    fun getOverviewItems() = categoriesExpenses.map { OverviewItem(it.key, it.value.first, it.value.second) }

    fun getCurrency() = expenseManager.currentProject.currency
}