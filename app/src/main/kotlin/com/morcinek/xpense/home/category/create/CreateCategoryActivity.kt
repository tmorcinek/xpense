package com.morcinek.xpense.home.category.create

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.inputmethod.EditorInfo
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.*
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

    override val labelEdit = R.string.category_label_edit

    override val labelNew = R.string.category_label_new

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

    override protected val canDelete: Boolean by lazy { categoryManager.canDeleteCategory(item) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category)
        (application as Application).component.inject(this)

        setupRecyclerView()
        setupEditText()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        setupName()
        super.onPostCreate(savedInstanceState)
        setupAdapter()
    }

    private fun setupName() {
        item = Category(intent.getStringExtra() ?: "")
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

    override protected fun onDoneItemSelected() {
        hideSoftInput(editText)
        super.onDoneItemSelected()
        if (isEditMode) {
            categoryManager.updateCategory(item)
        } else {
            categoryManager.addCategory(item)
        }
    }

    override protected fun onDeleteItemSelected() {
        super.onDeleteItemSelected()
        categoryManager.deleteCategory(item)
    }
}