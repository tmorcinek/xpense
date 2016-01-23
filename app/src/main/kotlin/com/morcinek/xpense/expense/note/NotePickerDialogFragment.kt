package com.morcinek.xpense.expense.note

import android.os.Bundle
import android.view.View
import com.morcinek.xpense.R
import com.morcinek.xpense.common.pickers.TextPickerDialogFragment
import com.morcinek.xpense.common.utils.hideSoftInput
import com.morcinek.xpense.common.utils.showSoftInput
import kotlinx.android.synthetic.main.text_picker.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class NotePickerDialogFragment : TextPickerDialogFragment<String>() {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(R.string.title_note)
        setupButton()
        setupEditText()
    }

    private fun setupButton() {
        isButtonVisible = { it.isNotBlank() }
        button.setImageResource(R.drawable.ic_action_accept)
        button.setOnClickListener({
            onItemSetListener.invoke(editText.text.toString().trim())
            dialog.window.hideSoftInput()
            dialog.dismiss()
        })
    }

    private fun setupEditText() {
        dialog.window.showSoftInput()
        setEditText(selectedItem!!)
    }

    private fun setEditText(text: String) {
        editText.setText(text)
        editText.setSelection(text.length)
    }

    override fun onItemClicked(item: String) {
        setEditText(item.toString())
    }
}
