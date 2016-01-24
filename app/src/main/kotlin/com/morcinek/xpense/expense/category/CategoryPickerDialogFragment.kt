package com.morcinek.xpense.expense.category

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.pickers.TextPickerDialogFragment
import com.morcinek.xpense.common.utils.getTrimString
import com.morcinek.xpense.common.utils.startActivityFromFragment
import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.category.CategoryManager
import com.morcinek.xpense.home.category.CategoryActivity
import kotlinx.android.synthetic.main.text_picker.*
import javax.inject.Inject


/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoryPickerDialogFragment : TextPickerDialogFragment<Category>() {

    @Inject
    lateinit var categoryManager: CategoryManager

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity.application as Application).component.inject(this)

        setTitle(R.string.title_category)
        setupItems()
        setupButton()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Toast.makeText(activity, "Show category fragment", Toast.LENGTH_SHORT).show()
    }

    private fun setupItems() {
        items = categoryManager.getCategories()
        adapter.setList(items)
    }

    private fun setupButton() {
        isButtonVisible = { true }
        button.setImageResource(R.drawable.ic_action_new)
        button.setOnClickListener({
            activity.startActivityFromFragment<CategoryActivity>(this, Category(editText.getTrimString()))
        })
    }

    override fun onItemClicked(item: Category) {
        onItemSetListener(item)
        dialog.dismiss()
    }
}
