package com.morcinek.xpense.splash.data.mock

import com.morcinek.xpense.data.category.CategoryManager
import com.morcinek.xpense.data.expense.Expense

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class FebruaryData(categoryManager: CategoryManager) : MockData(categoryManager) {

    override fun expenses(): List<Expense> = listOf(
            createExpense(2800, "Bike rental", transportation, "29/02/2016"),
            createExpense(60, "Lunch", food, "29/02/2016"),
            createExpense(70, "New year's eve beer", leisure, "29/02/2016"),
            createExpense(250, "New year's eve dinner", food, "29/02/2016"),
            createExpense(60, "Dinner", food, "29/02/2016"),

            createExpense(30, "Chocolate Roti", food, "28/02/2016"),
            createExpense(119, "Dinner", other, "28/02/2016"),
            createExpense(30, "Lunch", food, "28/02/2016"),
            createExpense(26, "Breakfast", food, "28/02/2016"),
            createExpense(70, "Train station transport", transportation, "28/02/2016"),

            createExpense(572, "Electricity, watter bills.", other, "27/02/2016"),
            createExpense(56, "Lunch", food, "27/02/2016"),
            createExpense(40, "Coffee", food, "27/02/2016"),
            createExpense(35, "Breakfast", food, "27/02/2016"),

            createExpense(49, "Dinner", food, "26/02/2016"),
            createExpense(25, "Breakfast", food, "26/02/2016"),
            createExpense(60, "Lunch", food, "26/02/2016"),

            createExpense(572, "Electricity, watter bills.", other, "25/02/2016"),
            createExpense(79, "Lunch", food, "25/02/2016"),
            createExpense(40, "Coffee", food, "25/02/2016"),
            createExpense(35, "Breakfast", food, "25/02/2016"),

            createExpense(49, "Dinner", food, "24/02/2016"),
            createExpense(100, "Beer", leisure, "24/02/2016"),
            createExpense(45, "Breakfast", food, "24/02/2016"),
            createExpense(60, "Lunch", food, "24/02/2016"),

            createExpense(572, "Electricity, watter bills.", other, "23/02/2016"),
            createExpense(6000, "Apartment rent", other, "23/02/2016"),
            createExpense(40, "Coffee", food, "23/02/2016"),
            createExpense(35, "Breakfast", food, "23/02/2016"),

            createExpense(49, "Dinner", food, "22/02/2016"),
            createExpense(40, "Football", leisure, "22/02/2016"),
            createExpense(200, "Package to Poland", leisure, "22/02/2016"),
            createExpense(130, "Present for mum", leisure, "22/02/2016"),
            createExpense(60, "Lunch", food, "22/02/2016"),

            createExpense(93, "Beers", food, "21/02/2016"),
            createExpense(40, "Bananas", food, "21/02/2016"),
            createExpense(49, "Dinner Steak", food, "21/02/2016"),
            createExpense(50, "Playing football", leisure, "21/02/2016"),
            createExpense(322, "Big C shopping", food, "21/02/2016"),
            createExpense(262, "Home stuff", other, "21/02/2016"),
            createExpense(138, "Sport shirts", leisure, "21/02/2016"),
            createExpense(20, "Smoothie", food, "21/02/2016"),
            createExpense(58, "Breakfast", food, "21/02/2016"),

            createExpense(40, "Flat tyre", transportation, "20/02/2016"),
            createExpense(30, "Chocolate Roti", food, "20/02/2016"),
            createExpense(50, "Dinner steak", food, "20/02/2016"),
            createExpense(20, "Smoothie", food, "20/02/2016"),
            createExpense(40, "Breakfast", food, "20/02/2016"),

            createExpense(33, "Water and juice", food, "19/02/2016"),
            createExpense(30, "Roti", food, "19/02/2016"),
            createExpense(120, "Steak dinner", food, "19/02/2016"),
            createExpense(130, "Coffee at Tom N Toms", food, "19/02/2016"),
            createExpense(25, "Coffee", food, "19/02/2016"),
            createExpense(50, "Breakfast", food, "19/02/2016"),

            createExpense(40, "Lunch", food, "18/02/2016"),
            createExpense(25, "Breakfast", food, "18/02/2016"),
            createExpense(70, "Gasoline", transportation, "18/02/2016"),
            createExpense(350, "Drinks in Club", leisure, "18/02/2016"),
            createExpense(49, "Dinner", food, "18/02/2016"),

            createExpense(140, "Shirt", other, "17/02/2016"),
            createExpense(50, "Second dinner", food, "17/02/2016"),
            createExpense(65, "Coffee", food, "17/02/2016"),
            createExpense(45, "Lunch", food, "17/02/2016"),
            createExpense(200, "Photos for Visa", other, "17/02/2016"),
            createExpense(1900, "Visa extension", other, "17/02/2016"),
            createExpense(30, "Chocolate roti", food, "17/02/2016"),
            createExpense(49, "Dinner steak", food, "17/02/2016"),

            createExpense(1463, "Bangkok flight ticket", leisure, "16/02/2016"),
            createExpense(59, "Dinner steak", food, "16/02/2016"),
            createExpense(125, "Medication for accident", leisure, "16/02/2016"),
            createExpense(560, "Scarves", leisure, "16/02/2016"),
            createExpense(245, "Present for grandma", leisure, "16/02/2016"),
            createExpense(155, "Coffee cake", food, "16/02/2016"),
            createExpense(25, "Breakfast", food, "16/02/2016"),
            createExpense(30, "Dinner after", food, "16/02/2016"),

            createExpense(35, "Dinner", food, "15/02/2016"),
            createExpense(59, "Lunch", food, "15/02/2016"),
            createExpense(30, "Pancake", food, "15/02/2016"),

            createExpense(25, "Breakfast", food, "14/02/2016"),
            createExpense(20, "Laundry", other, "14/02/2016"),
            createExpense(35, "Pancake", food, "14/02/2016"),
            createExpense(100, "Beers", leisure, "14/02/2016"),
            createExpense(59, "Lunch steak", food, "14/02/2016"),

            createExpense(5, "Parking", transportation, "13/02/2016"),
            createExpense(70, "Gasoline", transportation, "13/02/2016"),
            createExpense(49, "Dinner steak", food, "13/02/2016"),
            createExpense(80, "Ice cream", food, "13/02/2016"),
            createExpense(25, "Breakfast", food, "13/02/2016"),

            createExpense(100, "Room cleaning", other, "12/02/2016"),
            createExpense(10, "Sweet bread", food, "12/02/2016"),
            createExpense(25, "Tea", food, "12/02/2016"),
            createExpense(40, "Lunch", food, "12/02/2016"),

            createExpense(40, "Lunch", food, "11/02/2016"),
            createExpense(55, "Coffee", food, "11/02/2016"),
            createExpense(49, "Doughnuts", food, "11/02/2016"),
            createExpense(25, "Parking", transportation, "11/02/2016"),

            createExpense(75, "Lunch with coffee", food, "10/02/2016"),
            createExpense(55, "Carmel cake", food, "10/02/2016"),
            createExpense(59, "Lunch", food, "10/02/2016"),
            createExpense(56, "Laundry", other, "10/02/2016"),
            createExpense(20, "Gum", food, "10/02/2016"),
            createExpense(190, "Paper, Mr muscle, toothbrush", health, "10/02/2016"),
            createExpense(100, "Dinner", food, "10/02/2016"),

            createExpense(25, "Breakfast", food, "9/02/2016"),
            createExpense(60, "Gasoline", transportation, "9/02/2016"),
            createExpense(40, "Lunch", food, "9/02/2016"),
            createExpense(70, "Dinner", food, "9/02/2016"),

            createExpense(30, "Lunch", food, "8/02/2016"),
            createExpense(25, "Breakfast", food, "8/02/2016"),
            createExpense(49, "Dinner", food, "8/02/2016"),

            createExpense(49, "Lunch", food, "7/02/2016"),
            createExpense(130, "Ice creams", food, "7/02/2016"),
            createExpense(25, "Breakfast", food, "7/02/2016"),
            createExpense(59, "Dinner steak", food, "7/02/2016"),

            createExpense(20, "Ticket for festival", leisure, "6/02/2016"),
            createExpense(58, "Ice cream", food, "6/02/2016"),
            createExpense(59, "Lunch, steak", food, "6/02/2016"),
            createExpense(130, "Dinner", food, "6/02/2016"),
            createExpense(25, "Breakfast", food, "6/02/2016"),

            createExpense(105, "Fruits", food, "5/02/2016"),
            createExpense(40, "Lunch", food, "5/02/2016"),
            createExpense(80, "Gasoline", transportation, "5/02/2016"),
            createExpense(230, "Breakfast American style.", food, "5/02/2016"),
            createExpense(35, "Dinner", food, "5/02/2016"),

            createExpense(65, "Tea in Camp", food, "4/02/2016"),
            createExpense(40, "Dinner", food, "4/02/2016"),
            createExpense(10, "Strawberries", food, "4/02/2016"),
            createExpense(40, "Lunch", food, "4/02/2016"),
            createExpense(59, "Breakfast", food, "4/02/2016"),

            createExpense(49, "Dinner", food, "3/02/2016"),
            createExpense(70, "Lunch", food, "3/02/2016"),
            createExpense(70, "Breakfast", food, "3/02/2016"),

            createExpense(20, "Laundry", other, "2/02/2016"),
            createExpense(35, "Dinner", food, "2/02/2016"),
            createExpense(50, "Lunch", food, "2/02/2016"),

            createExpense(40, "Lunch", food, "1/02/2016"),
            createExpense(30, "Pancake", food, "1/02/2016"),
            createExpense(320, "Drinks", leisure, "1/02/2016"),
            createExpense(620, "Big C", other, "1/02/2016"),
            createExpense(39, "Big C soap", health, "1/02/2016"),
            createExpense(187, "Big C", food, "1/02/2016")
    )
}
