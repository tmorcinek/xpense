package com.morcinek.xpense.expense

import com.morcinek.xpense.expense.model.Expense
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.expectations.haveSize
import kotlin.expectations.should

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ExpenseManagerTest {

    lateinit var expenseManager: ExpenseManager

    @Before
    fun setUp() {
        expenseManager = ExpenseManager()
    }

    private fun exampleExpense() = Expense(120f, "GBP", "Pranie", setOf<String>(), Date())

    @Test
    fun addExpenseTest() {
        // given
        val expense = exampleExpense()

        // when then
        expenseManager.addExpense(expense)
    }

    @Test
    fun getExpensesTest() {
        // given
        // when
        expenseManager.addExpense(exampleExpense())
        val expenses = expenseManager.getExpenses()

        // then
        expenses.should.notBeNull().and.haveSize(1)
        val expense = expenses.elementAt(0)
        expense.should.notBeNull()
        expense.title.should.be("Pranie")
    }
}
