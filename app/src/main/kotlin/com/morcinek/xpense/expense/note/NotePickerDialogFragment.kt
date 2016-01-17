package com.morcinek.xpense.expense.note

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.morcinek.xpense.R
import com.morcinek.xpense.common.pickers.TextPickerDialogFragment
import com.morcinek.xpense.common.utils.hideSoftInput
import com.morcinek.xpense.common.utils.showSoftInput
import kotlinx.android.synthetic.main.text_picker.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class NotePickerDialogFragment : TextPickerDialogFragment<String>(), TextView.OnEditorActionListener {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(R.string.title_note)
        setupEditText()
    }

    private fun setupEditText() {
        dialog.window.showSoftInput()
        editText.setOnEditorActionListener(this)
        setEditText(selectedItem!!)
    }

    private fun setEditText(text: String) {
        editText.setText(text)
        editText.setSelection(text.length)
    }

    override fun onItemClicked(item: String) {
        setEditText(item.toString())
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            onItemSetListener(this, editText.text.toString())
            dialog.window.hideSoftInput()
            dialog.dismiss()
            return true
        }
        return false
    }
}
