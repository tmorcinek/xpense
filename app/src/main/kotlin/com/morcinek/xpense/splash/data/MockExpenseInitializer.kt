package com.morcinek.xpense.splash.data

import com.morcinek.xpense.data.category.CategoryManager
import com.morcinek.xpense.data.expense.Expense
import com.morcinek.xpense.data.project.Project
import com.morcinek.xpense.data.project.ProjectManager
import com.morcinek.xpense.splash.data.mock.JanuaryData
import com.orm.SugarRecord

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class MockExpenseInitializer(val projectManager: ProjectManager, val categoryManager: CategoryManager) : Initializer {

    override fun initialize() {
        val project = Project("Chiang Mai", "Chiang Mai", "‎฿")
        projectManager.addProject(project)
        var expenses = arrayListOf<Expense>()
        listOf(JanuaryData(categoryManager)).forEach {
            expenses.addAll(it.expenses())
        }
        expenses.forEach {
            it.project = project
        }
        SugarRecord.saveInTx(expenses)
    }
}