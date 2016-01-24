package com.morcinek.xpense.home.category

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.inputmethod.EditorInfo
import com.morcinek.xpense.R
import com.morcinek.xpense.common.CreateActivity
import com.morcinek.xpense.common.utils.getParcelable
import com.morcinek.xpense.common.utils.getTrimString
import com.morcinek.xpense.common.utils.hideSoftInput
import com.morcinek.xpense.common.utils.setTextWithSelection
import com.morcinek.xpense.data.category.Category
import kotlinx.android.synthetic.main.category.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoryActivity : CreateActivity<Category>() {

    private fun list() = listOf(
            0xFF000000, 0xFFff0000, 0xFFffc0cb, 0xFF008080, 0xFF0000ff, 0xFFeeeeee,
            0xFF40e0d0, 0xFFcccccc, 0xFFffd700, 0xFF00ffff, 0xFFff7373, 0xFFc0c0c0, 0xFF333333
    ).map { it.toInt() }


    private lateinit var colorAdapter: ColorAdapter

    override var item: Category
        get() = Category(editText.getTrimString(), 0)
        set(value) {
            editText.setTextWithSelection(value.name)
            colorAdapter.selectedItem = value.color
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category)

        setupAdapter()
        setupRecyclerView()
        setupEditText()
    }

    override fun restoreItem(bundle: Bundle) = bundle.getParcelable<Category>()

    private fun setupAdapter() {
        colorAdapter = ColorAdapter(this)
        colorAdapter.setList(list())
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = colorAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 5)
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun setupEditText() {
        editText.setOnEditorActionListener { textView, actionId, keyEvent ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    window.hideSoftInput()
                }
            }
            false
        }
    }

    override fun onDoneItemSelected() {
        TODO()
    }
}