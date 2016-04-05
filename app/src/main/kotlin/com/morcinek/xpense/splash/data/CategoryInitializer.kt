package com.morcinek.xpense.splash.data

import android.content.Context
import com.morcinek.xpense.R
import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.category.ColorManager
import com.orm.SugarRecord

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoryInitializer(val context: Context, val colorManager: ColorManager) : Initializer {

    override fun initialize() {
        context.resources.getStringArray(R.array.categories).forEachIndexed { index, name ->
            SugarRecord.save(Category(name, colorManager.colors[index]))
        }
    }
}