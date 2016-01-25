package com.morcinek.xpense.home.category

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.inputmethod.EditorInfo
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter
import com.morcinek.xpense.create.CreateActivity
import com.morcinek.xpense.common.utils.*
import com.morcinek.xpense.create.Validator
import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.category.CategoryManager
import com.morcinek.xpense.data.category.ColorManager
import kotlinx.android.synthetic.main.category.*
import javax.inject.Inject
import org.jetbrains.anko.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoryActivity : CreateActivity<Category>() {

    @Inject
    lateinit var categoryManager: CategoryManager

    @Inject
    lateinit var colorManager: ColorManager

    private lateinit var colorAdapter: ColorAdapter

    override var item: Category
        get() = Category(editText.getTrimString(), colorAdapter.selectedItem)
        set(value) {
            editText.setTextWithSelection(value.name)
            colorAdapter.selectedItem = value.color
        }

    override val validator: Validator<Category> by lazy { CategoryValidator(categoryManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category)
        (application as Application).component.inject(this)

        setupAdapter()
        setupRecyclerView()
        setupEditText()
    }

    override fun restoreItem(bundle: Bundle) = bundle.getParcelable<Category>()

    private fun setupAdapter() {
        colorAdapter = ColorAdapter(this)
        colorAdapter.itemClickListener = object : AbstractRecyclerViewAdapter.OnItemClickListener<Int> {
            override fun onItemClicked(item: Int) {
                colorAdapter.onItemClicked(item)
                invalidateItem()
            }
        }
        colorAdapter.setList(colorManager.colors.minus(disabledColors()))
    }

    private fun disabledColors() = categoryManager.getCategories().map { it.color!! }

    private fun setupRecyclerView() {
        recyclerView.adapter = colorAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 5)
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun setupEditText() {
        editText.textChangedListener {
            onTextChanged { charSequence, start, before, count ->
                invalidateItem()
            }
        }
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
        categoryManager.addCategory(item)
    }
}