package com.morcinek.xpense.splash.data.mock

import com.morcinek.xpense.data.category.CategoryManager
import com.morcinek.xpense.data.expense.Expense

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class MarchData(categoryManager: CategoryManager) : MockData(categoryManager) {

    override fun expenses(): List<Expense> = listOf(
            createExpense(65, "Tea in Camp", food, "4/03/2016"),
            createExpense(40, "Dinner", food, "4/03/2016"),
            createExpense(10, "Strawberries", food, "4/03/2016"),
            createExpense(40, "Lunch", food, "4/03/2016"),
            createExpense(59, "Breakfast", food, "4/03/2016"),

            createExpense(49, "Dinner", food, "3/03/2016"),
            createExpense(70, "Lunch", food, "3/03/2016"),
            createExpense(70, "Breakfast", food, "3/03/2016"),

            createExpense(20, "Laundry", other, "2/03/2016"),
            createExpense(35, "Dinner", food, "2/03/2016"),
            createExpense(50, "Lunch", food, "2/03/2016"),

            createExpense(40, "Lunch", food, "1/03/2016"),
            createExpense(30, "Pancake", food, "1/03/2016"),
            createExpense(320, "Drinks", leisure, "1/03/2016"),
            createExpense(620, "Big C", other, "1/03/2016"),
            createExpense(39, "Big C soap", health, "1/03/2016"),
            createExpense(187, "Big C", food, "1/03/2016")
    )
}
