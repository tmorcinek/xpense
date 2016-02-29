package com.morcinek.xpense.home.statistics.charts

import android.content.Context
import android.view.View
import android.widget.TextView
import com.morcinek.xpense.common.adapter.AbstractListAdapter
import com.morcinek.xpense.common.utils.setDrawableColor
import com.morcinek.xpense.data.category.Category

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoriesFilterAdapter(context: Context, resourceId: Int) : AbstractListAdapter<Category, CategoriesFilterAdapter.Holder>(context, resourceId) {

    override fun createViewHolder(view: View) = Holder(view)

    override fun initializeViews(item: Category, holder: Holder) {
        holder.title?.text = item.name
        holder.icon?.setDrawableColor(item.color!!)
    }

    class Holder {

        val title : TextView?;
//        protected val subtitle : TextView?;
        val icon : View?;

        constructor(view: View){
            title = view.findViewById(android.R.id.text1) as TextView?
//            subtitle = view.findViewById(android.R.id.text2) as TextView?
            icon = view.findViewById(android.R.id.icon)
        }
    }
}