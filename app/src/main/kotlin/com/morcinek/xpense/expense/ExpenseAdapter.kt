package com.morcinek.xpense.expense

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.morcinek.xpense.R
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter
import com.morcinek.xpense.expense.ExpenseItem
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ExpenseAdapter(context: Context) : AbstractRecyclerViewAdapter<ExpenseItem, ExpenseAdapter.ViewHolder>(context) {

    init {
        val expenseItems = ArrayList<ExpenseItem>()
        expenseItems.add(ExpenseItem("Amount", ""))
        expenseItems.add(ExpenseItem("Category", ""))
        expenseItems.add(ExpenseItem("Note", ""))
        expenseItems.add(ExpenseItem("Date", ""))
        setList(expenseItems)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (title, value) = getItem(position)
        holder.titleView.text = title
        holder.valueView.text = value
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.expense_item, parent, false))
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val titleView: TextView
        val valueView: TextView

        init {
            titleView = view.findViewById(R.id.title) as TextView
            valueView = view.findViewById(R.id.value) as TextView
        }
    }
}