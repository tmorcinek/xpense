package com.morcinek.xpense.data

import com.morcinek.xpense.expense.common.model.Category

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoriesManager {

    fun getCategories() = listOf(Category("Food and Drinks", 0xff00ff),Category("Alcohol", 0x00ffff),Category("Apartment", 0xffff00))
}