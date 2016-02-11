package com.morcinek.xpense.home.overview.category

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.morcinek.xpense.R
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter
import com.morcinek.xpense.common.formatters.CurrencyFormatter
import com.morcinek.xpense.common.utils.toDateFormat
import com.morcinek.xpense.data.expense.Expense

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class OverviewCategoryAdapter(context: Context) : AbstractRecyclerViewAdapter<Expense, OverviewCategoryAdapter.ViewHolder>(context) {

    lateinit var currency: String

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val expense = getItem(position)
        holder.dateView.text = expense.date.toDateFormat()
        holder.titleView.text = expense.note
        holder.valueView.text = CurrencyFormatter().format(expense.value, currency)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.overview_category_item, parent, false))
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val dateView: TextView
        val titleView: TextView
        val valueView: TextView

        init {
            dateView = view.findViewById(R.id.date) as TextView
            titleView = view.findViewById(R.id.title) as TextView
            valueView = view.findViewById(R.id.value) as TextView
        }
    }
}
