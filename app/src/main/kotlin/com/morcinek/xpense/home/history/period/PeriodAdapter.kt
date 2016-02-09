package com.morcinek.xpense.home.history.period

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.morcinek.xpense.R
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter
import com.morcinek.xpense.common.formatters.CurrencyFormatter
import com.morcinek.xpense.common.utils.*
import com.morcinek.xpense.data.expense.Expense
import com.morcinek.xpense.data.note.ExpenseAction

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class PeriodAdapter(context: Context) : AbstractRecyclerViewAdapter<Expense, PeriodAdapter.ViewHolder>(context) {

    fun updateList(expenses: List<Expense>, expense: Expense, expenseAction: ExpenseAction) {
        when (expenseAction) {
            ExpenseAction.UPDATED -> notifyItemChanged(items.indexOf(expense))
            ExpenseAction.DELETED -> notifyItemRemoved(items.indexOf(expense))
            ExpenseAction.CREATED -> notifyItemInserted(expenses.indexOf(expense))
        }
        items.clear()
        items.addAll(expenses)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        initializeOnClickListener(holder, item)
        holder.iconView.setDrawableColor(item.category!!.color!!)
        holder.iconView.text = item.date.toShortDate()
        holder.titleView.text = item.category!!.name
        holder.subtitleView.text = item.note
        holder.valueView.text = CurrencyFormatter().format(item.value, item.project!!.currency)
        if (position > 0 && !getItem(position - 1).date.isSameDay(item.date)) {
            holder.itemView.setTopMargin(R.dimen.view_margin)
        } else {
            holder.itemView.removeTopMargin()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.period_item, parent, false))
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val iconView: TextView
        val titleView: TextView
        val subtitleView: TextView
        val valueView: TextView

        init {
            iconView = view.findViewById(R.id.icon) as TextView
            titleView = view.findViewById(R.id.title) as TextView
            subtitleView = view.findViewById(R.id.subtitle) as TextView
            valueView = view.findViewById(R.id.value) as TextView
        }
    }
}