package com.morcinek.xpense.common.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import java.util.*

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
abstract class AbstractRecyclerViewAdapter<T, H : RecyclerView.ViewHolder>(protected var context: Context) : RecyclerView.Adapter<H>() {

    protected val items = ArrayList<T>()

    private var itemClickListener: OnItemClickListener<T>? = null

    fun setItemClickListener(itemClickListener: OnItemClickListener<T>) {
        this.itemClickListener = itemClickListener
    }

    protected fun getItem(position: Int): T {
        return items[position]
    }

    fun setList(list: List<T>) {
        items.clear()
        notifyDataSetChanged()
        items.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    protected fun initializeOnClickListener(holder: H, item: T) {
        holder.itemView.setOnClickListener { itemClickListener!!.onItemClicked(item) }
    }

    interface OnItemClickListener<T> {

        fun onItemClicked(item: T)
    }
}