package com.morcinek.xpense.data.category

import com.orm.SugarRecord

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoryManager() {

    private var categories: MutableList<Category> = SugarRecord.listAll(Category::class.java)

    fun getCategories(): List<Category> = categories

    fun addCategory(category: Category) {
        category.save()
        categories.add(category)
    }

    fun updateCategory(category: Category) {
        category.save()
        categories[categories.indexOf(category)] = category
    }
}