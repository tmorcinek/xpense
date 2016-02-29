package com.morcinek.xpense.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
abstract class AbstractListAdapter<T, H>(val context: Context, val resourceId: Int) : BaseAdapter() {

    var items: List<T> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getCount() = items.size

    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view: View? = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
            view.tag = createViewHolder(view)
        }
        initializeViews(getItem(position), view!!.tag as H)
        return view
    }

    abstract protected fun createViewHolder(view: View): H

    abstract fun initializeViews(item: T, holder: H)
}