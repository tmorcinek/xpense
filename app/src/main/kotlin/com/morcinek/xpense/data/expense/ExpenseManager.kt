package com.morcinek.xpense.data.expense

import com.morcinek.xpense.data.project.Project
import com.morcinek.xpense.data.project.ProjectManager
import com.orm.SugarRecord
import java.util.*

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
class ExpenseManager(private val projectManager: ProjectManager) {

    private val projectExpenses: MutableList<Expense> by lazy {
        SugarRecord.find(Expense::class.java, "project = ?", "${projectManager.currentProject!!.id}")
    }

    val currentProject: Project
        get() = projectManager.currentProject!!

    fun getExpenses(): List<Expense> = projectExpenses

    fun addExpense(expense: Expense) {
        expense.project = currentProject
        expense.save()
        projectExpenses.add(expense)
        //        listeners.forEach { it.expenseAdded(expense) }
    }

    fun updateExpense(expense: Expense) {
        expense.project = currentProject
        expense.save()
        projectExpenses.set(projectExpenses.indexOf(expense), expense)
        //        listeners.forEach { it.expenseAdded(expense) }
    }

    fun deleteExpense(expense: Expense) {
        projectExpenses.remove(expense)
        expense.delete()
    }

    private val listeners: MutableList<ExpenseManagerListener> = ArrayList()

    fun registerListener(expenseManagerListener: ExpenseManagerListener) {
        listeners.add(expenseManagerListener)
    }

    fun unregisterListener(expenseManagerListener: ExpenseManagerListener) {
        listeners.remove(expenseManagerListener)
    }
}
