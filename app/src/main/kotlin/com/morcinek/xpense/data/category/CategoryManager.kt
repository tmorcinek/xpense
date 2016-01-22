package com.morcinek.xpense.data.category

import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoryManager {

    private val categories: MutableList<Category> = ArrayList()

    init {
        categories.addAll(listOf(Category("Food and Drinks", 0xFFFF0000.toInt()), Category("Alcohol", 0xAAFFBB00.toInt()), Category("Apartment", 0xff00ff00.toInt())))
    }

    fun getCategories(): List<Category> = categories

    fun addCategory(category: Category) {
        categories.add(category)
    }
}