package com.morcinek.xpense.expense.category

import android.os.Bundle
import android.view.View
import com.morcinek.xpense.R
import com.morcinek.xpense.common.pickers.TextPickerDialogFragment
import com.morcinek.xpense.common.utils.hideSoftInput
import com.morcinek.xpense.expense.common.model.Category
import kotlinx.android.synthetic.main.text_picker.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoryPickerDialogFragment : TextPickerDialogFragment<Category>() {

    override fun getLayoutId() = R.layout.text_picker

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editText.clearFocus()
    }

    override fun onItemClicked(item: Category) {
        onItemSetListener(this, item)
        dialog.window.hideSoftInput()
        dialog.dismiss()
    }
}
