package com.morcinek.xpense.data.expense

import com.morcinek.xpense.data.project.Project
import com.morcinek.xpense.data.project.ProjectManager
import com.orm.SugarRecord

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
    }

    fun updateExpense(expense: Expense) {
        expense.project = currentProject
        expense.save()
        projectExpenses.set(projectExpenses.indexOf(expense), expense)
    }

    fun deleteExpense(expense: Expense) {
        projectExpenses.remove(expense)
        expense.delete()
    }
}
