package com.morcinek.xpense.home.statistics.charts

import android.content.Context
import com.morcinek.xpense.common.adapter.SimpleListAdapter
import com.morcinek.xpense.common.utils.setDrawableColor
import com.morcinek.xpense.data.category.Category

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoriesFilterAdapter(context: Context, resourceId: Int, items: List<Category>) : SimpleListAdapter<Category>(context, resourceId, items) {

    override fun initializeViews(item: Category, holder: Holder) {
        holder.title?.text = item.name
        holder.icon?.setDrawableColor(item.color!!)
    }
}