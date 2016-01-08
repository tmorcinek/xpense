package com.morcinek.xpense.home.history

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.morcinek.xpense.R
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter
import com.morcinek.xpense.expense.common.ExpenseManagerListener
import com.morcinek.xpense.expense.common.model.Expense

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class HistoryAdapter(context: Context) : AbstractRecyclerViewAdapter<Expense, HistoryAdapter.ViewHolder>(context), ExpenseManagerListener {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (value, currency, title, tags, date) = getItem(position)
        holder.titleView.text = title
        holder.subtitleView.text = "${value}"
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.history_list_item, parent, false))
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val titleView: TextView
        val subtitleView: TextView

        init {
            titleView = view.findViewById(R.id.title) as TextView
            subtitleView = view.findViewById(R.id.subtitle) as TextView
        }
    }

    override fun expenseAdded(expense: Expense) {
        items.add(expense)
        notifyItemInserted(items.size -1 )
    }

    override fun expenseDeleted(expense: Expense) {
        throw UnsupportedOperationException()
    }

    override fun expenseChanged(expense: Expense) {
        throw UnsupportedOperationException()
    }
}