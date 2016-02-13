package com.morcinek.xpense.data.expense

import com.morcinek.xpense.data.project.Project
import com.morcinek.xpense.data.project.ProjectManager
import com.orm.SugarRecord

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
class ExpenseManager(private val projectManager: ProjectManager) {

    private var projectExpenses: MutableList<Expense> = arrayListOf()

    val currentProject: Project
        get() = projectManager.currentProject!!

    val currency: String
        get() = projectManager.currentProject!!.currency

    init {
        updateExpenses()
    }

    fun updateExpenses() {
        projectExpenses = SugarRecord.find(Expense::class.java, "project = ?", "${projectManager.currentProject!!.id}")
    }

    fun getExpenses(): List<Expense> {
        projectExpenses.sortByDescending { it.date }
        return projectExpenses
    }

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
