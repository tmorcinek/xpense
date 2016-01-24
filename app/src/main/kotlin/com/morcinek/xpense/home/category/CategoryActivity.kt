package com.morcinek.xpense.home.category

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.morcinek.xpense.R
import com.morcinek.xpense.common.CreateActivity
import com.morcinek.xpense.common.recyclerview.DividerItemDecoration
import com.morcinek.xpense.common.utils.getParcelable
import com.morcinek.xpense.common.utils.getTrimString
import com.morcinek.xpense.common.utils.setTextWithSelection
import com.morcinek.xpense.data.category.Category
import kotlinx.android.synthetic.main.category.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoryActivity : CreateActivity<Category>() {

    override var item: Category
        get() = Category(editText.getTrimString(), 0)
        set(value) {
            editText.setTextWithSelection(value.name)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category)

        setupAdapter()
        setupRecyclerView()
    }

    override fun restoreItem(bundle: Bundle) = bundle.getParcelable<Category>()

    private fun setupAdapter() {
        //        expenseAdapter = ExpenseAdapter(this)
        //        expenseAdapter.itemClickListener = this
    }


    private fun setupRecyclerView() {
        //        recyclerView.adapter = expenseAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(DividerItemDecoration(context = this, showLast = true))
    }

    override fun onDoneItemSelected() {
        TODO()
    }
}