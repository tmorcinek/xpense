package com.morcinek.xpense.expense

import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.expense.Expense
import com.morcinek.xpense.data.expense.ExpenseManager
import com.morcinek.xpense.data.project.ProjectManager
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import kotlin.expectations.haveSize
import kotlin.expectations.should

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ExpenseManagerTest {

    lateinit var expenseManager: ExpenseManager

    @Before
    fun setUp() {
        expenseManager = ExpenseManager(Mockito.mock(ProjectManager::class.java))
    }

    private fun exampleExpense() = Expense(null, 120.0, Category("Food and Drinks", 0), "Pranie")

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
        expense.note.should.be("Pranie")
        expense.date.should.notBeNull()
    }
}
