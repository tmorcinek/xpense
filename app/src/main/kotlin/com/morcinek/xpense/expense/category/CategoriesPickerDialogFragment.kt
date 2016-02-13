package com.morcinek.xpense.expense.category

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter
import com.morcinek.xpense.common.pickers.TextPickerDialogFragment
import com.morcinek.xpense.common.utils.getTrimString
import com.morcinek.xpense.common.utils.startActivityFromFragment
import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.category.CategoryManager
import com.morcinek.xpense.home.category.create.CreateCategoryActivity
import kotlinx.android.synthetic.main.text_picker.*
import javax.inject.Inject


/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoriesPickerDialogFragment : TextPickerDialogFragment<Category>() {

    @Inject
    lateinit var categoryManager: CategoryManager

    override val adapter: AbstractRecyclerViewAdapter<out Any, out RecyclerView.ViewHolder> by lazy {
        CategoriesPickerDialogAdapter(context)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity.application as Application).component.inject(this)

        setTitle(R.string.title_category)
        setupItems()
        setupButton()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            setupItems()
        }
    }

    private fun setupItems() {
        items = categoryManager.getCategories()
        adapter.setList(items)
    }

    private fun setupButton() {
        button.setImageResource(R.drawable.ic_action_new)
        button.setOnClickListener({
            activity.startActivityFromFragment<CreateCategoryActivity>(this, Category(editText.getTrimString()))
        })
    }

    override fun onItemClicked(item: Category) {
        onItemSetListener?.invoke(item)
        dialog.dismiss()
    }
}
