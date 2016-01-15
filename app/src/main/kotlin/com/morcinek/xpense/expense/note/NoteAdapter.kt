package com.morcinek.xpense.expense.note

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.morcinek.xpense.R
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class NoteAdapter(context: Context) : AbstractRecyclerViewAdapter<String, NoteAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.string_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        (holder.itemView as TextView).text = item
        initializeOnClickListener(holder, item)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}