package com.morcinek.xpense.home.category

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.morcinek.xpense.R
import com.morcinek.xpense.common.recyclerview.DividerItemDecoration
import com.morcinek.xpense.common.utils.*
import com.morcinek.xpense.data.category.Category
import kotlinx.android.synthetic.main.category.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoryActivity : AppCompatActivity() {

    private var category: Category
        get() = Category(editText.getTrimString(), 0)
        set(value) {
            editText.setTextWithSelection(value.name)
            //
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category)
        //        (application as Application).component.inject(this)

        setupToolbar()
        setupAdapter()
        setupRecyclerView()
        setupCategory()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        val category: Category = savedInstanceState!!.getParcelable()!!
        editText.setText(category.name)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putParcelable(category)
    }

    private fun setupCategory() {
        val intentCategory = intent.getParcelableExtra<Category>()
        if (intentCategory != null) {
            category = intentCategory
        }
    }


    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_action_cancel)
    }

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

}