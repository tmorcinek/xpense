package com.morcinek.xpense.expense.common.model

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
data class Category(val name: String, val color: Long) : Comparable<Category> {

    override fun compareTo(other: Category) = name.compareTo(other.name)
}