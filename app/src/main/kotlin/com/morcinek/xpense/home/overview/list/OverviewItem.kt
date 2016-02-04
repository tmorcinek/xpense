package com.morcinek.xpense.home.overview.list

import com.morcinek.xpense.data.category.Category

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
data class OverviewItem(val category: Category, val amount: Double, val percentage: Double) {
}