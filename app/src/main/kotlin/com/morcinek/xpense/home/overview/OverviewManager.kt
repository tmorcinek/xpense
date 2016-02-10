package com.morcinek.xpense.home.overview

import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.expense.Expense
import com.morcinek.xpense.data.expense.ExpenseManager
import com.morcinek.xpense.home.overview.list.OverviewItem

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class OverviewManager(private val expenseManager: ExpenseManager) {

    private var categoriesExpenses: Map<Category, Pair<Double, Double>> = mapOf()
    private var expensesSum: Double = 0.0

    fun updateManager(filter: (Expense) -> Boolean) {
        val expenses = expenseManager.getExpenses().filter(filter)
        expensesSum = prepareExpensesSum(expenses)
        categoriesExpenses = prepareCategoriesExpenses(expenses)
    }

    private fun prepareExpensesSum(expenses: List<Expense>) = expenses.sumByDouble { it.value }

    private fun prepareCategoriesExpenses(expenses: List<Expense>): Map<Category, Pair<Double, Double>> {
        return expenses.groupBy { it.category!! }.mapValues {
            val categorySum = it.value.sumByDouble { it.value }
            categorySum to categorySum / expensesSum
        }.toSortedMap()
    }

    fun getExpensesSum() = expensesSum

    fun getChartValues() = categoriesExpenses.map { it.key.color!! to it.value.second.toFloat() }.sortedByDescending { it.second }

    fun getOverviewItems() = categoriesExpenses.map { OverviewItem(it.key, it.value.first, it.value.second) }.sortedByDescending { it.amount }

    fun getCurrency() = expenseManager.currentProject.currency
}