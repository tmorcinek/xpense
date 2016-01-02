package com.morcinek.xpense.common.expense

import com.morcinek.xpense.common.expense.model.Expense
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ExpenseManagerTest {

    lateinit var expenseManager: ExpenseManager

    @Before
    fun setUp() {
        expenseManager = ExpenseManager()
    }

    @Test
    fun addExpenseTest() {
        var expense = Expense(120f, "GBP", "Pranie", setOf<String>(), Date())
        expenseManager.addExpense(expense)
    }
}
