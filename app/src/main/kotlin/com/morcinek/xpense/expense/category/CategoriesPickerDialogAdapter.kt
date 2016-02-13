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
import com.morcinek.xpense.home.category.CategoriesAdapter

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoriesPickerDialogAdapter(context: Context) : CategoriesAdapter(context) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.dialog_category_item, parent, false))
    }
}