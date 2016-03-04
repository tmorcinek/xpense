package com.morcinek.xpense.splash.data

import com.morcinek.xpense.data.category.Category
import com.orm.SugarRecord

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoryInitializer() : Initializer {

    override fun initialize() {
        var initialCategories = listOf(
                Category("Food and Drinks", 0xFFffa500.toInt()),
                Category("Accommodation", 0xFF800000.toInt()),
                Category("Health", 0xFF088da5.toInt()),
                Category("Transportation", 0xFF800080.toInt()),
                Category("Leisure", 0xFF008000.toInt()),
                Category("Parties", 0xFFffd700.toInt()),
                Category("Books", 0xFF66cdaa.toInt())
        )
        initialCategories.forEach {
            SugarRecord.saveInTx(initialCategories)
        }
    }
}