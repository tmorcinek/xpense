package com.morcinek.xpense.splash.data.mock

import com.morcinek.xpense.data.category.CategoryManager
import com.morcinek.xpense.data.expense.Expense

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class JanuaryData(categoryManager: CategoryManager) : MockData(categoryManager) {

    override fun expenses(): List<Expense> = listOf(
            createExpense(75, "Ice cream", food, "30/01/2016"),
            createExpense(35, "Lunch", food, "30/01/2016"),
            createExpense(50, "Breakfast", food, "30/01/2016"),

            createExpense(30, "Pancake", food, "29/01/2016"),
            createExpense(69, "Beef steak", food, "29/01/2016"),
            createExpense(20, "Lunch", food, "29/01/2016"),
            createExpense(30, "Breakfast", food, "29/01/2016"),

            createExpense(30, "Pancake", food, "28/01/2016"),
            createExpense(59, "Fish steak", food, "28/01/2016"),
            createExpense(36, "Lunch", food, "28/01/2016"),
            createExpense(36, "Pancake for breakfast", food, "28/01/2016"),

            createExpense(20, "Smoothie", food, "27/01/2016"),
            createExpense(70, "Gasoline", transportation, "27/01/2016"),
            createExpense(30, "Dinner", food, "27/01/2016"),
            createExpense(57, "Fruits", food, "27/01/2016"),
            createExpense(40, "Breakfast", food, "27/01/2016"),

            createExpense(40, "Dinner", food, "26/01/2016"),
            createExpense(100, "Breakfast", food, "26/01/2016"),

            createExpense(20, "Sandwich", food, "25/01/2016"),
            createExpense(235, "Alcohol", leisure, "25/01/2016"),
            createExpense(78, "Food Big C", food, "25/01/2016"),
            createExpense(317, "Deodorants", health, "25/01/2016"),
            createExpense(790, "Blender", leisure, "25/01/2016"),
            createExpense(50, "Ka-ki", food, "25/01/2016"),
            createExpense(288, "Home supplies", health, "25/01/2016"),
            createExpense(1161, "Football shoes", leisure, "25/01/2016"),

            createExpense(20, "Shake", food, "24/01/2016"),
            createExpense(20, "Breakfast", food, "24/01/2016"),
            createExpense(49, "Dinner", food, "24/01/2016"),
            createExpense(40, "Lunch", food, "24/01/2016"),
            createExpense(6000, "Shake", other, "24/01/2016"),
            createExpense(2670, "Scooter rent", transportation, "24/01/2016"),

            createExpense(85, "Dinner", food, "23/01/2016"),
            createExpense(65, "Gasoline", transportation, "23/01/2016"),
            createExpense(25, "Breakfast", food, "23/01/2016"),

            createExpense(575, "Shopping in Big C", food, "22/01/2016"),
            createExpense(139, "Toothpaste", health, "22/01/2016"),
            createExpense(103, "Glasses", leisure, "22/01/2016"),
            createExpense(300, "Telephone", other, "22/01/2016")
    )
}
