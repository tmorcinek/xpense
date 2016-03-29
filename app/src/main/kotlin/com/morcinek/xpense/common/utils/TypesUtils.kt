package com.morcinek.xpense.common.utils

import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
fun Double.isInteger(): Boolean = mod(1) == 0.0

fun <T> arrayListOf(elements: Collection<T>): ArrayList<T> {
    val selectedCategories = arrayListOf<T>()
    selectedCategories.addAll(elements)
    return selectedCategories
}
