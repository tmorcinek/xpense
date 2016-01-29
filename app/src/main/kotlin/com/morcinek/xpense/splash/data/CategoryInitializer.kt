package com.morcinek.xpense.splash.data

import com.morcinek.xpense.data.category.Category
import com.orm.SugarRecord

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoryInitializer() : Initializer {

    override fun initialize() {
        var initialCategories = listOf(
                Category("Food and Drinks", 0xFF000000.toInt()),
                Category("Accommodation", 0xFFff0000.toInt()),
                Category("Health", 0xFFffc0cb.toInt()),
                Category("Transportation", 0xFF0000ff.toInt()),
                Category("Leisure", 0xFF008080.toInt())
        )
        initialCategories.forEach {
            SugarRecord.saveInTx(initialCategories)
        }
    }
}