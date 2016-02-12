package com.morcinek.xpense.expense.category

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.morcinek.xpense.R
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter
import com.morcinek.xpense.common.utils.setDrawableColor
import com.morcinek.xpense.data.CollectionAction
import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.expense.Expense

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoryAdapter(context: Context) : AbstractRecyclerViewAdapter<Category, CategoryAdapter.ViewHolder>(context) {

    fun updateList(categories: List<Category>, category: Category, collectionAction: CollectionAction) {
        when (collectionAction) {
            CollectionAction.UPDATED -> notifyItemChanged(items.indexOf(category))
            CollectionAction.DELETED -> notifyItemRemoved(items.indexOf(category))
            CollectionAction.CREATED -> notifyItemInserted(categories.indexOf(category))
        }
        items.clear()
        items.addAll(categories)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.category_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        initializeOnClickListener(holder, item)
        holder.textView.text = item.name
        holder.iconView.setDrawableColor(item.color!!)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textView: TextView
        val iconView: View

        init {
            textView = view.findViewById(R.id.text) as TextView
            iconView = view.findViewById(R.id.icon)
        }
    }
}