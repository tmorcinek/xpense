package com.morcinek.xpense.home.category.create

import com.morcinek.xpense.create.Validator
import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.category.CategoryManager

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoryValidator(private val categoryManager: CategoryManager) : Validator<Category> {

    override fun isValid(item: Category): Boolean {
        return item.color != null
                && item.name.isNotEmpty()
                && categoryManager.getCategories().none { it.name.equals(item.name, ignoreCase = true) }
    }
}