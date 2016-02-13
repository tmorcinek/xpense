package com.morcinek.xpense.home.category.create

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.inputmethod.EditorInfo
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.getParcelable
import com.morcinek.xpense.common.utils.getTrimString
import com.morcinek.xpense.common.utils.hideSoftInput
import com.morcinek.xpense.common.utils.setTextWithSelection
import com.morcinek.xpense.create.CreateActivity
import com.morcinek.xpense.create.Validator
import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.category.CategoryManager
import com.morcinek.xpense.data.category.ColorManager
import kotlinx.android.synthetic.main.category.*
import org.jetbrains.anko.textChangedListener
import javax.inject.Inject

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CreateCategoryActivity : CreateActivity<Category>() {

    @Inject
    lateinit var categoryManager: CategoryManager

    @Inject
    lateinit var colorManager: ColorManager

    private val colorAdapter: ColorAdapter
        get() = recyclerView.adapter as ColorAdapter

    override var item: Category = Category()
        set(value) {
            field = value
            editText.setTextWithSelection(value.name)
            colorAdapter.selectedItem = value.color
        }

    override val validator: Validator<Category> by lazy { CategoryValidator(categoryManager, editItem) }

    private val canDelete: Boolean by lazy { categoryManager.canDeleteCategory(item) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category)
        (application as Application).component.inject(this)

        setupRecyclerView()
        setupEditText()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupAdapter()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu!!.findItem(R.id.action_delete).setVisible(isEditMode && canDelete)
        return super.onPrepareOptionsMenu(menu)
    }

    private fun setupAdapter() {
        if (isEditMode) {
            colorAdapter.setList(listOf(item.color!!).plus(availableColors()))
        } else {
            colorAdapter.setList(availableColors())
        }
    }

    private fun availableColors() = colorManager.colors.minus(disabledColors())

    private fun disabledColors() = categoryManager.getCategories().map { it.color!! }

    override fun restoreItem(bundle: Bundle) = bundle.getParcelable<Category>()

    private fun setupRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(this, 5)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = ColorAdapter(this)
        colorAdapter.setItemClickListener {
            colorAdapter.onItemClicked(it)
            item.color = it
            invalidateItem()
        }
    }

    private fun setupEditText() {
        editText.textChangedListener {
            onTextChanged { charSequence, start, before, count ->
                item.name = editText.getTrimString()
                invalidateItem()
            }
        }
        editText.setOnEditorActionListener { textView, actionId, keyEvent ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    hideSoftInput(editText)
                }
            }
            false
        }
    }

    override fun onDoneItemSelected() {
        hideSoftInput(editText)
        super.onDoneItemSelected()
        if (isEditMode) {
            categoryManager.updateCategory(item)
        } else {
            categoryManager.addCategory(item)
        }
    }
}