package com.morcinek.xpense.home.statistics.charts.common

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.morcinek.xpense.R
import com.morcinek.xpense.home.category.CategoriesAdapter

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoriesChartAdapter (context: Context) : CategoriesAdapter(context) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.chart_category_item, parent, false))
    }
}