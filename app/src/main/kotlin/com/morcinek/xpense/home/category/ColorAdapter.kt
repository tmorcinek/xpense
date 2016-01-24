package com.morcinek.xpense.home.category

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morcinek.xpense.R
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter
import com.morcinek.xpense.common.utils.setDrawableColor

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ColorAdapter(context: Context) : AbstractRecyclerViewAdapter<Int, ColorAdapter.ViewHolder>(context), AbstractRecyclerViewAdapter.OnItemClickListener<Int> {

    var selectedItem: Int = 0
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        itemClickListener = this
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.color_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        initializeOnClickListener(holder, item)
        holder.iconView.setDrawableColor(item)
        holder.itemView.setActivated(item == selectedItem)
    }

    override fun onItemClicked(item: Int) {
        notifyItemChanged(items.indexOf(selectedItem))
        selectedItem = item
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val iconView: View

        init {
            iconView = view.findViewById(R.id.icon)
        }
    }
}