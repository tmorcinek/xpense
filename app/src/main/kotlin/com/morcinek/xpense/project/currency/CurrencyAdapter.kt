package com.morcinek.xpense.project.currency

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.morcinek.xpense.R
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CurrencyAdapter(context: Context) : AbstractRecyclerViewAdapter<Currency, CurrencyAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        (holder.itemView as TextView).text = "(${item.symbol}) ${item.displayName}"
        initializeOnClickListener(holder, item)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}