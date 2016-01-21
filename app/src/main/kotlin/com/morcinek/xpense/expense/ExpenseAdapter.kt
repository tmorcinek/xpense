package com.morcinek.xpense.expense

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.morcinek.xpense.R
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter
import com.morcinek.xpense.common.formatters.CurrencyFormatter
import com.morcinek.xpense.common.utils.setDrawableColor
import com.morcinek.xpense.common.utils.startWarningAnimation
import com.morcinek.xpense.data.expense.Expense
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ExpenseAdapter(context: Context) : AbstractRecyclerViewAdapter<Int, ExpenseAdapter.ViewHolder>(context) {

    var invalidItems = setOf<Int>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var expense: Expense = Expense()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        setList(listOf(R.string.title_amount, R.string.title_category, R.string.title_note, R.string.title_date))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.expense_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        initializeOnClickListener(holder, item)
        holder.titleView.text = context.getString(item)
        holder.itemView.isActivated = invalidItems.contains(item)
        if (invalidItems.contains(item)) {
            holder.itemView.startWarningAnimation()
        }
        holder.iconView.visibility = View.GONE
        when (item) {
            R.string.title_amount -> holder.valueView.text = CurrencyFormatter().format(expense.value)
            R.string.title_category -> {
                holder.valueView.text = expense.category?.name
                if (expense.category != null) {
                    holder.iconView.setDrawableColor(expense.category!!.color)
                    holder.iconView.visibility = View.VISIBLE
                }
            }
            R.string.title_note -> holder.valueView.text = expense.note
            R.string.title_date -> holder.valueView.text = dateFormatForTime(expense.date.timeInMillis)
        }
    }

    private fun dateFormatForTime(time: Long) = DateUtils.getRelativeTimeSpanString(time, Date().time, DateUtils.DAY_IN_MILLIS, DateUtils.FORMAT_SHOW_YEAR)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView
        val valueView: TextView

        val iconView: View

        init {
            titleView = view.findViewById(R.id.title) as TextView
            valueView = view.findViewById(R.id.value) as TextView
            iconView = view.findViewById(R.id.icon)
        }
    }
}
