package com.morcinek.xpense.common.adapter

import android.R
import android.content.Context
import android.view.View
import android.widget.TextView

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
abstract class SimpleListAdapter<T>(context: Context, resourceId: Int, items: List<T>) : AbstractListAdapter<T, SimpleListAdapter.Holder>(context, resourceId) {

    init {
        this.items = items
    }

    override fun createViewHolder(view: View) = Holder(view)

    class Holder {

        val title: TextView?;
        val icon: View?;

        constructor(view: View) {
            title = view.findViewById(R.id.text1) as TextView?
            icon = view.findViewById(R.id.icon)
        }
    }
}