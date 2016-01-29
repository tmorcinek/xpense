package com.morcinek.xpense.data.category

import com.morcinek.xpense.common.helpers.PreferencesHelper
import com.orm.SugarRecord

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoryManager(private val preferencesHelper: PreferencesHelper) {

    private val categories: MutableList<Category> = SugarRecord.listAll(Category::class.java)

    init {
    }

    private fun initializeCategories() {
        var initialCategories = listOf(
                Category("Food and Drinks", 0xFF000000.toInt()),
                Category("Accommodation", 0xFFff0000.toInt()),
                Category("Health", 0xFFffc0cb.toInt()),
                Category("Transportation", 0xFF0000ff.toInt()),
                Category("Leisure", 0xFF008080.toInt())
        )
        initialCategories.forEach {
            it.save()
            categories.add(it)
        }
    }

    fun getCategories(): List<Category> = categories

    fun addCategory(category: Category) {
        category.save()
        categories.add(category)
    }
}