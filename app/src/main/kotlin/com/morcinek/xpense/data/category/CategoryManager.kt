package com.morcinek.xpense.data.category

import com.morcinek.xpense.data.category.Category

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoryManager {

    fun getCategories() = listOf(Category("Food and Drinks", 0xFFFF0000.toInt()), Category("Alcohol", 0xAAFFBB00.toInt()), Category("Apartment", 0xff00ff00.toInt()))
}