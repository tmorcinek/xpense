package com.morcinek.xpense.splash.data.mock

import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.category.CategoryManager
import com.morcinek.xpense.data.expense.Expense
import java.text.SimpleDateFormat
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
abstract class MockData(val categoryManager: CategoryManager) {

    abstract fun expenses(): List<Expense>

    protected fun createExpense(value: Int, note: String, category: Category, date: String) = Expense(value.toDouble(), category, note, calendarWithString(date))

    protected val food by lazy {
        categoryWithName("Food and Drinks")
    }

    protected val other by lazy {
        categoryWithName("Accommodation")
    }

    protected val health by lazy {
        categoryWithName("Health")
    }

    protected val transportation by lazy {
        categoryWithName("Transportation")
    }

    protected val leisure by lazy {
        categoryWithName("Leisure")
    }

    protected fun calendarWithString(date: String): Calendar {
        val calendar = Calendar.getInstance()
        calendar.time = SimpleDateFormat("dd/MM/yyyy").parse(date)
        return calendar
    }

    protected fun categoryWithName(name: String) = categoryManager.getCategories().find { it.name == name }!!

}