package com.morcinek.xpense.home.overview.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.morcinek.xpense.R
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter
import com.morcinek.xpense.common.formatters.CurrencyFormatter
import com.morcinek.xpense.common.utils.setDrawableColor

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class OverviewAdapter(context: Context) : AbstractRecyclerViewAdapter<OverviewItem, OverviewAdapter.ViewHolder>(context) {

    lateinit var currency: String

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (category, amount, percentage) = getItem(position)
        holder.iconView.setDrawableColor(category.color!!)
        holder.titleView.text = category.name
        holder.valueView.text = CurrencyFormatter().format(amount, currency)
        holder.percentageView.text = "${(percentage * 100).toInt()}%"
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.overview_item, parent, false))
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val iconView: TextView
        val titleView: TextView
        val valueView: TextView
        val percentageView: TextView

        init {
            iconView = view.findViewById(R.id.icon) as TextView
            titleView = view.findViewById(R.id.title) as TextView
            valueView = view.findViewById(R.id.value) as TextView
            percentageView = view.findViewById(R.id.percentage) as TextView
        }
    }
}